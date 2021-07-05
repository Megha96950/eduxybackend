package com.eduxy.demo.dao;



public interface TeacherDAO {

	public Integer uploadIdPhoto(String userEmailId,byte[] idPhoto,Integer id);
	public Integer uploadDegree(String userEmailId,byte[] degreePhoto,Integer id);
	public void updateDescription(Integer id,String newDescription);
}
