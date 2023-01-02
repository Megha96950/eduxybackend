package com.eduxy.demo.api;


import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.eduxy.demo.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("ChatAPI")
public class ChatAPI  {
  @Autowired
  private ChatService chatService;

  @Autowired
  private UserService userService;

    @MessageMapping("/chat/{channelId}")
    @SendTo("/chat/{channelId}")
   // @PostMapping(value="chat/{channelId}")
    public ChatMessage chatMessage(@PathVariable String channelId,@RequestBody ChatMessage message)
        throws BeansException, UserNotFoundException {
    	System.out.println(message);
      chatService.submitMessage(message);

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
    
    @RequestMapping(value="/api/private-chat/channel/{channelUuid}", method=RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<ChatMessage>> getExistingChatMessages(@PathVariable("channelUuid") String channelUuid) {
      List<ChatMessage> messages = chatService.getExistingChatMessages(channelUuid);

      return new ResponseEntity<List<ChatMessage>>(messages,HttpStatus.OK);
    		  //JSONResponseHelper.createResponse(messages, HttpStatus.OK);
    }
}
