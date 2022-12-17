package com.eduxy.demo.dao;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eduxy.demo.entity.ChatMessageEntity;





@Transactional
@Repository
public interface ChatMessageDAO extends CrudRepository<ChatMessageEntity, String> {
    @Query(" FROM"
        + "    ChatMessage m"
        + "  WHERE"
        + "    m.authorUser.id IN (:userIdOne, :userIdTwo)"
        + "  AND"
        + "    m.recipientUser.id IN (:userIdOne, :userIdTwo)"
        + "  ORDER BY"
        + "    m.timeSent"
        + "  DESC")
    public List<ChatMessageEntity> getExistingChatMessages(
        @Param("userIdOne") String userIdOne, @Param("userIdTwo") String userIdTwo, Pageable pageable);
}
