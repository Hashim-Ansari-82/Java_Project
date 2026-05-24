package com.entity;

import java.util.Date;
import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Note {

	@Id
	@Column()
	private int id;
	private String title;
	@Column(columnDefinition = "LONGTEXT")
	private String content;
	@Column(name="Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date addDate;
	
	public Note(String title, String content, Date addDate) {
		super();
		this.id = 1000 + new Random().nextInt(9000);
		this.title = title;
		this.content = content;
		this.addDate = addDate;
	}
	 
}