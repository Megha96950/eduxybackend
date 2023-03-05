package com.eduxy.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.eduxy.demo.dao.ChatRoomDAO;
import com.eduxy.demo.dao.MessageDAO;
import com.eduxy.demo.dao.UserDAO;
import com.eduxy.demo.exception.InternalException;
import com.eduxy.demo.model.ChatRoom;
import com.eduxy.demo.model.Message;
import com.eduxy.demo.service.WebSocketEventListener;

@Controller
public class ChatRoomAPI {

	 @Autowired
	    WebSocketEventListener auth;

	    @Autowired
	    private SimpMessageSendingOperations messagingTemplate;

	    @Autowired
	    private UserDAO userDAO;

	    @Autowired
	    private MessageDAO messageDAO;

	    @Autowired
	    private ChatRoomDAO chatroomDAO;

	    @MessageMapping("/chat")
	    public void sendMessage(Message chatMessage
	            , SimpMessageHeaderAccessor headerAccessor) {
	        String sessionId = headerAccessor.getSessionId();
	        headerAccessor.setSessionId(sessionId);

	        ChatRoom chatroom = chatroomDAO.findChatroomBySenderIdAndRecipientId(chatMessage.getSenderId(),chatMessage.getRecipientId());
	        String chatroomId = "";
	        if(chatroom!=null){
	             chatroomId= String.format("%s_%s", chatMessage.getSenderId(), chatMessage.getRecipientId());

//	            ChatRoom senderRecipient = ChatRoom
//	                    .builder()
//	                    .chatroomId(chatroomId)
//	                    .senderId(chatMessage.getSenderId())
//	                    .recipientId(chatMessage.getRecipientId())
//	                    .build();
//
//	            Chatroom recipientSender = Chatroom
//	                    .builder()
//	                    .chatroomId(chatroomId)
//	                    .senderId(chatMessage.getRecipientId())
//	                    .recipientId(chatMessage.getSenderId())
//	                    .build();
	            try{
//	                chatroomRepository.save(senderRecipient);
//	                chatroomRepository.save(recipientSender);
	            }
	            catch(Exception ex){
	                ex.printStackTrace();
	                throw new InternalException("Cannont create new chat room between sender "+chatMessage.getSenderId()+" and recipient "+chatMessage.getRecipientId());
	            }

	        }
	        else{
	         //   chatroomId = chatroom.get().getChatroomId();
	        }
	        chatMessage.setChatroomId(chatroomId);
	        Message saved = null;
	        try{
	            //saved = messageRepository.save(chatMessage);
	        }
	        catch(Exception ex){
	            throw new InternalException("Cannot create new message in chatroomId "+ chatroomId);
	        }

	     //   NotificationDto noti = MapperUtils.mapperObject(saved, NotificationDto.class);

	       // messagingTemplate.convertAndSendToUser(chatMessage.getRecipientName(),"/queue/messages",noti);
	    }

}
