package com.transformedge.apps.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin
@RequestMapping(value="${spring.data.rest.base-path}/redirect_controller")
public class PageRedirectController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/registerPage/MainRegisterPage")
	public ModelAndView registerPage() {
		logger.info("INSIDE PageRedirectController START METHOD registerPage");
		ModelAndView model = new ModelAndView("registerPage/MainRegisterPage");
		return model;
	}
}
