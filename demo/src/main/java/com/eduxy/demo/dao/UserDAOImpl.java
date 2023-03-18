package com.eduxy.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.eduxy.demo.entity.AddressEntity;
import com.eduxy.demo.entity.ChatChannelEntity;
import com.eduxy.demo.entity.StudentEntity;
import com.eduxy.demo.entity.TeacherEntity;
import com.eduxy.demo.entity.UserEntity;
import com.eduxy.demo.model.Address;
import com.eduxy.demo.model.Message;
import com.eduxy.demo.model.Student;
import com.eduxy.demo.model.Teacher;
import com.eduxy.demo.model.User;


@Repository(value = "userDAO")
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public String authenticateUser(String emailId, String password) {
       Query query = entityManager.createQuery("select c from UserEntity c where c.emailId = '"+emailId+"' and c.password = '"+password+"'");
		
		List<UserEntity> userEntities = query.getResultList();
		if(userEntities.isEmpty())
			return null;

		return userEntities.get(0).getEmailId();
	}

    @Override
	public String getPasswordOfUser(String emailId) {
		String password = null;
		emailId = emailId.toLowerCase();
		UserEntity userEntity = (UserEntity)entityManager.find(UserEntity.class, emailId);
		if (userEntity!=null){
			password = userEntity.getPassword();
		}
		
		return password;
	}
    
	@Override
	public User getUserByEmailId(String emailId) {
		User user = null;
	    emailId=emailId.toLowerCase();
	    UserEntity userEntity = entityManager.find(UserEntity.class, emailId);
	    List<Address> userAddresses = new ArrayList<>();
	    List<Student> userStudent = new ArrayList<>();
	    List<Teacher> userTeacher = new ArrayList<>();
	    
		if (userEntity!=null){
		    user = new User();
			user.setEmailId(userEntity.getEmailId());
			user.setName(userEntity.getName());
			user.setPhoneNumber(userEntity.getPhoneNumber());
			user.setRole(userEntity.getRole());
			for (AddressEntity i : userEntity.getAddressEntities()) {
				Address address = new Address();
				address.setAddressId(i.getAddressId());
				address.setAddressLine1(i.getAddressLine1());
				address.setAddressLine2(i.getAddressLine2());
				address.setCity(i.getCity());
				address.setContactNumber(i.getContactNumber());
				address.setPin(i.getPin());
				address.setState(i.getState());
				
				userAddresses.add(address);
			}
			user.setAddresses(userAddresses);
			
			for (StudentEntity i : userEntity.getStudentEntity()) {
				Student student = new Student();
				student.setStudentId(i.getStudentId());
				student.setSubjects(i.getSubjects());
				student.setIdProof(i.getIdProof());
				student.setInstituteName(i.getInstituteName());
				student.setIdPhoto(i.getIdPhoto());
				student.setStandard(i.getStandard());
				student.setFatherName(i.getFatherName());
				student.setMotherName(i.getMotherName());
				student.setDisplayImg(i.getDisplayImg());
			
				userStudent.add(student);
			}
			user.setStudent(userStudent);
			
			for (TeacherEntity i : userEntity.getTeacherEntity()) {
				Teacher teacher =new Teacher();
				teacher.setTeacherId(i.getTeacherId());
				teacher.setSubjects(i.getSubjects());
				teacher.setIdProof(i.getIdProof());
				teacher.setIdPhoto(i.getIdPhoto());
				teacher.setHigherQualification(i.getHigherQualification());
				teacher.setFeesCharged(i.getFeesCharged());
				teacher.setDescription(i.getDescription());
				teacher.setDegreePhoto(i.getDegreePhoto());
				teacher.setDisplayImg(i.getDisplayImg());
				
				userTeacher.add(teacher);
			}
			user.setTeacher(userTeacher);
		}
			
		
		return user;
	}
	@Override
	public String registerNewUser(User user) {
		
		String registeredWithEmailId = null;

		UserEntity userEntity = new UserEntity();

		userEntity.setEmailId(user.getEmailId());
		userEntity.setName(user.getName());
		userEntity.setPassword(user.getPassword());
		userEntity.setPhoneNumber(user.getPhoneNumber());
		userEntity.setRole(user.getRole());
		
		entityManager.persist(userEntity);
		
		registeredWithEmailId = userEntity.getEmailId();
		
		return registeredWithEmailId;
	}
	@Override
	public User getUserByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateProfile(User user) {
		
		UserEntity userEntity = entityManager.find(UserEntity.class, user.getEmailId().toLowerCase());	
		userEntity.setName(user.getName());
		userEntity.setPhoneNumber(user.getPhoneNumber());
		
	}
	
	@Override
	public Boolean checkAvailabilityOfEmailId(String emailId) {
		Boolean flag = false;
		
		UserEntity userEntity = null;

		userEntity = entityManager.find(UserEntity.class, emailId);
	   
		if(userEntity == null)
			flag = true;
		 
		return flag;
	}

	
	@Override
	public Integer addAddress(String userEmailId, Address address) {
		
		UserEntity userEntity = null;
		Integer newAddressId = null;
		
		userEntity = entityManager.find(UserEntity.class, userEmailId);
	
		List<AddressEntity> userAddressEntities = userEntity.getAddressEntities();
		
		AddressEntity newAddress = new AddressEntity();
		newAddress.setAddressLine1(address.getAddressLine1());
		newAddress.setAddressLine2(address.getAddressLine2());
		newAddress.setCity(address.getCity());
		newAddress.setContactNumber(address.getContactNumber());
		newAddress.setPin(address.getPin());
		newAddress.setState(address.getState());
		
		userAddressEntities.add(newAddress);
		userEntity.setAddressEntities(userAddressEntities);
		
		entityManager.persist(userEntity);
		
		
		List<AddressEntity> customerAddressEntitiesAfterAddition = userEntity.getAddressEntities();
		
		AddressEntity addressEntity = customerAddressEntitiesAfterAddition.get(customerAddressEntitiesAfterAddition.size()-1);
		newAddressId = addressEntity.getAddressId();
		return newAddressId;
		
	}
	
	@Override
	public Integer addStudentDetail(String userEmailId, Student student) {
		
		UserEntity userEntity = null;
		Integer newStudentId = null;
		
		userEntity = entityManager.find(UserEntity.class, userEmailId);
		
		List<StudentEntity> userStudentEntities = userEntity.getStudentEntity();
		
		StudentEntity newStudent = new StudentEntity();
		newStudent.setSubjects(student.getSubjects());
		//newStudent.setPhoto(student.getPhoto());
		newStudent.setIdProof(student.getIdProof());
		//System.out.println(student.getPhoto());
		newStudent.setInstituteName(student.getInstituteName());
		newStudent.setStandard(student.getStandard());
		newStudent.setFatherName(student.getFatherName());
		newStudent.setMotherName(student.getMotherName());
		
		userStudentEntities.add(newStudent);
		userEntity.setStudentEntity(userStudentEntities);
		
		entityManager.persist(userEntity);
		
		
		List<StudentEntity> userStudentEntitiesAfterAddition = userEntity.getStudentEntity();
		
		StudentEntity studentEntity = userStudentEntitiesAfterAddition.get(userStudentEntitiesAfterAddition.size()-1);
		newStudentId = studentEntity.getStudentId();
		return newStudentId;
		
	}

	@Override
	public Integer addTeacherDetail(String userEmailId, Teacher teacher) throws Exception {
		UserEntity userEntity = null;
		Integer newTeacherId = null;
		
		userEntity = entityManager.find(UserEntity.class, userEmailId);
		
		List<TeacherEntity> userTeacherEntities = userEntity.getTeacherEntity();
		
		TeacherEntity newTeacher = new TeacherEntity();
		newTeacher.setSubjects(teacher.getSubjects());
		newTeacher.setIdProof(teacher.getIdProof());
		//newTeacher.setIdPhoto(teacher.getIdPhoto().getBytes());
		newTeacher.setHigherQualification(teacher.getHigherQualification());
		newTeacher.setFeesCharged(teacher.getFeesCharged());
		newTeacher.setDescription(teacher.getDescription());
		//newTeacher.setDegreePhoto(degreePhoto.getBytes());
		newTeacher.setName(teacher.getName());
		userTeacherEntities.add(newTeacher);
		userEntity.setTeacherEntity(userTeacherEntities);
		
		entityManager.persist(userEntity);
		
		
		List<TeacherEntity> userTeacherEntitiesAfterAddition = userEntity.getTeacherEntity();
		
		TeacherEntity teacherEntity = userTeacherEntitiesAfterAddition.get(userTeacherEntitiesAfterAddition.size()-1);
		newTeacherId = teacherEntity.getTeacherId();
		return newTeacherId;
	}
	
	@Override
	public void changeName(String newName, String emailId) {
		
		UserEntity userEntity = (UserEntity)entityManager.find(UserEntity.class, emailId);
		userEntity.setName(newName);
		entityManager.persist(userEntity);
		
	}

	@Override
	public void changeNumber(String newNumber, String emailId) {
		UserEntity userEntity = (UserEntity)entityManager.find(UserEntity.class, emailId);
		userEntity.setPhoneNumber(newNumber);;
		entityManager.persist(userEntity);
		
	}

	@Override
	public void changePassword(String newPassword, String emailId) {
		UserEntity userEntity = (UserEntity)entityManager.find(UserEntity.class, emailId);
		userEntity.setPassword(newPassword);;
		entityManager.persist(userEntity);
		
	}

	@Override
	public List<User> getFriendListFor(String Id) {
		 Query query = entityManager.createQuery("select c from ChatChannelEntity c where c.UserIdOne = ?1 or c.UserIdTwo = ?1 ");
		 query.setParameter(1,Id);
		 List<ChatChannelEntity> chatChannelEntity =query.getResultList();
		 List<User> users =new ArrayList();
		 for(ChatChannelEntity c : chatChannelEntity) {
			 User user= new User();
			 if(c.getUserIdOne().equals(Id))
			 user =this.getUserByEmailId(c.getUserIdTwo());
			 else
			 user =this.getUserByEmailId(c.getUserIdOne());
			 users.add(user);
		 }
		 
		 
		return users;
	}

	@Override
	public List<User> getAllUsers() {
		Query query =entityManager.createQuery("Select u from UserEntity u");
		List<UserEntity> userEntities =query.getResultList();
		List<User> users = userEntities.stream()
				.map(u->modelMapper.map(u,User.class))
				.collect(Collectors.toList());
	//	System.out.println("............................................................................" +users.get(0).getName());
 		return users;
	}

}
