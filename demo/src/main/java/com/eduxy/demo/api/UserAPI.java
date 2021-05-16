package com.eduxy.demo.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.eduxy.demo.model.User;
import com.eduxy.demo.service.UserService;




@CrossOrigin
@RestController
@RequestMapping("UserAPI")
public class UserAPI {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Environment environment;
	
	static Logger logger = LogManager.getLogger(UserAPI.class.getName());
	
	@PostMapping(value = "userLogin")
	public ResponseEntity<User> authenticateUser(@RequestBody User user) throws Exception {
		try {
			logger.info("USER TRYING TO LOGIN, VALIDATING CREDENTIALS. USER EMAIL ID: "+user.getEmailId());
			
			User userfromDB = userService.authenticateUser(user.getEmailId(), user.getPassword());
			
			logger.info("USER LOGIN SUCCESS, USER EMAIL : "+userfromDB.getEmailId());
			
			return new ResponseEntity<User>(userfromDB, HttpStatus.OK);
		} 
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value = "registerUser")
	public ResponseEntity<String> registerUser(@RequestBody User user) throws Exception {
		try
		{
			logger.info("CUSTOMER TRYING TO REGISTER. CUSTOMER EMAIL ID: "+user.getEmailId());
		//	System.out.println("detiuhkj");
			String registeredWithEmailID = userService.registerNewUser(user);
			
			registeredWithEmailID = environment.getProperty("userAPI.CUSTOMER_REGISTRATION_SUCCESS")+registeredWithEmailID;
			
			return new ResponseEntity<String>(registeredWithEmailID, HttpStatus.OK);
			
		}
		catch (Exception e){
			if(e.getMessage().contains("Validator")){
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, environment.getProperty(e.getMessage()));
			}
			throw new ResponseStatusException(HttpStatus.CONFLICT, environment.getProperty(e.getMessage()));
		}
	}
	
}
