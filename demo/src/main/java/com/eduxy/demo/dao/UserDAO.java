package com.eduxy.demo.dao;

import com.eduxy.demo.model.User;

public interface UserDAO {
	public String registerNewUser(User user);
	public String authenticateUser(String emailId, String password);
	public String getPasswordOfUser(String emailId) ;
	public User getUserByEmailId(String emailId);
	public User getUserByPhoneNumber(String phoneNumber);

	public void updateProfile(User user);
	public void changePassword(String userEmailId, String newHashedPassword);
	

}
