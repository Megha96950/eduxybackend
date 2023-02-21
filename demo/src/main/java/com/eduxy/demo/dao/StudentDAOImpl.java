package com.eduxy.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.eduxy.demo.entity.StudentEntity;
import com.eduxy.demo.entity.TeacherEntity;
import com.eduxy.demo.model.Teacher;



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

	@Override

	public List<Teacher> searchTeacher(String searchString) {
//       FullTextQuery jpaQuery = searchUsersQuery(searchString);
//		
//
//		List<TeacherEntity> teacherEntities = jpaQuery.getResultList();
//	
		
		 List<Teacher> teachers=new ArrayList();
		 Query query = entityManager.createQuery("select t from TeacherEntity t where t.subjects like  ?1 ");
		 query.setParameter(1,"%"+searchString+"%" );
			
		 List<TeacherEntity> teacherEntities = query.getResultList();
		 if(teacherEntities.isEmpty())
				return null;
		 for(TeacherEntity t: teacherEntities) {
			 Teacher teacher=new Teacher();
			    teacher.setTeacherId(t.getTeacherId());
				teacher.setSubjects(t.getSubjects());
				teacher.setEmailId(t.getEmailId());
				teacher.setIdProof(t.getIdProof());
				//teacher.setIdPhoto(i.getIdPhoto());
				teacher.setHigherQualification(t.getHigherQualification());
				teacher.setFeesCharged(t.getFeesCharged());
				teacher.setDescription(t.getDescription());
				//teacher.setDegreePhoto(i.getDegreePhoto());
				teachers.add(teacher);
			 
		 }
		return teachers;
	}

	@Override
	public Integer displayImage(String userEmailId, String displayImage,Integer id) {
		StudentEntity studentEntity = null;
		Integer dataId = null;
		studentEntity  = entityManager.find(StudentEntity.class, id);
		studentEntity.setDisplayImg(displayImage);
		entityManager.persist(studentEntity);			
		return id;
		
	}

//private FullTextQuery searchUsersQuery (String searchText) {
//		
//		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
//				.buildQueryBuilder()
//				.forEntity(TeacherEntity.class)
//				.get();
//				
//		org.apache.lucene.search.Query luceneQuery = queryBuilder
//			.keyword()
//			.wildcard()
//			.onFields("subjects", "description", "feesCharged")
//			.matching(searchText)
//			.createQuery();
//		
//		FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, TeacherEntity.class);
//		System.out.println(jpaQuery);
//		return jpaQuery;
//	}
//	


}
