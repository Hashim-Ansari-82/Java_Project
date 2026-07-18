package com.hospitalmangement.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Appointment {

	@Id
	@Column(name = "Appointment_Id")
	private Integer id;
	@Column(name = "Appointment_Time")
	private LocalTime time;
	@Column(name = "Appointment_Date")
	private LocalDate date;
	@Column(name = "Appointment_Status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "Doctor_Id")
	private Doctor doctor;
	
	@ManyToOne
	@JoinColumn(name = "Patient_Id")
	private Patient patient;
	
	@OneToOne(mappedBy="appointment")
	private Prescription prescription;
	
	@OneToOne(mappedBy="appointment")
	private Billing billing;
	
}
