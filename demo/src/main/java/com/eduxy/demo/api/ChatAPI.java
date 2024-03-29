package com.eduxy.demo.api;


import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eduxy.demo.exception.IsSameUserException;
import com.eduxy.demo.exception.UserNotFoundException;
import com.eduxy.demo.model.ChatChannel;
import com.eduxy.demo.model.ChatMessage;
import com.eduxy.demo.model.EstablishedChatConnection;
import com.eduxy.demo.model.User;
import com.eduxy.demo.service.ChatService;
import com.eduxy.demo.service.NotificationService;
import com.eduxy.demo.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("ChatAPI")
public class ChatAPI  {
  @Autowired
  private ChatService chatService;

  @Autowired
  private UserService userService;
  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;
  @Autowired
  private NotificationService notificationService;

    @MessageMapping("/chat/{channelId}")  
    @SendTo("/topic/message/{channelId}")
    public ChatMessage chatMessage(@DestinationVariable String channelId,ChatMessage message) 
        throws BeansException, UserNotFoundException,InterruptedException {

      chatService.submitMessage(message,channelId);
      
      simpMessagingTemplate.convertAndSendToUser(channelId,"/topic/private-notifications", message);
     // notificationService.sendPrivateNotification(message.getRecipientUserId());
    //  simpMessagingTemplate.convertAndSend("/topic/message/"+channelId,message);
      
      return message;
    }

   // @RequestMapping(value="/api/private-chat/channel", method=RequestMethod.PUT, produces="application/json", consumes="application/json")
    @PostMapping(value="chat", produces="application/json")
    public ResponseEntity<EstablishedChatConnection> establishChatChannel(@RequestBody ChatChannel chatChannel) 
        throws IsSameUserException, UserNotFoundException { 
      String channelUuid = chatService.establishChatSession(chatChannel);
      User userOne = userService.getUser(chatChannel.getUserIdOne());
      User userTwo = userService.getUser(chatChannel.getUserIdTwo());
      

     EstablishedChatConnection establishedChatChannel = new EstablishedChatConnection(
        channelUuid,
        userOne.getName(),
        userTwo.getName()
      );

      return new ResponseEntity<EstablishedChatConnection>(establishedChatChannel,HttpStatus.OK);
    		  //JSONResponseHelper.createResponse(establishedChatChannel, HttpStatus.OK);
    }
    
    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping(value="/channel/{channelUuid}", produces="application/json")
    public ResponseEntity<List<ChatMessage>> getExistingChatMessages(@PathVariable("channelUuid") String channelUuid) {
    	  System.out.println(channelUuid);
      List<ChatMessage> messages = chatService.getExistingChatMessages(channelUuid);
   
     for(ChatMessage t:messages) {
    	System.out.println(t.getContents());
     }
      
      return new ResponseEntity<List<ChatMessage>>(messages,HttpStatus.OK);
    		  //JSONResponseHelper.createResponse(messages, HttpStatus.OK);
    }
    
    @GetMapping(value="/friend/{Id}")
    public ResponseEntity<List<User>> getFriendListFor(@PathVariable String Id){
    	List<User> users =userService.getFriendListFor(Id);
    	return new ResponseEntity<List<User>>(users,HttpStatus.OK);
  }

}
