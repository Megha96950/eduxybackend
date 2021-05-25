package com.eduxy.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.eduxy.demo.entity.AddressEntity;
import com.eduxy.demo.entity.TeacherDataEntity;
import com.eduxy.demo.entity.TeacherEntity;


public class TeacherDAOImpl implements TeacherDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Integer upload(String userEmailId, byte[] idPhoto, byte[] degreePhoto) {
		
		TeacherEntity teacherEntity = null;
		Integer dataId = null;
		
		teacherEntity  = entityManager.find(TeacherEntity.class, userEmailId);
		List<TeacherDataEntity> teacherDataEntities = teacherEntity.getData();
		
		TeacherDataEntity teacherDataEntity =new TeacherDataEntity();
		teacherDataEntity.setDegreePhoto(degreePhoto);
		teacherDataEntity.setIdPhoto(idPhoto);
		
		entityManager.persist(teacherDataEntity);
		
         List<TeacherDataEntity> teacherDataEntitiesAfterAddition = teacherEntity.getData();
		
		TeacherDataEntity teacherDataEntityAfter = teacherDataEntitiesAfterAddition.get(teacherDataEntitiesAfterAddition.size()-1);
		dataId = teacherDataEntityAfter.getId();
	
		return dataId;
		
	}

}
