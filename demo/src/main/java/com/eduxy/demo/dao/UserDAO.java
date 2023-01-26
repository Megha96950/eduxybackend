package com.eduxy.demo.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eduxy.demo.model.Address;
import com.eduxy.demo.model.Student;
import com.eduxy.demo.model.Teacher;
import com.eduxy.demo.model.User;

public interface UserDAO {
	public Boolean checkAvailabilityOfEmailId(String emailId);
	
	public String registerNewUser(User user);
	public String authenticateUser(String emailId, String password);
	public String getPasswordOfUser(String emailId) ;
	public User getUserByEmailId(String emailId);
	public User getUserByPhoneNumber(String phoneNumber);

	public void updateProfile(User user);
	
	public Integer addAddress(String userEmailId, Address address);
	public Integer addStudentDetail(String userEmailId, Student student);
	public Integer addTeacherDetail(String userEmailId, Teacher teacher)throws Exception;

	
	public void changeName(String newName , String emailId);
	public void changeNumber(String newNumber , String emailId);
	public void changePassword(String newPassword , String emailId);
	public List<User> getFriendListFor(String Id);
}
