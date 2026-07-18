package com.hospitalmangement.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Doctor {

	@Id
	@Column(name = "Doctor_Id")
	private Integer id;
	@Column(name = "Doctor_Name")
	private String name ;
	private String specialization;
	
	@ManyToOne
	@JoinColumn(name="department_id	")
	private Department department;
	
	@OneToMany(mappedBy="doctor")
	private List<Appointment> appointments;
}
