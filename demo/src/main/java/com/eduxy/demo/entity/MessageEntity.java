package com.eduxy.demo.entity;

import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "messages")
public class MessageEntity {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "message_id")
	    private int messageId;

	    @Column(name="chatroom_id")
	    private String chatroomId;

	    @Column(name="sender_id")
	    private String senderId;

	    @Column(name="recipient_id")
	    private String recipientId;

	    @Column(name="sender_name")
	    private String senderName;

	    @Column(name="recipient_name")
	    private String recipientName;

	    @Column
	    private String content;

	    @Column(name = "created_on",nullable = false)
	    private Date createdOn;

	    @Column(name="status")
	    private String status;

		public String getChatroomId() {
			return chatroomId;
		}

		public void setChatroomId(String chatroomId) {
			this.chatroomId = chatroomId;
		}

		public int getMessageId() {
			return messageId;
		}

		public void setMessageId(int messageId) {
			this.messageId = messageId;
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

		public String getSenderName() {
			return senderName;
		}

		public void setSenderName(String senderName) {
			this.senderName = senderName;
		}

		public String getRecipientName() {
			return recipientName;
		}

		public void setRecipientName(String recipientName) {
			this.recipientName = recipientName;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Date getCreatedOn() {
			return createdOn;
		}

		public void setCreatedOn(Date createdOn) {
			this.createdOn = createdOn;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	    
	    
}
