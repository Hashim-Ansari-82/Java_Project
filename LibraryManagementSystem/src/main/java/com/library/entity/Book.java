package com.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Book_Id")
	private Integer bookId;
	@Column(name="Book_Title")
	private String title;
	@Column(name="Author_Name")
	private String author;
	@Column(name="Category")
	private String category;
	@Column(name="Book_Price")
	private Integer price;
	@Column(name="Book_Quantity")
	private Integer quantity;
	@Column(name="Availabe_Book")
	private Integer availableQuantity;
	
}
