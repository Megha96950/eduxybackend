package com.eduxy.demo.dao;
import java.util.List;

import org.springframework.data.domain.Pageable;


import com.eduxy.demo.entity.ChatMessageEntity;
import com.eduxy.demo.model.ChatMessage;




public interface ChatMessageDAO 
//extends CrudRepository<ChatMessageEntity, String>
{
   
    public List<ChatMessage> getExistingChatMessages(
       // @Param("userIdOne") 
        String userIdOne,
       // @Param("userIdTwo")
        String userIdTwo, Pageable pageable);
    
    public void submitMessage(ChatMessageEntity chatMessage);
}
