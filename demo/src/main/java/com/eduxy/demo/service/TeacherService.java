package com.eduxy.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface TeacherService {
	
	public void upload(MultipartFile idPhoto,MultipartFile degreePhoto)throws Exception;

}
