package com.kx.web.validator;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.kx.web.bean.LoginBean;
import com.kx.web.bean.URLAggrigateBean;
import com.kx.web.dao.UserDaoImpl;

@Component("aggrigationValidator")
public class AggrigationValidator implements Validator{

	//@Autowired
	//private UserDaoImpl userDaoImpl;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return URLAggrigateBean.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		URLAggrigateBean urlAggrigateBean = (URLAggrigateBean) target;
		
		ValidationUtils.rejectIfEmpty(errors, "title", "300", "title is empty");
		ValidationUtils.rejectIfEmpty(errors, "description", "300", "description is empty");
		ValidationUtils.rejectIfEmpty(errors, "url", "300", "url is empty");
		
		
	}
	

}
