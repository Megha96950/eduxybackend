package com.eduxy.demo.dao;

import java.util.List;

import com.eduxy.demo.model.Student;

public interface TeacherDAO {

	public Integer uploadIdPhoto(String userEmailId,String idPhoto,Integer id);
	public Integer uploadDegree(String userEmailId,byte[] degreePhoto,Integer id);
	public void updateDescription(Integer id,String newDescription);
	public Integer displayImage(String userEmailId, String displayImage,Integer id);
	public List<Student> searchStudent(String searchString);
}
