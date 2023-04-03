package com.eduxy.demo.service;

import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.eduxy.demo.dao.UserDAO;
import com.eduxy.demo.entity.UserEntity;
import com.eduxy.demo.model.OnlineUserDto;
import com.eduxy.demo.model.User;

import lombok.Data;

@Component
@Data
public class WebSocketEventListener {
	   private Set<OnlineUserDto> onlineUsrs;

	   

		@Autowired
	    private SimpMessageSendingOperations messagingTemplate;

	    @Autowired
	    private UserService userService;
	    
	    @Autowired
	    private ModelMapper modelMapper;
	    

	    @EventListener
	    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
	    	  System.out.print(event);
            System.out.println("GHUigjLGGDHGSYAtdghhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
	        StompHeaderAccessor stompAccessor = StompHeaderAccessor.wrap(event.getMessage());
	        @SuppressWarnings("rawtypes")
	        GenericMessage connectHeader = (GenericMessage) stompAccessor
	                .getHeader(SimpMessageHeaderAccessor.CONNECT_MESSAGE_HEADER);
	        // to the server
	        @SuppressWarnings("unchecked")
	        Map<String, List<String>> nativeHeaders = (Map<String, List<String>>) connectHeader.getHeaders()
	                .get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
            System.out.print(nativeHeaders.get(0));
	        String login = nativeHeaders.get("username").get(0);
	       
	        String sessionId = stompAccessor.getSessionId();
	        if(this.onlineUsrs==null){
	            this.onlineUsrs = new HashSet<>();
	        }
	        User usr = userService.getUser(login);
	        if(usr!=null){
	            OnlineUserDto onl = modelMapper.map(usr, OnlineUserDto.class);
	            onl.setSessionId(sessionId);
	            this.onlineUsrs.add(onl);
	        }
	    }

	    @EventListener
	    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
	        StompHeaderAccessor stompAccessor = StompHeaderAccessor.wrap(event.getMessage());
	        String sessionId = stompAccessor.getSessionId();
	        OnlineUserDto offlineUsr = this.onlineUsrs
	                .stream()
	                .filter((a)->a.getSessionId().equals(sessionId))
	                .collect(Collectors.toList()).get(0);
	        this.onlineUsrs.remove(offlineUsr);
	    }
	    
	    public Set<OnlineUserDto> getOnlineUsrs() {
			return onlineUsrs;
		}

		public void setOnlineUsrs(Set<OnlineUserDto> onlineUsrs) {
			this.onlineUsrs = onlineUsrs;
		}

}
