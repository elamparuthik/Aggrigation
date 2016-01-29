package com.kx.web;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kx.web.bean.LoginBean;
import com.kx.web.dao.UserDaoImpl;
import com.kx.web.validator.SignUpValidator;
/**
 * Handles requests for the application home page.
 */
@Controller
public class SignUpController {
	
	private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);
	
	@Autowired
	SignUpValidator validator;
	
	@Autowired
	UserDaoImpl userDaoImpl;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = {"/SignUp"}, method = RequestMethod.GET)   
	public String home(Model model) {
		LoginBean SignUp= new LoginBean();
		model.addAttribute("signUp", SignUp);
		
		return "signUp";
	}
	
	@RequestMapping(value={"/SignUp"}, method = RequestMethod.POST)
	public String submitForm(Model model,@Valid @ModelAttribute("signUp") LoginBean loginBean, BindingResult result, HttpSession session,  Errors errors) throws SQLException {
		String returnVal = "redirect:/Login";
		
		
		if(result.hasErrors()) {
			returnVal = "/signUp";
			System.out.println("hgfcx");			
		}
		else
		{
			String errorStatus = userDaoImpl.InsertSignUpdetails(loginBean);
			
			if(errorStatus != "") {
				errors.reject("300", errorStatus);
				
				returnVal = "/signUp";
			}
			else{
				session.setAttribute("EMAIL_SESSION", loginBean.getEmail());
			}
				
		}
		
		return returnVal;
	}
	
}
