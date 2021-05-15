package com.eduxy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduxy.demo.dao.UserDAO;
import com.eduxy.demo.model.User;



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
			throw new Exception ("CustomerService.INVALID_CREDENTIALS");
		
		return user;
		
	}
	
	@Override
	public String registerNewUser(User user) throws Exception {
  return null;
	}
	
	@Override
	public void updateProfile(User user) throws  Exception {
		
		
	}

	@Override
	public void changePassword(String userEmailId, String currentPassword, String newPassword)
			throws Exception {

	}
	

}
