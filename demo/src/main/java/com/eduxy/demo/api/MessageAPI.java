package com.eduxy.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduxy.demo.exception.InternalException;
import com.eduxy.demo.model.ChatRoom;
import com.eduxy.demo.model.Message;
import com.eduxy.demo.service.ChatRoomService;
import com.eduxy.demo.service.MessageService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("MessageAPI")
public class MessageAPI {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatRoomService chatroomService;


    @GetMapping("/{senderId}/{recipientId}")
    public ResponseEntity<List<Message>> getChatMessages(@PathVariable String senderId,
                                                         @PathVariable String recipientId) {
        List<Message> messagesFromSenderRepicient = null;
        try{
        	
            List<Message> msgs = messageService.findChatMessagesFromSelectedUser(senderId, recipientId);
        
            messageService.updateMessagesStatusToDelivered(msgs);

            ChatRoom cr = chatroomService.findChatroomBySenderIdAndRecipientId(senderId, recipientId);

            if(cr!=null){
                messagesFromSenderRepicient = messageService.findChatMessagesByChatroomId(cr.getChatroomId());
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            throw new InternalException("Cannot find messages between sender "+senderId+" and recipient "+recipientId);
        }
        return new ResponseEntity<List<Message>>(messagesFromSenderRepicient, HttpStatus.OK);
    }
}
