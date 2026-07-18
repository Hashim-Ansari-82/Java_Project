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
public class Patient {

	@Id
	@Column(name ="Patient_ID")
	private Integer id;
	
	@Column(name ="Patient_Name")
	private String name;
	
	@Column(name ="Patient_Age")
	private Integer age;
	
	@Column(name ="Patient_Gender")
	private String gender;
	
	@Column(name ="Patient_Mobile")
	private String mobile;
	
	@OneToMany(mappedBy = "patient")
	private List<Appointment> appointments;
}
