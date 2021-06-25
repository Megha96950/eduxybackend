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
	public Integer upload(String userEmailId, byte[] idPhoto) {
		
		UserEntity userEntity = null;
		TeacherEntity teacherEntity = null;
		Integer dataId = null;
		userEntity = entityManager.find(UserEntity.class, userEmailId);
		teacherEntity  = entityManager.find(TeacherEntity.class, 2);
		 System.out.println("gjkhfkja");
		//List<TeacherEntity> teacherDataEntities = teacherEntity.getData();
	
		//TeacherEntity teacherDataEntity =new TeacherDataEntity();
		//teacherDataEntity.setDegreePhoto(degreePhoto);
		teacherEntity.setIdPhoto(idPhoto);
		
		entityManager.persist(teacherEntity);
		
//         List<TeacherDataEntity> teacherDataEntitiesAfterAddition = teacherEntity.getData();
//		
//		TeacherDataEntity teacherDataEntityAfter = teacherDataEntitiesAfterAddition.get(teacherDataEntitiesAfterAddition.size()-1);
//		dataId = teacherDataEntityAfter.getId();
	
		return 2;
		
	}

}
