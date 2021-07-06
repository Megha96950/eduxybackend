package com.eduxy.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface StudentService {
	public Integer uploadIdPhoto(String userEmailId, MultipartFile idPhoto, Integer id) throws Exception;

}
