package com.eduxy.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnlineUserDto {
	private String userId;
    private String sessionId;
    private String username;
    private Integer noOfNewMessages;
    private String status;

    
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getNoOfNewMessages() {
		return noOfNewMessages;
	}

	public void setNoOfNewMessages(Integer noOfNewMessages) {
		this.noOfNewMessages = noOfNewMessages;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
    public int hashCode() {
        int prime = 31;
        return prime+ ((userId==null)?0:prime+userId.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        OnlineUserDto user = (OnlineUserDto) obj;
        if(!this.userId.equals(((OnlineUserDto) obj).getUserId())){
            return false;
        }
        else if(!this.sessionId.equals(((OnlineUserDto) obj).sessionId)){
            return false;
        }
        return true;
    }

}
