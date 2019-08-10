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
import com.transformedge.apps.entity.Employee;
import com.transformedge.apps.exceptions.ErrorFormInfo;
import com.transformedge.apps.model.EmployeeModel;
import com.transformedge.apps.repository.UserRepository;
import com.transformedge.apps.service.EmployeeService;

@RestController
@CrossOrigin
@RequestMapping(value="${spring.data.rest.base-path}/employee_controller")
public class EmployeeController {

	@SuppressWarnings("unused")
	@Autowired
	private CsvConfiguration csvConfiguration;

	@Autowired
	private EmployeeService employeeServiceIMPL;
	
	@Autowired
	UserRepository userRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/saveEmployee")
	public ResponseEntity<?> registerEmployee(@RequestBody @Valid EmployeeModel employeeModel, HttpServletRequest request) {
		logger.info("INSIDE EmployeeController START METHOD registerEmployee :");
		final Employee registeredEmployee = employeeServiceIMPL.registerEmployee(employeeModel);
		ErrorFormInfo errorInfo = null;
		if (registeredEmployee != null) {
			String successMsg = Translator.toLocale("user.added.successfully");
			errorInfo = new ErrorFormInfo(HttpStatus.OK, true, request.getRequestURI(), successMsg, null);
			return new ResponseEntity<>(errorInfo, HttpStatus.OK);
		}
		return new ResponseEntity<>(errorInfo, HttpStatus.RESET_CONTENT);
	}
}
