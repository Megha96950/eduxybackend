package com.eduxy.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eduxy.demo.entity.ChatChannelEntity;
import com.eduxy.demo.model.ChatChannel;

import javax.transaction.Transactional;
import java.util.List;

//@Transactional
//@Repository
public interface  ChatChannelDAO 
//extends CrudRepository<ChatChannelEntity, String> 
{
//	  @Query(" FROM"
//	      + "    ChatChannel c"
//	      + "  WHERE"
//	      + "    c.userOne.id IN (:userOneId, :userTwoId) "
//	      + "  AND"
//	      + "    c.userTwo.id IN (:userOneId, :userTwoId)")
	  public List<ChatChannel> findExistingChannel(
	      //@Param("userOneId")
	      String userOneId,
	     // @Param("userTwoId")
	      String userTwoId);
	  
	  public String newChatSession(ChatChannel chatchannel);
//	  @Query(" SELECT"
//	      + "    uuid"
//	      + "  FROM"
//	      + "    ChatChannel c"
//	      + "  WHERE"
//	      + "    c.userOne.id IN (:userIdOne, :userIdTwo)"
//	      + "  AND"
//	      + "    c.userTwo.id IN (:userIdOne, :userIdTwo)")
//	  public String getChannelUuid(
//	      @Param("userIdOne") long userIdOne, @Param("userIdTwo") long userIdTwo);
//
//	  @Query(" FROM"
//	      + "    ChatChannel c"
//	      + "  WHERE"
//	      + "    c.uuid IS :uuid")
     public ChatChannel getChannelDetails( String uuid);
	

}
