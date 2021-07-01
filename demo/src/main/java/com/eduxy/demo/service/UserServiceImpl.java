package com.eduxy.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eduxy.demo.dao.UserDAO;
import com.eduxy.demo.model.Address;
import com.eduxy.demo.model.Student;
import com.eduxy.demo.model.Teacher;
import com.eduxy.demo.model.User;
import com.eduxy.demo.validator.UserValidator;





@Service( value = "userService" )
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public User authenticateUser(String emailId, String password) throws Exception{

		User user = null;
		String userEmailIdFromDAO = userDAO.authenticateUser(emailId.toLowerCase(), password);
		if(userEmailIdFromDAO!=null){
			
				user = userDAO.getUserByEmailId(userEmailIdFromDAO);
		}
		else
			throw new Exception ("UserService.INVALID_CREDENTIALS");
		
		return user;
		
	}
	
	@Override
	public String registerNewUser(User user) throws Exception {
		String registeredWithEmailId = null;
		
		UserValidator.validateUser(user);
	
		Boolean emailAvailable = userDAO.checkAvailabilityOfEmailId(user.getEmailId().toLowerCase());
		
		if(emailAvailable){
	
				String emailIdToDB =user.getEmailId().toLowerCase();

				user.setEmailId(emailIdToDB);

				registeredWithEmailId = userDAO.registerNewUser(user);

			}
	
		else{
			throw new Exception("userService.EMAIL_ID_ALREADY_IN_USE");
		}


		return registeredWithEmailId;
	}
	
	@Override
	public void updateProfile(User user) throws  Exception {
		
		
	}

	@Override
	public void changePassword(String userEmailId, String currentPassword, String newPassword) throws Exception {
		
		Boolean validPassword = UserValidator.validatePassword(newPassword);
		
		if (!validPassword)
			throw new Exception("userService.INVALID_NEW_PASSWORD");
		
		String passwordFromDB = userDAO.getPasswordOfUser(userEmailId);
		
		if(!passwordFromDB.equals(currentPassword))
			throw new Exception("userService.INVALID_CURRENT_PASSWORD");
		
		if(currentPassword.equals(newPassword))
			throw new Exception("userService.OLD_PASSWORD_NEW_PASSWORD_SAME");
		
		userDAO.changePassword(userEmailId, newPassword);

	}

	@Override
	public String getPasswordOfUser(String emailId) throws Exception {
	
	    String s=userDAO.getPasswordOfUser(emailId);
	   
		return s;
	}
	
	@Override
	public Integer addAddress(String userEmailId, Address address) throws Exception {
		
		UserValidator.validateAddress(address);
		
		Integer newAddressID = userDAO.addAddress(userEmailId, address);
		
		return newAddressID; 
	}
	
	@Override
	public Integer addStudentDetail(String userEmailId, Student student) throws Exception{
		//UserValidator.validateAddress(address);
		Integer newStudentID = userDAO.addStudentDetail(userEmailId, student);
		
		return newStudentID; 
		
	}
	
	@Override
	public Integer addTeacherDetail(String userEmailId, Teacher teacher) throws Exception {
		//UserValidator.validateAddress(address);
		User user = null;
		Integer newTeacherID = userDAO.addTeacherDetail(userEmailId, teacher);

		
		return newTeacherID;
		
		
	}
	

}
