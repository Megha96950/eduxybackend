package com.eduxy.demo.dao;

import com.eduxy.demo.model.ChatRoom;
import com.eduxy.demo.model.Message;

public interface ChatRoomDAO {
	public ChatRoom findChatroomBySenderIdAndRecipientId(String senderId, String recipientId);
	public String createChatRoom(String chatroomId, String senderId,String recipentId);
	public String createMessage(Message chatMessage);
	
}
