package com.eduxy.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface TeacherService {
	
	public Integer upload(String userEmailId,MultipartFile idPhoto)throws Exception;

}
