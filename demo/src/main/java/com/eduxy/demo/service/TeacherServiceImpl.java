package com.eduxy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.eduxy.demo.dao.TeacherDAO;

@Service( value = "teacherService" )
@Transactional
public class TeacherServiceImpl implements TeacherService{
    
	@Autowired
	TeacherDAO teacherDAO;
	
	@Override
	public Integer upload(String userEmailId, MultipartFile idPhoto) throws Exception {
		
        Integer dataID = teacherDAO.upload(userEmailId , idPhoto.getBytes()) ;
       
		return dataID;
		
	}

	

}
