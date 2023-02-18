package com.eduxy.demo.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.eduxy.demo.model.User;

import java.util.Date;

@Entity
@Table(name="chatmessage")
public class ChatMessageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  
  @Column(name="recipient_User_Id")
  private String recipientUserId;
	
  @Column(name="author_User_Id")
  private String authorUserId;

  @NotNull
  @Column(name="time_sent")
  private Date timeSent;

  @NotNull
  @Column(name="contents")
  private String contents;
  
 
 


  @OneToOne()
  @JoinColumn(name = "author_User_Id",insertable = false, updatable = false)
  private UserEntity authorUser;

  @OneToOne()
  @JoinColumn(name = "recipient_User_Id",insertable = false, updatable = false)
  private UserEntity recipientUser;
  
 

//   @OneToOne
//  @JoinColumn(name = "authorUserId")
//  private UserEntity authorUser;
//
//  @OneToOne
//  @JoinColumn(name = "recipientUserId")
//  private UserEntity recipientUser;




  public ChatMessageEntity() {}
  public ChatMessageEntity(String channelID,String user,String user2, String contents) {
	  
	    this.authorUserId = user;
	    this.recipientUserId = user2;
	    this.contents = contents;
	    this.timeSent = new Date();
	  }
	  


public String getRecipientUserId() {
	return recipientUserId;
}
public void setRecipientUserId(String recipientUserId) {
	this.recipientUserId = recipientUserId;
}
public String getAuthorUserId() {
	return authorUserId;
}
public void setAuthorUserId(String authorUserId) {
	this.authorUserId = authorUserId;
}
public void setContents(String contents) {
	this.contents = contents;
}
public ChatMessageEntity(UserEntity user, UserEntity user2, String contents) {
    this.authorUser = user;
    this.recipientUser = user2;
    this.contents = contents;
    this.timeSent = new Date();
  }
  
  public long getId() {
    return this.id;
  }
  
  public UserEntity getAuthorUser() {
    return this.authorUser;
  }
  
  public UserEntity getRecipientUser() {
    return this.recipientUser;
  }

  public void setAuthorUser(UserEntity user) {
    this.recipientUser = user;
  }
  
  public void setRecipientUser(UserEntity user) {
    this.authorUser = user;
  }

  public Date getTimeSent() {
    return this.timeSent;
  }
  
  public String getContents() {
    return this.contents;
  }
  

}
