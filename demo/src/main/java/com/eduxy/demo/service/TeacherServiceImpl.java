package com.eduxy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.eduxy.demo.dao.TeacherDAO;

public class TeacherServiceImpl implements TeacherService{
    
	@Autowired
	TeacherDAO teacherDAO;
	
	@Override
	public Integer upload(String userEmailId, MultipartFile idPhoto, MultipartFile degreePhoto) throws Exception {
		
        Integer dataID = teacherDAO.upload(userEmailId , idPhoto.getBytes(), degreePhoto.getBytes()) ;
		return dataID;
		
	}

	

}
