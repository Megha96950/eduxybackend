package com.eduxy.demo.model;

public class ChatChannel {
	  private static  String userIdOne;

	  private static String userIdTwo;
	  private static String uuid;

	  public static void setUuid(String uuid) {
		ChatChannel.uuid = uuid;
	}

	public static String getUuid() {
		return uuid;
	}

	public ChatChannel() {}

	  public void setUserIdOne(String userIdOne) {
	    this.userIdOne = userIdOne;
	  }

	  public void setUserIdTwo(String userIdTwo) {
	    this.userIdTwo = userIdTwo;
	  }

	  public static   String getUserIdOne() {
	    return userIdOne;
	  }

	  public static  String getUserIdTwo() {
	    return userIdTwo;
	  }
	}