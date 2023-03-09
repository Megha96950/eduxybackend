package com.eduxy.demo.api;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.eduxy.demo.dao.ChatRoomDAO;
import com.eduxy.demo.dao.MessageDAO;
import com.eduxy.demo.dao.UserDAO;
import com.eduxy.demo.entity.MessageEntity;
import com.eduxy.demo.exception.InternalException;
import com.eduxy.demo.model.ChatRoom;
import com.eduxy.demo.model.Message;
import com.eduxy.demo.service.ChatRoomService;
import com.eduxy.demo.service.WebSocketEventListener;

@Controller
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
	    
	    @MessageMapping("/chat")
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
	        Message saved = null;
	        try{
	         //   saved = messageRepository.save(chatMessage);
	        	MessageEntity messageentity=modelMapper.map(chatMessage,MessageEntity.class);
	        	entityManager.persist(messageentity);
	        }
	        catch(Exception ex){
	            throw new InternalException("Cannot create new message in chatroomId "+ chatroomId);
	        }

	     //   NotificationDto noti = MapperUtils.mapperObject(saved, NotificationDto.class);

	       messagingTemplate.convertAndSendToUser(chatMessage.getRecipientName(),"/queue/messages",chatMessage);
	    }

}
