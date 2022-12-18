package com.eduxy.demo.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.eduxy.demo.model.User;

import java.util.Date;

@Entity
@Table(name="chatMessage")
public class ChatMessageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  

  
  @NotNull
  private Date timeSent;

  @NotNull
  private String contents;

 
  
  @OneToOne
  @JoinColumn(name = "authorUserId")
  private UserEntity authorUser;

  @OneToOne
  @JoinColumn(name = "recipientUserId")
  private UserEntity recipientUser;




  public ChatMessageEntity() {}

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
