package com.eduxy.demo.model;

import javax.persistence.Column;

import lombok.Builder;
import lombok.Getter;



@Getter
@Builder
public class ChatRoom {
	private Integer id;
    private String chatroomId;
    private String senderId;
    private String recipientId;
}
