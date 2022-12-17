package com.eduxy.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.eduxy.demo.entity.ChatChannelEntity;

import com.eduxy.demo.model.ChatChannel;
import com.eduxy.demo.service.UserService;
;

public class ChatChannelDAOImpl implements ChatChannelDAO {

	@Autowired
	private EntityManager entityManager;
	
	private UserService userService;
	@Override
	public List<ChatChannel> findExistingChannel(String userOneId, String userTwoId) {
		List<ChatChannel> channels=new ArrayList();
		Query query = entityManager.createQuery("select c from chatchannel c"
				+ " where c.userone in (?1,?2)"
			  + "and c.userone in (?1,?2)");
		 query.setParameter(1,"%"+userOneId+"%" );
		 query.setParameter(1,"%"+userTwoId+"%" );
		 List<ChatChannelEntity> ChatChannelEntities = query.getResultList();
		 for(ChatChannelEntity t: ChatChannelEntities) {
			ChatChannel channel =new ChatChannel();
			channel.setUserIdOne(userOneId);
			channel.setUserIdTwo(userTwoId);
			channels.add(channel);
			 
		 }
		return channels;
	}
	@Override
	public String newChatSession(ChatChannel chatchannel) {
		ChatChannelEntity chatChannelEntity =new ChatChannelEntity
				(userService.getUser(ChatChannel.getUserIdOne()),userService.getUser(ChatChannel.getUserIdTwo()));
		entityManager.persist(chatChannelEntity);
		return chatChannelEntity.getUuid();
	}
	
	@Override
	public ChatChannelEntity getChannelDetails(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

}
