package com.form.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Employee {

	@Id
	@Column(name = "employee_id")
	private Integer id;
	@Column(name = "employee_name")
	private String name;
	@Column(name = "employee_department")
	private String dept;
	@Column(name = "employee_salary")
	private Double salary;
	@Column(name = "employee_email")
	private String email;
	@Column(name = "employee_password")
	private String password;
}
