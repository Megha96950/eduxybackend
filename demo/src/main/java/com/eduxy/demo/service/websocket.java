package com.eduxy.demo.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class websocket implements WebSocketMessageBrokerConfigurer{
	@Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
		 registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:4200").withSockJS();
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic");
		registry.setApplicationDestinationPrefixes("/app");
	
		//registry.setApplicationDestinationPrefixes("/app").enableSimpleBroker("/chat");
		registry.setUserDestinationPrefix("/user");
	}

}
