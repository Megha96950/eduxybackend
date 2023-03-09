package com.eduxy.demo.model;

import javax.persistence.Column;

import lombok.Builder;
import lombok.Getter;

public class ChatRoom {
	private Integer id;
    private String chatroomId;
    private String senderId;
    private String recipientId;
	public String getChatroomId() {
		return chatroomId;
	}
	public void setChatroomId(String chatroomId) {
		this.chatroomId = chatroomId;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}
    
    
}
