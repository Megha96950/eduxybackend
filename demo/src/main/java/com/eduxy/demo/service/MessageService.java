package com.eduxy.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduxy.demo.dao.MessageDAO;
import com.eduxy.demo.entity.MessageEntity;
import com.eduxy.demo.model.Message;

@Service
public class MessageService {
	   @Autowired
	    private MessageDAO messageDAO;
	
	
	    public int countNewMessagesFromOnlineUser(String currentUserId, String userId){
	        return messageDAO.countNewMessagesFromOnlineUser(currentUserId, userId);
	    }

	    public List<Message>findChatMessagesFromSelectedUser(String senderId, String recipientId){
	        return messageDAO.findChatMessagesFromSelectedUser(senderId, recipientId);
	    }

	    public void updateMessagesStatusToDelivered(List<Message>msgs){
	      messageDAO.updateMessagesStatusToDelivered(msgs);
	    }

	    public List<Message> findChatMessagesByChatroomId(String chatroomId){
	        return messageDAO.findChatMessagesByChatroomId(chatroomId);
	    }

}
