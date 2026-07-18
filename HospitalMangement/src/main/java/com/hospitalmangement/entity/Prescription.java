package com.hospitalmangement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Prescription {

	@Id
	@Column(name="Prescription_Id")
	private Integer id;
	private String medicine;
	private String instruction;
	
	@OneToOne
	@JoinColumn(name = "Appointment_Id")
	private Appointment appointment;
}
