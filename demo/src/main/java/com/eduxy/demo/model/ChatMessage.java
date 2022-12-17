package com.eduxy.demo.model;

import java.util.Date;


public class ChatMessage {
  
	 private long id;
	 private String authorUser;
	 private String recipientUser;
     private Date timeSent;
	 private String contents;
     public ChatMessage() {}

	  public ChatMessage(String authorUser, String recipientUser, String contents) {
	    this.authorUser = authorUser;
	    this.recipientUser = recipientUser;
	    this.contents = contents;
	   
	  }
	  
	  public long getId() {
	    return this.id;
	  }
	  
	  public String getAuthorUser() {
	    return this.authorUser;
	  }
	  
	  public String getRecipientUser() {
	    return this.recipientUser;
	  }

	  public void setAuthorUser(String user) {
	    this.recipientUser = user;
	  }
	  
	  public void setRecipientUser(String user) {
	    this.authorUser = user;
	  }

	  public Date getTimeSent() {
	    return this.timeSent;
	  }
	  
	  public String getContents() {
	    return this.contents;
	  }
  
}
