package com.transformedge.apps.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transformedge.apps.appconfiguration.CsvConfiguration;
import com.transformedge.apps.appconfiguration.Translator;
import com.transformedge.apps.entity.Blog;
import com.transformedge.apps.entity.Employee;
import com.transformedge.apps.exceptions.ErrorFormInfo;
import com.transformedge.apps.model.BlogModel;
import com.transformedge.apps.model.EmployeeModel;
import com.transformedge.apps.repository.UserRepository;
import com.transformedge.apps.service.BlogService;
import com.transformedge.apps.service.EmployeeService;

@RestController
@CrossOrigin
@RequestMapping(value="${spring.data.rest.base-path}/blog_controller")
public class BlogController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BlogService blogService;
	
	@PostMapping("/saveBlog")
	public ResponseEntity<?> saveBlog(@RequestBody @Valid BlogModel model, HttpServletRequest request) {
		logger.info("INSIDE BlogController START METHOD saveBlog :");
		final Blog blog = blogService.saveBlog(model);
		
		ErrorFormInfo errorInfo = null;
		if (blog != null) {
			String successMsg = Translator.toLocale("blog.added.successfully");
			errorInfo = new ErrorFormInfo(HttpStatus.OK, true, request.getRequestURI(), successMsg, null);
			return new ResponseEntity<>(errorInfo, HttpStatus.OK);
		}
		return new ResponseEntity<>(errorInfo, HttpStatus.RESET_CONTENT);
	}
}
