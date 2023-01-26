package com.eduxy.demo.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;


import java.util.UUID;

@Entity
@Table(name="chatchannel")
public class ChatChannelEntity {

  @Id
  @NotNull
  private String uuid;
  
  @Column(name="useridone")
  private String UserIdOne;
	

@Column(name="useridtwo")
  private String UserIdTwo;

  @OneToOne()
  @JoinColumn(name = "useridone", nullable=false,insertable = false, updatable = false)
  private UserEntity UserOne;

  @OneToOne()
  @JoinColumn(name = "useridtwo", nullable=false,insertable = false, updatable = false)
  private UserEntity UserTwo;
  
  public ChatChannelEntity(String UserIdOne,String UserIdTwo) {
	    this.uuid = UUID.randomUUID().toString();
	    this.UserIdOne = UserIdOne;
	    this.UserIdTwo = UserIdTwo;
	  }


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


public String getUserIdOne() {
	return UserIdOne;
}


public void setUserIdOne(String userIdOne) {
	UserIdOne = userIdOne;
}


public String getUserIdTwo() {
	return UserIdTwo;
}


public void setUserIdTwo(String userIdTwo) {
	UserIdTwo = userIdTwo;
}
  
}
