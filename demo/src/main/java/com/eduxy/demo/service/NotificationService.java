package com.eduxy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduxy.demo.model.ResponseMessage;


@Service
@Transactional
public class NotificationService {
	  private final SimpMessagingTemplate messagingTemplate;

	    @Autowired
	    public NotificationService(SimpMessagingTemplate messagingTemplate) {
	        this.messagingTemplate = messagingTemplate;
	    }

	  
	    public void sendPrivateNotification(final String userId) {
	        ResponseMessage message = new ResponseMessage("Private Notification");

	        messagingTemplate.convertAndSendToUser(userId,"/topic/private-notifications", message);
	       // messagingTemplate.convertAndSend("/topic/private-notifications/"+userId, message);
	      
	    }
}
