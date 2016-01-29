package com.kx.web.validator;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.kx.web.bean.LoginBean;
import com.kx.web.dao.UserDaoImpl;

@Component("signupValidator")
public class SignUpValidator implements Validator{

	@Autowired
	private UserDaoImpl userDaoImpl;
	
	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LoginBean.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		LoginBean loginBean = (LoginBean) target;
		
		ValidationUtils.rejectIfEmpty(errors, "name", "300", "name is empty");
		ValidationUtils.rejectIfEmpty(errors, "email", "300", "email is empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "300", "password is empty");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "300", "confitm password is empty");
		
		if (!loginBean.getPassword().equals(loginBean.getConfirmPassword())) {
			errors.reject("300", "password does not match with confirm password");
		}
		
		//System.out.println(loginBean.getEmail() + "------" + loginBean.getPassword());
		boolean isValidEmail = emailValidator.valid(loginBean.getEmail());
		if(!isValidEmail){
			errors.reject("300", "invalid email");
		}
		
		
		try {
			String strErrorMessage = userDaoImpl.ChkSignUpEmail(loginBean.getEmail());
			System.out.println(strErrorMessage);
			if(strErrorMessage != null)errors.reject("300", strErrorMessage);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
		}
		
	}
	

}
