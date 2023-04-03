package com.eduxy.demo.api;

import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduxy.demo.exception.InternalException;
import com.eduxy.demo.model.OnlineUserDto;
import com.eduxy.demo.model.User;
import com.eduxy.demo.service.MessageService;
import com.eduxy.demo.service.UserService;
//import com.eduxy.demo.service.WebSocketEventListener;
import com.eduxy.demo.service.WebSocketEventListener;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/ws/users")
public class UserWSAPI {

	 @Autowired
	    WebSocketEventListener webSocketEventListener;

	    @Autowired
	    private MessageService messageService;

	    @Autowired
	    private UserService userService;

	    @Autowired
	    private ModelMapper modelMapper;
	    
	    @GetMapping("/{currentUserId}")
	    public ResponseEntity<List<OnlineUserDto>> getOnlineUsers(@PathVariable String currentUserId) {
	    	
	        List<OnlineUserDto>usersWithStatus = new ArrayList<>();
            List<User> users =userService.getAllUsers();
	        List<OnlineUserDto>offlineUsers = users.stream().map(u->modelMapper.map(u,OnlineUserDto.class)).collect(Collectors.toList());
	        offlineUsers.stream().map(u->{
	            u.setStatus("OFFLINE");
	            return u;
	        }).collect(Collectors.toList());
	       
	        try{
	            Set<OnlineUserDto>onlsSet = webSocketEventListener.getOnlineUsrs();
	            if(onlsSet!=null){
	            	
	                List<OnlineUserDto>onls = onlsSet.stream().collect(Collectors.toList());
	                onls.forEach(o->{
	                	System.out.println("gjHGFUOYFOIEJFLKJEA"+ onls.get(0).getName());
	                    int count = messageService.countNewMessagesFromOnlineUser(currentUserId, o.getEmailId());
	                    System.out.println("gjHGFUOYFOIEJFLKJEAhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
	                    o.setNoOfNewMessages(count);
	                    o.setStatus("ONLINE");
	                });
	                usersWithStatus.addAll(onls);
	                List<OnlineUserDto> finalOnls = onls;
	                offlineUsers.forEach(u->{
	                    if(finalOnls.stream().map(OnlineUserDto::getName).collect(Collectors.toList()).contains(u.getName())==false){
	                        usersWithStatus.add(u);
	                    }
	                });
	            }
	            else{
	                usersWithStatus.addAll(offlineUsers);
	            }

	        }
	        catch(Exception ex){
	            throw new InternalException("Cannot get the number of online users");
	        }
	        return ResponseEntity.ok(usersWithStatus);
	       // return ResponseEntity.ok(offlineUsers);
	    //	return null;
    }
}
