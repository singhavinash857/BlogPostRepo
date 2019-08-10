package com.transformedge.apps.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.transformedge.apps.appconfiguration.Translator;
import com.transformedge.apps.service.EmployeeService;
import com.transformedge.apps.utils.StringUtils;

@Controller
@CrossOrigin
public class UserController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	StringUtils stringUtils;	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/login")
	public String login(Model model,String error, String logout) {
		if (error != null){
			String loginErrorMsg = Translator.toLocale("user.invalid.credentials");
			model.addAttribute("errorMsg", loginErrorMsg);
		}
		if (logout != null){
			String logoutSuccessMsg = Translator.toLocale("user.logout.successfully");
			model.addAttribute("message", logoutSuccessMsg);
		}
		return "/loginPage/MainLoginPage";
	}

	@GetMapping({"/welcome_page"})
	public String welcomePageAfterLogin(Principal principle) {
		return "blogPostPage/MainRegisterPage";
	}
	
	@GetMapping(value="/403")
	public ModelAndView errorPage(){
		return new ModelAndView("loginPage/403");
	}
	
	@GetMapping(value="/resetPassword")
	public ModelAndView resetPassword(ModelAndView modelAndView, @RequestParam("token") String token) {
		logger.info("INSIDE UserController START METHOD resetPassword");
		ModelAndView model = new ModelAndView("loginPage/resetPassword");
		if(stringUtils.isTokenExpired(token)){
			model.addObject("errorMessage", "Oops!  This link has been expired!!");
		}else{
			model.addObject("resetToken", token);
		}
		return model;
	}
}
