package com.eduxy.demo.entity;

import javax.persistence.*;

import lombok.*;
@Entity
@Table(name = "chatroom")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
}
