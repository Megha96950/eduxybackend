package com.eduxy.demo.service;




import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.eduxy.demo.dao.UserDAO;

import com.eduxy.demo.model.Address;
import com.eduxy.demo.model.Notification;
import com.eduxy.demo.model.Student;
import com.eduxy.demo.model.Teacher;
import com.eduxy.demo.model.User;
import com.eduxy.demo.validator.UserValidator;
import org.springframework.messaging.simp.SimpMessagingTemplate;




@Service( value = "userService" )
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	
	 private SimpMessagingTemplate simpMessagingTemplate;
	
	 
	@Override
	public User authenticateUser(String emailId, String password) throws Exception{

		User user = null;
		String userEmailIdFromDAO = userDAO.authenticateUser(emailId.toLowerCase(), password);
		if(userEmailIdFromDAO!=null){
			
				user = userDAO.getUserByEmailId(userEmailIdFromDAO);
		}
		else
			throw new Exception ("UserService.INVALID_CREDENTIALS");
		
		return user;
		
	}
	
	@Override
	public String registerNewUser(User user) throws Exception {
		String registeredWithEmailId = null;
		
		UserValidator.validateUser(user);
	
		Boolean emailAvailable = userDAO.checkAvailabilityOfEmailId(user.getEmailId().toLowerCase());
		
		if(emailAvailable){
	
				String emailIdToDB =user.getEmailId().toLowerCase();

				user.setEmailId(emailIdToDB);

				registeredWithEmailId = userDAO.registerNewUser(user);

			}
	
		else{
			throw new Exception("userService.EMAIL_ID_ALREADY_IN_USE");
		}


		return registeredWithEmailId;
	}
	
	@Override
	public void updateProfile(User user) throws  Exception {
		
		
	}

	@Override
	public void changePassword(String userEmailId, String currentPassword, String newPassword) throws Exception {
		
		Boolean validPassword = UserValidator.validatePassword(newPassword);
		
		if (!validPassword)
			throw new Exception("userService.INVALID_NEW_PASSWORD");
		
		String passwordFromDB = userDAO.getPasswordOfUser(userEmailId);
		
		if(!passwordFromDB.equals(currentPassword))
			throw new Exception("userService.INVALID_CURRENT_PASSWORD");
		
		if(currentPassword.equals(newPassword))
			throw new Exception("userService.OLD_PASSWORD_NEW_PASSWORD_SAME");
		
		userDAO.changePassword(userEmailId, newPassword);

	}

	@Override
	public String getPasswordOfUser(String emailId) throws Exception {
	
	    String s=userDAO.getPasswordOfUser(emailId);
	   
		return s;
	}
	
	@Override
	public Integer addAddress(String userEmailId, Address address) throws Exception {
		
		UserValidator.validateAddress(address);
		
		Integer newAddressID = userDAO.addAddress(userEmailId, address);
		
		return newAddressID; 
	}
	
	@Override
	public Integer addStudentDetail(String userEmailId, Student student) throws Exception{
		//UserValidator.validateAddress(address);
		Integer newStudentID = userDAO.addStudentDetail(userEmailId, student);
		
		return newStudentID; 
		
	}
	
	@Override
	public Integer addTeacherDetail(String userEmailId, Teacher teacher) throws Exception {
		//UserValidator.validateAddress(address);
		User user = null;
		Integer newTeacherID = userDAO.addTeacherDetail(userEmailId, teacher);

		
		return newTeacherID;
		
		
	}
	
	
	@Override
	public void changeName(String newName, String emailId) {
		userDAO.changeName(newName, emailId);

	}
	
	
	@Override
	public void changeNumber(String newNumber, String emailId) {
		userDAO.changeNumber(newNumber, emailId);
		
	}

	@Override
	public void changePassword(String newPassword, String emailId) {
		userDAO.changePassword(newPassword, emailId);
		
	}

	@Override
	public Boolean getUserExist(String emailid) {
	    return null;
	}

	@Override
	public User getUser(String emailid) {
		User user = userDAO.getUserByEmailId(emailid);
		return user;
	}
	
//	private <T> User getUser(T userIdentifier, IUserRetrievalStrategy<T> strategy)
//		      throws UserNotFoundException {
//		    User user = strategy.getUser(userIdentifier);
//
//		    if (user == null) { throw new UserNotFoundException("User not found."); }
//
//		    return user;
//     }
//
//	public User getUser(String userEmail)
//		      throws BeansException, UserNotFoundException {
//		    return this.getUser(userEmail, beanFactory.getBean(UserRetrievalByEmailStrategy.class));
//		  }

	 
	  public void notifyUser(User recipientUser, Notification notification) {
//		    if (this.isPresent(recipientUser)) {
		    	simpMessagingTemplate
		        .convertAndSend("/topic/user.notification." + recipientUser.getEmailId(), notification);
//		    } else {
//		      System.out.println("sending email notification to " + recipientUser.getName());
//		
//		    }
		  }
}
