package com.doctorfinder.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Appointment {

	@Id @Column(name="Appointment_id")
	private Integer Id;
	@Column(name="Appointment_Date")
	private LocalDate date;
	@Column(name="Appointment_Time")
	private LocalTime time;
	@Column(name="Appointment_Status")
	private String status;
	@Column(name="Appointment_Doctor")
	private String doctor;
	@Column(name="Appointment_Patient")
	private String patient;
}
