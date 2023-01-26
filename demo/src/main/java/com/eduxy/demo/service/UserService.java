package com.eduxy.demo.service;



import java.util.List;

import com.eduxy.demo.model.Address;
import com.eduxy.demo.model.Notification;
import com.eduxy.demo.model.Student;
import com.eduxy.demo.model.Teacher;
import com.eduxy.demo.model.User;


public interface UserService {
	public User authenticateUser(String emailId, String password) throws Exception;
	public String registerNewUser(User user) throws Exception ;

	public void updateProfile(User user) throws Exception;

	public void changePassword(String UserEmailId, String currentPassword, String newPassword) throws Exception;
	public String getPasswordOfUser(String emailId) throws Exception;
	public List<User> getFriendListFor(String Id);
	public Integer addAddress(String customerEmailId, Address address) throws Exception;
	public Integer addStudentDetail(String customerEmailId, Student student) throws Exception;
	public Integer addTeacherDetail(String userEmailId, Teacher teacher)throws Exception;
	
	public void changeName(String newName, String emailId);
	public void changeNumber(String newNumber, String emailId);
	public void changePassword(String newPassword, String emailId);
	
	public User getUser(String emailid);
	public Boolean getUserExist(String emailid);
	public void notifyUser(String channelId, Notification notification);
	    
}
