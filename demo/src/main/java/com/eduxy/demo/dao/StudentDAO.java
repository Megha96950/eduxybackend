package com.eduxy.demo.dao;

import java.util.List;


import com.eduxy.demo.model.Teacher;

public interface StudentDAO    {
	public Integer uploadIdPhoto(String userEmailId, byte[] idPhoto,Integer id);
	
	public List<Teacher> searchTeacher(String searchString);
}
