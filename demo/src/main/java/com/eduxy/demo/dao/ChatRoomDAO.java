package com.eduxy.demo.dao;

import com.eduxy.demo.model.ChatRoom;

public interface ChatRoomDAO {
	public ChatRoom findChatroomBySenderIdAndRecipientId(String senderId, String recipientId);
	public String createChatRoom(String chatroomId, String senderId,String recipentId);
	
}
