package com.doctorfinder.entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

	@Id
	@Column(name = "Doctor_id")
	private Integer id;
	@Column(name = "Doctor_name")
	private String name;
	@Column(name = "Doctor_email")
	private String email;
	@Column(name = "Doctor_password")
	private Integer password;
	@Column(name = "Doctor_No")
	private Long mobile_No;
	@Column(name = "Doctor_number")
	private Integer number;
	@Column(name = "Doctor_qualification")
	private String qualification;
	@Column(name = "Doctor_specialization")
	private String specialization;
	@Column(name = "Doctor_experience")
	private String experience;
	@Column(name = "Doctor_Hospital")
	private String hospital_Name;
	@Column(name = "Doctor_City")
	private String city;
	@Column(name = "Doctor_Address")
	private String Address;
	@Column(name = "Doctor_Fees")
	private Double fees;
	@Column(name = "Doctor_availableTime")
	private LocalTime availableTime;
}
