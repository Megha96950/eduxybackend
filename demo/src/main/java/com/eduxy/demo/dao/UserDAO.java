package com.eduxy.demo.dao;

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
	public void changePassword(String userEmailId, String newHashedPassword);
	public Integer addAddress(String userEmailId, Address address);
	public Integer addStudentDetail(String userEmailId, Student student);
	public Integer addTeacherDetail(String userEmailId, Teacher teacher);

}
