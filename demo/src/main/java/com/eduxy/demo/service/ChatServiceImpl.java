package com.eduxy.demo.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.eduxy.demo.entity.ChatMessageEntity;
import com.eduxy.demo.entity.UserEntity;
import com.eduxy.demo.dao.ChatChannelDAO;
import com.eduxy.demo.dao.ChatMessageDAO;
import com.google.common.collect.Lists;
import com.eduxy.demo.model.ChatChannel;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.eduxy.demo.exception.UserNotFoundException;
import com.eduxy.demo.exception.IsSameUserException;
import com.eduxy.demo.model.ChatMessage;
import com.eduxy.demo.model.Notification;
import com.eduxy.demo.model.User;

@Service(value="ChatService")
@Transactional
public class ChatServiceImpl implements ChatService {

	@Autowired
  private ChatChannelDAO chatChannelDAO;
	@Autowired
  private ChatMessageDAO chatMessageDAO;
	@Autowired
  private UserService userService;
  
  private final int MAX_PAGABLE_CHAT_MESSAGES = 100;

  @Autowired
  public ChatServiceImpl(
      ChatChannelDAO chatChannelDAO,
      ChatMessageDAO chatMessageDAO,
      UserService userService) {
    this.chatChannelDAO = chatChannelDAO;
    this.chatMessageDAO = chatMessageDAO;
    this.userService = userService;
  }
 
  @Override
  public String getExistingChannel(ChatChannel chatChannel) {

    List<ChatChannel> channel;
		channel = chatChannelDAO.findExistingChannel(
				ChatChannel.getUserIdOne()
			    ,ChatChannel.getUserIdTwo());
		if(!channel.isEmpty())
		 System.out.println(channel.get(0));
    return (channel != null && !channel.isEmpty()) ? channel.get(0).getUuid() : null;
  }

  @Override
  public String newChatSession(ChatChannel chatChannel)throws BeansException,UserNotFoundException {
	  String uuid= chatChannelDAO.newChatSession(chatChannel);
       return uuid;
  }
  
  @Override
  public String establishChatSession(ChatChannel chatChannel) throws IsSameUserException, BeansException,UserNotFoundException {
    if (chatChannel.getUserIdOne() == chatChannel.getUserIdTwo()) {
      throw new IsSameUserException();
    }

    String uuid = getExistingChannel(chatChannel);

    // If channel doesn't already exist, create a new one
    return (uuid != null) ? uuid : newChatSession(chatChannel);
  }
  
  @Override
  public void submitMessage(ChatMessage chatMessage,String channelId)
      throws BeansException, UserNotFoundException {
	  
    ChatMessageEntity chatMessageEntity =this.mapChatDTOtoMessage(chatMessage);
    chatMessageDAO.submitMessage(chatMessageEntity);
  

    User fromUser = userService.getUser(chatMessageEntity.getAuthorUserId());
    User recipientUser = userService.getUser(chatMessageEntity.getRecipientUserId());
      
    userService.notifyUser(channelId,
      new Notification(
        "ChatMessageNotification",
        fromUser.getName() + " has sent you a message",
        chatMessage.getAuthorUserId()
      )
    );
  }
 @Override
  public List<ChatMessage> getExistingChatMessages(String channelUuid) {
    ChatChannel channel = chatChannelDAO.getChannelDetails(channelUuid);

    List<ChatMessage> chatMessages = 
      chatMessageDAO.getExistingChatMessages(
        channel.getUserIdOne(),
        channel.getUserIdTwo(),
        PageRequest.of(0, MAX_PAGABLE_CHAT_MESSAGES)
      );

    // TODO: fix this
    List<ChatMessage> messagesByLatest = Lists.reverse(chatMessages); 

    return this.mapMessagesToChatDTOs(messagesByLatest);
  }
  
  public  List<ChatMessage> mapMessagesToChatDTOs(List<ChatMessage> chatMessages) {
	    List<ChatMessage> dtos = new ArrayList<ChatMessage>();

	    for(ChatMessage chatMessage : chatMessages) { 
	      dtos.add(
	         new ChatMessage(
	          chatMessage.getContents(),
	          chatMessage.getAuthorUserId(),
	          chatMessage.getRecipientUserId()
	        )
	      );
	    }

	    return dtos;
	  }

	  public  ChatMessageEntity mapChatDTOtoMessage(ChatMessage dto) {
	
		  ChatMessageEntity c=new ChatMessageEntity( dto.getRecipientUserId(),
				  dto.getAuthorUserId(),
	      dto.getContents()
	    );
		System.out.println(c.getAuthorUserId());  
		  return c;
	  }
}
