package com.doctorfinder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patient {

	@Id @Column(name = "Patient_id")
	private Integer Id;
	@Column(name = "Patient_name")
	private String name;
	@Column(name = "Patient_email")
	private String email;
	@Column(name = "Patient_password")
	private String password;
	@Column(name = "Patient_mobile")
	private Long mobile;
	@Column(name = "Patient_age")
	private Integer age;
	@Column(name = "Patient_gender")
	private String gender;
	@Column(name = "Patient_address")
	private String address;
	
}
