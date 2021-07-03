package com.eduxy.demo.dao;



public interface TeacherDAO {

	public Integer uploadIdPhoto(String userEmailId,byte[] idPhoto,Integer id);
	public Integer uploadDegree(String userEmailId,byte[] degreePhoto,Integer id);
	public void changeName(String newName , String emailId);
	public void changeNumber(String newNumber , String emailId);
	public void changePassword(String newPassword , String emailId);
}
