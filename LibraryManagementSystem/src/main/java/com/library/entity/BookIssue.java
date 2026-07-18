package com.library.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookIssue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer issueId;
	private LocalDate issueDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	private String status;
	@ManyToOne
	@JoinColumn(name="book_Id")
	private Book book;
	@ManyToOne
	@JoinColumn(name="member_Id")
	private Member member;
}
