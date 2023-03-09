package com.eduxy.demo.entity;

import javax.persistence.*;

import lombok.*;
@Entity
@Table(name = "chatroom")
public class ChatRoomEntity {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @Column(name = "chatroom_id")
	    private String chatroomId;

	    @Column(name="sender_id")
	    private String senderId;

	    @Column(name="recipient_id")
	    private String recipientId;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

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
