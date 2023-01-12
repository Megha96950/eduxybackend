package com.eduxy.demo.model;

import java.util.Date;


public class ChatMessage {
  
	 private long id;
	 private String authorUserId;
	 private String recipientUserId;
     private Date timeSent;
	 private String contents;
     public ChatMessage() {}

	  public ChatMessage(String authorUser, String recipientUser, String contents) {
	    this.authorUserId = authorUser;
	    this.recipientUserId = recipientUser;
	    this.contents = contents;
	   
	  }
	  
	  public long getId() {
	    return this.id;
	  }
	  
	  public void setTimeSent(Date timeSent) {
		this.timeSent = timeSent;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getAuthorUserId() {
	    return this.authorUserId;
	  }
	  
	  public String getRecipientUserId() {
	    return this.recipientUserId;
	  }

	  public void setAuthorUserId(String user) {
	    this.recipientUserId = user;
	  }
	  
	  public void setRecipientUserId(String user) {
	    this.authorUserId = user;
	  }

	  public Date getTimeSent() {
	    return this.timeSent;
	  }
	  
	  public String getContents() {
	    return this.contents;
	  }
  
}
