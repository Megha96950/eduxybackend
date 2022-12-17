package com.eduxy.demo.service;

import org.springframework.beans.BeansException;

import com.eduxy.demo.exception.IsSameUserException;
import com.eduxy.demo.exception.UserNotFoundException;
import com.eduxy.demo.model.ChatChannel;
import com.eduxy.demo.model.ChatMessage;

public interface ChatService {

	public String getExistingChannel(ChatChannel chatChannel);
    public String newChatSession(ChatChannel chatChannel)throws BeansException,UserNotFoundException;
    public String establishChatSession(ChatChannel chatChannel) throws IsSameUserException, BeansException,UserNotFoundException;
    public void submitMessage(ChatMessage chatMessage)throws BeansException, UserNotFoundException ;
}
