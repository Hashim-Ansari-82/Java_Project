package com.track.entity;

import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "User_Details")
public class User {

	@Id
	private int id;
	private String name;
	private String email;
	private String password;
	private String about;
	
	public User(String name, String email, String password, String about) {
		super();
		this.id = 10000 + new Random().nextInt(900000);
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}

}
