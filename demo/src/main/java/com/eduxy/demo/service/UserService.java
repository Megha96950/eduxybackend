package com.eduxy.demo.service;

import com.eduxy.demo.model.User;

public interface UserService {
	public User authenticateUser(String emailId, String password) throws Exception;
	public String registerNewUser(User user) throws Exception ;

	public void updateProfile(User user) throws Exception;

	public void changePassword(String UserEmailId, String currentPassword, String newPassword) throws Exception;
	public String getPasswordOfUser(String emailId) throws Exception;
}
