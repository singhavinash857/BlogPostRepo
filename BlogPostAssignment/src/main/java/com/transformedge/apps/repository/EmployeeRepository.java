package com.transformedge.apps.repository;

import org.springframework.data.repository.CrudRepository;

import com.transformedge.apps.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
		Employee findByEmployeeMailId(String employeemailid);
}
