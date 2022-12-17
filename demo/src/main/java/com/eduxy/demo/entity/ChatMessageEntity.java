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

  @OneToOne
  @JoinColumn(name = "authorUserId")
  private User authorUser;

  @OneToOne
  @JoinColumn(name = "recipientUserId")
  private User recipientUser;

  @NotNull
  private Date timeSent;

  @NotNull
  private String contents;

  public ChatMessageEntity() {}

  public ChatMessageEntity(User user, User user2, String contents) {
    this.authorUser = user;
    this.recipientUser = user2;
    this.contents = contents;
    this.timeSent = new Date();
  }
  
  public long getId() {
    return this.id;
  }
  
  public User getAuthorUser() {
    return this.authorUser;
  }
  
  public User getRecipientUser() {
    return this.recipientUser;
  }

  public void setAuthorUser(User user) {
    this.recipientUser = user;
  }
  
  public void setRecipientUser(User user) {
    this.authorUser = user;
  }

  public Date getTimeSent() {
    return this.timeSent;
  }
  
  public String getContents() {
    return this.contents;
  }
}
