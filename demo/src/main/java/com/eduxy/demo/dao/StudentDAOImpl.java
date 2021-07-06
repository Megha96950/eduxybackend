package com.eduxy.demo.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eduxy.demo.entity.StudentEntity;


@Repository(value = "studentDAO")
public class StudentDAOImpl implements StudentDAO {
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Integer uploadIdPhoto(String userEmailId, byte[] idPhoto,Integer id) {
	
		
		StudentEntity studentEntity = null;
		Integer dataId = null;
		studentEntity  = entityManager.find(StudentEntity.class, id);
		studentEntity.setIdPhoto(idPhoto);
		entityManager.persist(studentEntity);			
		return id;
		
	}


}
