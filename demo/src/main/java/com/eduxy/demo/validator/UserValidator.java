package com.eduxy.demo.validator;

import com.eduxy.demo.model.User;

public class UserValidator {
	public static void validateUser(User user) throws Exception {
		
		if(!validateEmailId(user.getEmailId()))
			throw new Exception("userValidator.INVALID_EMAIL_FORMAT");
		
		if(!validatePhoneNumber(user.getPhoneNumber()))
			throw new Exception("userValidator.INVALID_PHONE_NUMBER");
		
		if(!validateName(user.getName()))
			throw new Exception("userValidator.INVALID_NAME");
		
		if(!validatePassword(user.getPassword()))
			throw new Exception("userValidator.INVALID_PASSWORD_FORMAT");
			
	}
	public static Boolean validateName(String name){
		Boolean flag = false;
		if(!name.matches("[ ]*") && name.matches("([A-Za-z])+(\\s[A-Za-z]+)*"))
			flag=true;
		return flag;
	}

	public static Boolean validatePhoneNumber(String phoneNumber){
		Boolean flag = false;
		if(phoneNumber.matches("[0-9]+") && phoneNumber.length()==10)
			flag=true;
		return flag;
	}


	public static Boolean validateEmailId(String emailId){
		Boolean flag = false;
		if(emailId.matches("[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+"))
			flag = true;
		return flag;
	}
	
	
	
	public static Boolean validatePassword(String password){
		Boolean flag = false;
		if (password.length()>=6 && password.length()<=20)
			if(password.matches(".*[A-Z]+.*"))
				if(password.matches(".*[a-z]+.*"))
					if(password.matches(".*[0-9]+.*"))
						if(password.matches(".*[^a-zA-Z-0-9].*"))
							flag = true;
		return flag;
	}
	

}
