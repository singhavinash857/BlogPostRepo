package com.transformedge.apps.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.transformedge.apps.appconfiguration.Translator;
import com.transformedge.apps.entity.Employee;
import com.transformedge.apps.entity.Role;
import com.transformedge.apps.entity.User;
import com.transformedge.apps.exceptions.UserAlreadyExistException;
import com.transformedge.apps.model.EmployeeModel;
import com.transformedge.apps.repository.EmployeeRepository;
import com.transformedge.apps.repository.RoleRepository;
import com.transformedge.apps.repository.UserRepository;
import com.transformedge.apps.service.EmployeeService;

@Service
public class EmployeeServiceIMPL implements EmployeeService{

	@Autowired
	EmployeeRepository  employeeRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	public Employee registerEmployee(final EmployeeModel employeeModel) {
		if (emailExists(employeeModel.getEmployeeMailId())) {
			String emailExistMessage = Translator.toLocale("user.email.exist");
			throw new UserAlreadyExistException(emailExistMessage + employeeModel.getEmployeeMailId());
		}
		final Employee employee = new Employee();
		final User user = new User();
		try {
			user.setUsername(employeeModel.getEmployeeMailId());
			user.setPassword(bCryptPasswordEncoder.encode((employeeModel.getEmployeePassword())));
			user.setPasswordConfirm(bCryptPasswordEncoder.encode((employeeModel.getEmployeeConfirmPassword())));
			for(String roles : employeeModel.getEmployeeRole()){
				Role userRole = roleRepository.findByName(roles);
				if(userRole != null){
					user.getRoles().add(userRole);
				}else{
					user.getRoles().add(new Role(roles));
				}
			}
			if(userRepository.save(user) != null){
				employee.setEmployeeFirstName(employeeModel.getEmployeeFirstName());
				employee.setEmployeeLastName(employeeModel.getEmployeeLastName());
				employee.setEmployeeMailId(employeeModel.getEmployeeMailId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeRepository.save(employee);
	}

	private boolean emailExists(final String employeemailid) {
		return employeeRepository.findByEmployeeMailId(employeemailid) != null;
	}

	@Override
	public Employee getEmployeeByMailId(String username) {
		return employeeRepository.findByEmployeeMailId(username);
	}
}