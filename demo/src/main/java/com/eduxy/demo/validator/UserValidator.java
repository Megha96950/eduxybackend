package com.eduxy.demo.validator;

import com.eduxy.demo.model.Address;
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
	
	public static void validateAddress(Address address) throws Exception{
		
		if ( ! isValidAddressLine1(address.getAddressLine1()) )
			throw new Exception("userValidator.INVALID_ADDRESS_LINE_1");
		
		if ( ! validateCity(address.getCity()) )
			throw new Exception("userValidator.INVALID_CITY");
		
		if ( ! validateState(address.getState() ) )
			throw new Exception("userValidator.INVALID_STATE");
		
		if (! validateContactNumber(address.getContactNumber()))
			throw new Exception("userValidator.INVALID_CONTACT_NUMBER");
		
		if (! validatePIN(address.getPin()))
			throw new Exception("userValidator.INVALID_PIN");
		

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
	
	
	public static Boolean validateCity(String city){
		Boolean flag = false;
		if( ! city.matches("[ ]*"))
			flag=true;
		return flag;
		
	}
	
	public static Boolean validateState(String state){
		Boolean flag = false;
		if(! state.matches("[ ]*"))
			flag=true;
		return flag;
		
	}
	
	public static Boolean isValidAddressLine1(String addressLine1){
		Boolean flag = false;
		if(! addressLine1.matches("[ ]*"))
			flag=true;
		return flag;
		
	}
	

	public static Boolean validateContactNumber(String phoneNumber){

		Boolean flag = false;
		if(phoneNumber.matches("[0-9]+") && phoneNumber.length()==10)
			flag=true;
		return flag;

	}

	public static Boolean validatePIN(String pin){

		Boolean flag = false;
		if(pin.matches("[A-Za-z0-9]{6,10}"))
			flag=true;
		return flag;

	} 

}
