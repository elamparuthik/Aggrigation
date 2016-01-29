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
import com.kx.web.bean.URLAggrigateBean;
import com.kx.web.dao.URLAggrigateDao;
import com.kx.web.dao.UserDaoImpl;
import com.kx.web.validator.AggrigationValidator;


/**
 * Handles requests for the application home page.
 */
@Controller
public class WelcomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	
	@Autowired
	AggrigationValidator validator;
	
	@Autowired
	UserDaoImpl userDaoImpl;
	
	@Autowired
	URLAggrigateDao urlAggrigateDao;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = {"/Welcome"}, method = RequestMethod.GET)   
	public String home(Model model, HttpSession session) throws SQLException {
		URLAggrigateBean aggriBean= new URLAggrigateBean();
		model.addAttribute("aggriBean", aggriBean);
		String strEmailAddress = (String) session.getAttribute("EMAIL_SESSION");
		
		if(strEmailAddress == "" || strEmailAddress == null) {
			return "redirect:/Login";
		}
		else{
			model.addAttribute("Aggri", urlAggrigateDao.GetURLAggrigateDetails(strEmailAddress));
		}
		
		
		return "welcome";
	}
	
	@RequestMapping(value={"/Welcome"}, method = RequestMethod.POST)
	public String submitForm(Model model,@Valid @ModelAttribute("aggriBean") URLAggrigateBean urlAggrigateBean, BindingResult result, HttpSession session,  Errors errors) throws SQLException {
		String returnVal = "/welcome";
		String strEmailAddress = (String) session.getAttribute("EMAIL_SESSION");
		
		if(strEmailAddress == "" || strEmailAddress == null) {
			return "redirect:/Login";
		}
		else{
			model.addAttribute("Aggri", urlAggrigateDao.GetURLAggrigateDetails(strEmailAddress));
		}
		
		if(result.hasErrors()) {
			returnVal = "/welcome";
		}
		else
		{
			String errorStatus = urlAggrigateDao.InsertURLdetails(urlAggrigateBean, strEmailAddress);
			
			if(errorStatus != "" ) {
				errors.reject("300", errorStatus);
				returnVal = "/welcome";
			}
			else{
				
				return "/welcome";
			}
				
		}
		
		return returnVal;
	}
	
}
