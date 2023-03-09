package com.eduxy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduxy.demo.dao.ChatRoomDAO;
import com.eduxy.demo.model.ChatRoom;

@Service
@Transactional
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
	
   public Boolean createChatRoom(String chatroomId, String senderId,String recipentId) {
		String chatRoomId=chatRoomDAO.createChatRoom(chatroomId, senderId,recipentId);
		if(chatRoomId!=null)		
			return true;
				return false;
	}
}
