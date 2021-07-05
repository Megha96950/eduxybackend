package com.eduxy.demo.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.eduxy.demo.model.Address;
import com.eduxy.demo.model.Student;
import com.eduxy.demo.model.Teacher;
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
	
	@PostMapping(value = "changePassword")
	public ResponseEntity<String> changePassword(@RequestBody User user) throws Exception {

		try
		{
			userService.changePassword(user.getEmailId(), user.getPassword(), user.getNewPassword());
			String modificationSuccessMsg = environment.getProperty("userAPI.CUSTOMER_PASSWORD_CHANGE_SUCCESS");
			return new ResponseEntity<String>(modificationSuccessMsg, HttpStatus.OK);
			
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, environment.getProperty(e.getMessage()));
		}

	}
	
	@PostMapping(value = "addNewAddress/{userEmailId:.+}")
	public ResponseEntity<String> addNewAddress(@RequestBody Address address, @PathVariable("userEmailId") String customerEmailId) throws Exception {
		int addressId;
		
		try
		{
			addressId = userService.addAddress(customerEmailId,address);
			String message=environment.getProperty("userAPI.NEW_SHIPPING_ADDRESS_ADDED_SUCCESS");
			String toReturn = message+addressId;
			
			toReturn = toReturn.trim();
			return new ResponseEntity<String>(toReturn, HttpStatus.OK);
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		  }	
		}
	@PostMapping(value = "addStudent/{userEmailId:.+}")
	public ResponseEntity<String> addStudentDetail(@RequestBody Student student, @PathVariable("userEmailId") String userEmailId) throws Exception {
		int studentId;
		
		try
		{
			studentId = userService.addStudentDetail(userEmailId,student);
			String message=environment.getProperty("userAPI.STUDENT_ADDED_SUCCESS");
			String toReturn = message+studentId;
			
			toReturn = toReturn.trim();
			return new ResponseEntity<String>(toReturn, HttpStatus.OK);
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		  }	
		}
	
	@PostMapping(value = "addTeacher/{userEmailId:.+}")
	public ResponseEntity<String> addTeacherDetail(@RequestBody Teacher teacher, @PathVariable("userEmailId") String userEmailId ) throws Exception {
		
		System.out.print("fygjhgj");
		try
		{
		
			Integer teacherID= userService.addTeacherDetail(userEmailId,teacher);
			String message=environment.getProperty("userAPI.TEACHER_ADDED_SUCCESS");
			String toReturn = message+teacherID;
			return new ResponseEntity<String>(toReturn, HttpStatus.OK);
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		  }	
		}
	
	 @PostMapping("/updateName/{userEmailId:.+}")
	 
	 public ResponseEntity<String> updateName(@PathVariable("userEmailId")String userEmailId ,@RequestBody String newName) throws Exception {
	  System.out.println("fjhag");
		
			try
			{
				
	           System.out.println(newName);
				userService.changeNumber(newName,userEmailId);
			    String message=environment.getProperty("teacherAPI.TEACHER_NAME_CHANGE");
			    return new ResponseEntity<String>(message, HttpStatus.OK);
			}
			catch (Exception e) {
				
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
			  }	 
		

	 }
	 
	 @PostMapping("/updateNumber/{userEmailId:.+}")
	 
	 public ResponseEntity<String> updateNumber(@PathVariable("userEmailId")String userEmailId ,@RequestBody String newNumber) throws Exception {
	 
		
			try
			{
			
				userService.changeName(newNumber,userEmailId);
			    String message=environment.getProperty("teacherAPI.TEACHER_Number_CHANGE");
			    return new ResponseEntity<String>(message, HttpStatus.OK);
			}
			catch (Exception e) {
				
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
			  }	 
		

	 }
	 
	 @PostMapping("/updatePassword/{userEmailId:.+}")
	 
	 public ResponseEntity<String> updatePassword(@PathVariable("userEmailId")String userEmailId ,@RequestBody String newPassword) throws Exception {
	 
		
			try
			{
			
				userService.changePassword(newPassword,userEmailId);
			    String message=environment.getProperty("teacherAPI.TEACHER_PASSWORD_CHANGE");
			    return new ResponseEntity<String>(message, HttpStatus.OK);
			}
			catch (Exception e) {
				
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
			  }	 
		

	 }
	 
	 
	
	
}
