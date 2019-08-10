package com.transformedge.apps.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="employeeId")
@Data
@Entity
@Table(name="employee")
public class Employee implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="employee_id")
	private long employeeId;

	@Column(name="employee_firstname")
	private String employeeFirstName;

	@Column(name="employee_lasttname")
	private String employeeLastName;
	
	@Column(name="employee_mail_id")
	private String employeeMailId;

	@Transient
	private Set<Role> employeeRoles;
}
