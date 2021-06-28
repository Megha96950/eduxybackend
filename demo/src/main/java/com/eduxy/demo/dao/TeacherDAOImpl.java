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
		
		UserEntity userEntity = null;
		TeacherEntity teacherEntity = null;
		Integer dataId = null;
		userEntity = entityManager.find(UserEntity.class, userEmailId);
		teacherEntity  = entityManager.find(TeacherEntity.class, id);
		 //System.out.println("gjkhfkja");
		teacherEntity.setIdPhoto(idPhoto);
		entityManager.persist(teacherEntity);			
		return id;
		
	}

	@Override
	public Integer uploadDegree(String userEmailId, byte[] degreePhoto, Integer id) {
		UserEntity userEntity = null;
		TeacherEntity teacherEntity = null;
		Integer dataId = null;
		userEntity = entityManager.find(UserEntity.class, userEmailId);
		teacherEntity  = entityManager.find(TeacherEntity.class, id);
		 //System.out.println("gjkhfkja");
		teacherEntity.setIdPhoto(degreePhoto);
		entityManager.persist(teacherEntity);			
		return id;
		
	}

}
