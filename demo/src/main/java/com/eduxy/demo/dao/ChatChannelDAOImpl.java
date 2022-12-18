package com.eduxy.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eduxy.demo.entity.ChatChannelEntity;
import com.eduxy.demo.entity.UserEntity;
import com.eduxy.demo.model.ChatChannel;
import com.eduxy.demo.model.User;
import com.eduxy.demo.service.UserService;
;
@Repository(value = "chatChannelDAO")
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
		User userOne= userService.getUser(ChatChannel.getUserIdOne());
		User userTwo=userService.getUser(ChatChannel.getUserIdTwo());
		UserEntity userEntityOne= new UserEntity();
		UserEntity userEntityTwo= new UserEntity();
		userEntityOne.setEmailId(userOne.getEmailId());
		userEntityOne.setName(userOne.getName());
		userEntityOne.setPhoneNumber(userOne.getPhoneNumber());
		userEntityOne.setRole(userOne.getRole());
		userEntityTwo.setEmailId(userTwo.getEmailId());
		userEntityTwo.setName(userTwo.getName());
		userEntityTwo.setPhoneNumber(userTwo.getPhoneNumber());
		userEntityTwo.setRole(userTwo.getRole());
		ChatChannelEntity chatChannelEntity =new ChatChannelEntity
				(userEntityOne,userEntityTwo);
		entityManager.persist(chatChannelEntity);
		return chatChannelEntity.getUuid();
	}
	
	@Override
	public ChatChannel getChannelDetails(String uuid) {
		Query query = entityManager.createQuery("select c from chatchannel c"
				+ " where c.uuid = ?1");
		 query.setParameter(1,"%"+uuid+"%" );
		List<ChatChannelEntity> chatChannelEntity =query.getResultList();
		ChatChannel channel =new ChatChannel();
		for(ChatChannelEntity t: chatChannelEntity) {
			
			channel.setUserIdOne(t.getUserOne().getEmailId());
			channel.setUserIdTwo(t.getUserTwo().getEmailId());
			
			 
		 }
		return channel;
	}

}
