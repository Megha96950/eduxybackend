package com.eduxy.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eduxy.demo.entity.UserEntity;
import com.eduxy.demo.model.User;





@Repository(value = "userDAO")
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private EntityManager entityManager;
	
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
		UserEntity userEntity = entityManager.find(UserEntity.class, emailId);
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
		if (userEntity!=null){
		    user = new User();
			user.setEmailId(userEntity.getEmailId());
			user.setName(userEntity.getName());
			user.setPhoneNumber(userEntity.getPhoneNumber());
			user.setRole(userEntity.getRole());
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void changePassword(String userEmailId, String newHashedPassword) {
		// TODO Auto-generated method stub
		
	}




	

}
