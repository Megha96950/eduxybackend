package com.eduxy.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.eduxy.demo.model.User;

public interface TeacherService {
	
	public Integer uploadIdPhoto(String userEmailId,MultipartFile idPhoto, Integer id)throws Exception;
	public Integer uploadDegree(String userEmailId,MultipartFile degreePhoto, Integer id)throws Exception;
    public void changeName(String newName, String emailId);
    public void changeNumber(String newNumber, String emailId);
    public void changePassword(String newPassword, String emailId);
    
}
