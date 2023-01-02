package com.eduxy.demo.model;


public class EstablishedChatConnection {
  private String channelUuid;
  
  private String userOneFullName;
  
  private String userTwoFullName;

  public String getUserTwoFullName() {
	return userTwoFullName;
}

public void setUserTwoFullName(String userTwoFullName) {
	this.userTwoFullName = userTwoFullName;
}

public String getUserOneFullName() {
	return userOneFullName;
}

public EstablishedChatConnection() {}
  
  public EstablishedChatConnection(String channelUuid, String userOneFullName, String userTwoFullName) {
    this.channelUuid = channelUuid;
    this.userOneFullName = userOneFullName;
    this.userTwoFullName = userTwoFullName;
  }
  
  public void setChannelUuid(String channelUuid) {
    this.channelUuid = channelUuid;
  }
  
  public String getChannelUuid() {
    return this.channelUuid;
  }
  
  public void setUserOneFullName(String userOneFullName) {
    this.userOneFullName = userOneFullName;
  }
  }