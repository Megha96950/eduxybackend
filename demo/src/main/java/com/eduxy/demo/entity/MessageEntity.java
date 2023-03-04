package com.eduxy.demo.entity;

import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "messages")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
}
