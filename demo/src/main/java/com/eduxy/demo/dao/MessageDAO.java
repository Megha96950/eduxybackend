package com.eduxy.demo.dao;

import java.util.List;

import com.eduxy.demo.model.Message;

public interface MessageDAO {
	List<Message> findChatMessagesFromSelectedUser(String senderId, String recipientId);

    List<Message> findChatMessagesByChatroomId(String chatroomId);

    int countNewMessagesFromOnlineUser(String currentUserId, String onlineUserId);
  void updateMessagesStatusToDelivered(List<Message>msgs);
}