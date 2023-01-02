package com.eduxy.demo.dao;
import java.util.List;

import org.springframework.data.domain.Pageable;


import com.eduxy.demo.entity.ChatMessageEntity;




public interface ChatMessageDAO 
//extends CrudRepository<ChatMessageEntity, String>
{
//    @Query(" FROM"
//        + "    ChatMessage m"
//        + "  WHERE"
//        + "    m.authorUser.id IN (:userIdOne, :userIdTwo)"
//        + "  AND"
//        + "    m.recipientUser.id IN (:userIdOne, :userIdTwo)"
//        + "  ORDER BY"
//        + "    m.timeSent"
//        + "  DESC")
    public List<ChatMessageEntity> getExistingChatMessages(
       // @Param("userIdOne") 
        String userIdOne,
       // @Param("userIdTwo")
        String userIdTwo, Pageable pageable);
    
    public void submitMessage(ChatMessageEntity chatMessage);
}
