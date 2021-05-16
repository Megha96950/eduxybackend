package com.eduxy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduxy.demo.dao.UserDAO;
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
	public void changePassword(String userEmailId, String currentPassword, String newPassword)
			throws Exception {

	}

	@Override
	public String getPasswordOfUser(String emailId) throws Exception {
	
	    String s=userDAO.getPasswordOfUser(emailId);
	   
		return s;
	}
	

}
