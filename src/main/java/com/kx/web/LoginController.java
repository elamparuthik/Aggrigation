package com.kx.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kx.web.bean.LoginBean;
import com.kx.web.validator.LoginValidator;
/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	LoginValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = {"/", "/Login"}, method = RequestMethod.GET)   
	public String home(Model model) {
		LoginBean login= new LoginBean();
		model.addAttribute("login", login);
		
		return "login";
	}
	
	@RequestMapping(value={"/Login"}, method = RequestMethod.POST)
	public String submitForm(@Valid @ModelAttribute("login") LoginBean loginBean, BindingResult result, HttpSession session) {
		String returnVal = "redirect:/Welcome";
		
		
		if(result.hasErrors()) {
			System.out.println("gfdxdd");
			returnVal = "/login";
		}
		else
		{
			session.setAttribute("EMAIL_SESSION", loginBean.getEmail());
			returnVal = "redirect:/Welcome";
		}
		
		return returnVal;
	}
	
}
