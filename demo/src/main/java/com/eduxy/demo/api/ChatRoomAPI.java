package com.eduxy.demo.api;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduxy.demo.dao.ChatRoomDAO;
import com.eduxy.demo.dao.MessageDAO;
import com.eduxy.demo.dao.UserDAO;
import com.eduxy.demo.entity.MessageEntity;
import com.eduxy.demo.exception.InternalException;
import com.eduxy.demo.model.ChatRoom;
import com.eduxy.demo.model.Message;
import com.eduxy.demo.service.ChatRoomService;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("ChatRoomAPI")
public class ChatRoomAPI {

//	    @Autowired
//	    WebSocketEventListener auth;
//
	    @Autowired
	    private SimpMessageSendingOperations messagingTemplate;
//
//	    @Autowired
//	    private UserDAO userDAO;
//
//	    @Autowired
//	    private MessageDAO messageDAO;

	    @Autowired
	    private ChatRoomService chatroomService;
	    @Autowired
	    private ModelMapper modelMapper;

	    @Autowired
	    private EntityManager entityManager;
	    
	    @MessageMapping("/chat/")
	    public void sendMessage(Message chatMessage , SimpMessageHeaderAccessor headerAccessor) {
	        String sessionId = headerAccessor.getSessionId();
	        headerAccessor.setSessionId(sessionId);
          
	        ChatRoom chatroom = chatroomService.findChatroomBySenderIdAndRecipientId(chatMessage.getSenderId(),chatMessage.getRecipientId());
	        String chatroomId = "";
	        if(chatroom==null){
	        	
	             chatroomId= String.format("%s_%s", chatMessage.getSenderId(), chatMessage.getRecipientId());
                 Boolean senderRecipient= chatroomService.createChatRoom(chatroomId, chatMessage.getSenderId(), chatMessage.getRecipientId());
                 Boolean recipientSender= chatroomService.createChatRoom(chatroomId,  chatMessage.getRecipientId(),chatMessage.getSenderId());
                 if(!senderRecipient || !recipientSender )
	                throw new InternalException("Cannont create new chat room between sender "+chatMessage.getSenderId()+" and recipient "+chatMessage.getRecipientId());
	        }
	        else{
	        chatroomId = chatroom.getChatroomId();
	        }
	        chatMessage.setChatroomId(chatroomId);
	        System.out.println(chatMessage.getChatroomId());
	        Message saved = null;
	        try{
	         //   saved = messageRepository.save(chatMessage);
	        	 // System.out.println("dshalfjdshuioAYE8FHSJKAFFSGZULSD.............................................................hfyuiugjgfyufrtydukduffghddfghgffy");
//	        	MessageEntity messageentity=modelMapper.map(chatMessage,MessageEntity.class);
//	        	  System.out.println(messageentity.getContent());
//	        	entityManager.persist(messageentity);
	        	  chatroomService.createMessage(chatMessage);
	        }
	        catch(Exception ex){
	            throw new InternalException("Cannot create new message in chatroomId "+ chatroomId);
	        }

	     //   NotificationDto noti = MapperUtils.mapperObject(saved, NotificationDto.class);

	       messagingTemplate.convertAndSendToUser(chatMessage.getRecipientName(),"/queue/messages",chatMessage);
	    }

}
