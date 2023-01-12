package com.eduxy.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.eduxy.demo.entity.ChatChannelEntity;
import com.eduxy.demo.entity.ChatMessageEntity;
import com.eduxy.demo.model.ChatChannel;
import com.eduxy.demo.model.ChatMessage;

@Repository(value="chatMessageDAO")
public class ChatMessageDAOImpl implements ChatMessageDAO {
	@Autowired
	private EntityManager entityManager;
	@Override
	public List<ChatMessage> getExistingChatMessages(String userIdOne, String userIdTwo, Pageable pageable) {
		Query query = entityManager.createQuery("select c from ChatMessageEntity c"
				+ " where c.authorUserId in (?1,?2) and"
				+ " c.recipientUserId in (?1 ,?2) order by c.timeSent desc");
		query.setParameter(1,userIdOne);
		query.setParameter(2,userIdTwo);
		List<ChatMessageEntity> chatMessageEntity =query.getResultList();
		List<ChatMessage> messages =new ArrayList();
		for(ChatMessageEntity t: chatMessageEntity) {
		    ChatMessage message=new ChatMessage();
		    message.setAuthorUserId(t.getAuthorUserId());
		    message.setRecipientUserId(t.getRecipientUserId());
		    message.setContents(t.getContents());
			messages.add(message);
			 
		 }
		return messages;
	
	}

	@Override
	public void submitMessage(ChatMessageEntity chatMessageEntity) {
		  entityManager.persist(chatMessageEntity);
		
	}

}
