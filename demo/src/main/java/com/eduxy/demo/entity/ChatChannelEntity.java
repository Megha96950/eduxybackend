package com.eduxy.demo.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;


import java.util.UUID;

@Entity
@Table(name="chatChannel")
public class ChatChannelEntity {

  @Id
  @NotNull
  private String uuid;
  


  @OneToOne()
  @JoinColumn(name = "UserIdOne")
  private UserEntity UserOne;

  @OneToOne()
  @JoinColumn(name = "UserIdTwo")
  private UserEntity UserTwo;

  public ChatChannelEntity(UserEntity UserOne, UserEntity UserTwo) {
    this.uuid = UUID.randomUUID().toString();
    this.UserOne = UserOne;
    this.UserTwo = UserTwo;
  }

  public ChatChannelEntity() {}

  public void setUserTwo(UserEntity UserEntity) {
    this.UserTwo = UserEntity;
  }

  public void setUserOne(UserEntity UserEntity) {
    this.UserOne = UserEntity;
  }

  public UserEntity getUserOne() {
    return this.UserOne;
  }

  public UserEntity getUserTwo() {
    return this.UserTwo;
  }

  public String getUuid() {
    return this.uuid;
  }
}
