package com.eduxy.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eduxy.demo.model.Teacher;
import com.eduxy.demo.model.User;

public interface TeacherService {
	
	public Integer uploadIdPhoto(String userEmailId,MultipartFile idPhoto, Integer id)throws Exception;
	public Integer uploadDegree(String userEmailId,MultipartFile degreePhoto, Integer id)throws Exception;
    public void updateDescription(Integer id, String newDescription);
    public Integer uplaodDisplayImg(String userEmailId, MultipartFile displayImg,Integer id) throws Exception;

  
}
