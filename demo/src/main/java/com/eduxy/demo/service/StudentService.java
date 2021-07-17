package com.eduxy.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eduxy.demo.model.Teacher;

public interface StudentService {
	public Integer uploadIdPhoto(String userEmailId, MultipartFile idPhoto, Integer id) throws Exception;
	  public List<Teacher> searchTeacher(String Keyword);

}
