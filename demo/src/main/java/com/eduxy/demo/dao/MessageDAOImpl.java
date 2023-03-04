package com.eduxy.demo.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eduxy.demo.entity.ChatRoomEntity;
import com.eduxy.demo.entity.MessageEntity;
import com.eduxy.demo.model.ChatRoom;
import com.eduxy.demo.model.Message;

@Repository(value = "messageDAO")
public class MessageDAOImpl implements MessageDAO{
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Message> findChatMessagesFromSelectedUser(String senderId, String recipientId) {
		Query query = entityManager.createQuery("select c from MessageEntity c"
		+ " where c.senderId=?1 and"
				+ " c.recipientId=?2");
		query.setParameter(1,senderId);
		query.setParameter(2,recipientId);
		List<MessageEntity> messageEntity =query.getResultList();
	List<Message> messages =messageEntity.stream()
			.map(msg->modelMapper
		    .map(msg,Message.class))
			.collect(Collectors.toList());
		return messages;
	}

	@Override
	public List<Message> findChatMessagesByChatroomId(String chatroomId) {
		Query query = entityManager.createQuery("select c from MessageEntity c"
				+ " where c.=?1");
				query.setParameter(1,chatroomId);
				List<MessageEntity> messageEntity =query.getResultList();
			List<Message> messages =messageEntity.stream()
					.map(msg->modelMapper.map(msg,Message.class))
					.collect(Collectors.toList());
				return messages;
		
	}

	@Override
	public int countNewMessagesFromOnlineUser(String currentUserId, String onlineUserId) {
		Query query =entityManager.createQuery("SELECT COUNT(*) FROM Message m"
				+ " WHERE m.recipientId = ?1 AND"
				+ " m.senderId = ?2 AND"
				+ " m.status = 'RECEIVED'");
		query.setParameter(1,currentUserId);
		query.setParameter(1,onlineUserId);
		List<Integer>  count =query.getResultList();
		return count.get(0);
	}
	@Override
	  public void updateMessagesStatusToDelivered(List<Message>msgs) {
		 msgs.stream().filter(m->m.getStatus().equals("RECEIVED")).forEach(m->{
	            m.setStatus("DELIVERED");
	          List<MessageEntity> messageEntity =msgs.stream()
	      			.map(msg->modelMapper
	      				    .map(msg,MessageEntity.class))
	      					.collect(Collectors.toList());
	           entityManager.merge(msgs);
	        });
	  }

}
