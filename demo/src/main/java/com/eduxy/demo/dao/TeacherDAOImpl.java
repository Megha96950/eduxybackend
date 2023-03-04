package com.eduxy.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eduxy.demo.entity.AddressEntity;
import com.eduxy.demo.entity.StudentEntity;
import com.eduxy.demo.entity.TeacherEntity;
import com.eduxy.demo.entity.UserEntity;
import com.eduxy.demo.model.Student;
import com.eduxy.demo.model.Teacher;

@Repository(value = "teacherDAO")
public class TeacherDAOImpl implements TeacherDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Integer uploadIdPhoto(String userEmailId, String idPhoto,Integer id) {
	
		
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
		teacherEntity.setDegreePhoto(degreePhoto);
		entityManager.persist(teacherEntity);			
		return id;
		
	}

	@Override
	public void updateDescription(Integer id, String newDescription) {
		
		TeacherEntity teacherEntity = null;
		teacherEntity  = entityManager.find(TeacherEntity.class, id);
		teacherEntity.setDescription(newDescription);
		entityManager.persist(teacherEntity);	
	}

	@Override
	public Integer displayImage(String userEmailId, String displayImage,Integer id) {
		TeacherEntity teacherEntity = null;
		Integer dataId = null;
		teacherEntity  = entityManager.find(TeacherEntity.class, id);
		teacherEntity.setDisplayImg(displayImage);
		entityManager.persist(teacherEntity);			
		return id;
		
	}
	
	@Override
	public List<Student> searchStudent(String searchString) {
//      FullTextQuery jpaQuery = searchUsersQuery(searchString);
//		
//
//		List<TeacherEntity> teacherEntities = jpaQuery.getResultList();
//	
		
		 List<Student>students=new ArrayList();
		 Query query = entityManager.createQuery("select t from StudentEntity t where t.subjects like  ?1 ");
		 query.setParameter(1,"%"+searchString+"%" );
			
		 List<StudentEntity> studentEntities = query.getResultList();
		 if(studentEntities.isEmpty())
				return null;
		 for(StudentEntity t: studentEntities) {
			 Student student=new Student();
			    student.setStudentId(t.getStudentId());
			    student.setSubjects(t.getSubjects());
			    student.setIdProof(t.getIdProof());
				//teacher.setIdPhoto(i.getIdPhoto());
			    student.setInstituteName(t.getInstituteName());
			    student.setStandard(t.getStandard());
			    student.setSubjects(t.getSubjects());
				//teacher.setDegreePhoto(i.getDegreePhoto());
			    student.setDisplayImg(t.getDisplayImg());
			    student.setName(t.getName());
				students.add(student);
			 
		 }
		return students;
	}


}
