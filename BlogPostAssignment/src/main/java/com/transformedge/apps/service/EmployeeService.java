package com.transformedge.apps.service;

import javax.validation.Valid;

import com.transformedge.apps.entity.Employee;
import com.transformedge.apps.model.EmployeeModel;

public interface EmployeeService {
	Employee registerEmployee(@Valid EmployeeModel employeeModel);
	Employee getEmployeeByMailId(String username);
}
