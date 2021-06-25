package com.eduxy.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.eduxy.demo.model.Address;
import com.eduxy.demo.model.Student;
import com.eduxy.demo.model.Teacher;
import com.eduxy.demo.model.User;

public interface UserService {
	public User authenticateUser(String emailId, String password) throws Exception;
	public String registerNewUser(User user) throws Exception ;

	public void updateProfile(User user) throws Exception;

	public void changePassword(String UserEmailId, String currentPassword, String newPassword) throws Exception;
	public String getPasswordOfUser(String emailId) throws Exception;
	
	public Integer addAddress(String customerEmailId, Address address) throws Exception;
	public Integer addStudentDetail(String customerEmailId, Student student) throws Exception;
	public Integer addTeacherDetail(String userEmailId, Teacher teacher)throws Exception;
}
