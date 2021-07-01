package com.eduxy.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eduxy.demo.entity.AddressEntity;

import com.eduxy.demo.entity.TeacherEntity;
import com.eduxy.demo.entity.UserEntity;

@Repository(value = "teacherDAO")
public class TeacherDAOImpl implements TeacherDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Integer uploadIdPhoto(String userEmailId, byte[] idPhoto,Integer id) {
	
		
		TeacherEntity teacherEntity = null;
		Integer dataId = null;
		teacherEntity  = entityManager.find(TeacherEntity.class, id);
		teacherEntity.setIdPhoto(idPhoto);
		entityManager.persist(teacherEntity);			
		return id;
		
	}

	@Override
	public Integer uploadDegree(String userEmailId, byte[] degreePhoto, Integer id) {
	
		TeacherEntity teacherEntity = null;
		Integer dataId = null;
		teacherEntity  = entityManager.find(TeacherEntity.class, id);
		teacherEntity.setIdPhoto(degreePhoto);
		entityManager.persist(teacherEntity);			
		return id;
		
	}

	@Override
	public void changeName(String newName, String emailId) {
		
		UserEntity userEntity = (UserEntity)entityManager.find(UserEntity.class, emailId);
		userEntity.setName(newName);
		entityManager.persist(userEntity);
		
	}

}
