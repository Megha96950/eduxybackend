package com.eduxy.demo.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.eduxy.demo.model.User;

import java.util.UUID;

@Entity
@Table(name="chatChannel")
public class ChatChannelEntity {

  @Id
  @NotNull
  private String uuid;

  @OneToOne
  @JoinColumn(name = "userIdOne")
  private User userOne;

  @OneToOne
  @JoinColumn(name = "userIdTwo")
  private User userTwo;

  public ChatChannelEntity(User User, User userTwo) {
    this.uuid = UUID.randomUUID().toString();
    this.userOne = userOne;
    this.userTwo = userTwo;
  }

  public ChatChannelEntity() {}

  public void setUserTwo(User user) {
    this.userTwo = user;
  }

  public void setUserOne(User user) {
    this.userOne = user;
  }

  public User getUserOne() {
    return this.userOne;
  }

  public User getUserTwo() {
    return this.userTwo;
  }

  public String getUuid() {
    return this.uuid;
  }
}
