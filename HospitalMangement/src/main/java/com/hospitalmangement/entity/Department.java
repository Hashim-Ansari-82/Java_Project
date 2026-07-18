package com.hospitalmangement.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Department {

	@Id
	@Column(name = "Department_Id")
	private Integer id;
	@Column(name = "Department_Name")
	private String name;
	
	@OneToMany(mappedBy = "department")
	private List<Doctor> doctor;
}
