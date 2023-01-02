package com.eduxy.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.eduxy.demo.entity.ChatMessageEntity;

@Repository(value="chatMessageDAO")
public class ChatMessageDAOImpl implements ChatMessageDAO {
	@Autowired
	private EntityManager entityManager;
	@Override
	public List<ChatMessageEntity> getExistingChatMessages(String userIdOne, String userIdTwo, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void submitMessage(ChatMessageEntity chatMessageEntity) {
		  entityManager.persist(chatMessageEntity);
		
	}

}
