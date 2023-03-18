package com.eduxy.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eduxy.demo.entity.ChatMessageEntity;
import com.eduxy.demo.entity.ChatRoomEntity;
import com.eduxy.demo.model.ChatRoom;
@Repository(value = "chatRoomDAO")
public class ChatRoomDAOImpl implements ChatRoomDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public ChatRoom findChatroomBySenderIdAndRecipientId(String senderId, String recipientId) {
		Query query = entityManager.createQuery("select c from ChatRoomEntity c"
				+ " where c.senderId=?1 and"
				+ " c.recipientId=?2");
		query.setParameter(1,senderId);
		query.setParameter(2,recipientId);
		List<ChatRoomEntity> chatRoomEntity =query.getResultList();
		for(ChatRoomEntity chat : chatRoomEntity) {
			ChatRoom chatRoom =modelMapper.map(chat,ChatRoom.class);
			return chatRoom;
		}
			
		


		
		return null;
	}
	
	@Override
	public String createChatRoom(String chatroomId, String senderId,String recipentId) {
		ChatRoomEntity chatRoomEntity=new ChatRoomEntity();
		try {
		chatRoomEntity.setChatroomId(chatroomId);
		chatRoomEntity.setSenderId(senderId);
		chatRoomEntity.setRecipientId(recipentId);
		entityManager.persist(chatRoomEntity);
		
		return chatroomId;
		}catch(Exception ex) {
			return null;
			
		}
		
	}

}
