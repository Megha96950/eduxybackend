package com.eduxy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.eduxy.demo.dao.ChatRoomDAO;
import com.eduxy.demo.model.ChatRoom;

public class ChatRoomService {
	
	@Autowired
	private ChatRoomDAO chatRoomDAO;
	
	public ChatRoom findChatroomBySenderIdAndRecipientId(String senderId, String recipientId){
        ChatRoom found = chatRoomDAO.findChatroomBySenderIdAndRecipientId(senderId, recipientId);
        if(found!=null){
            return found;
        }
        return null;
    }
}
