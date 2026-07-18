package com.library.dao;

import java.util.List;

import com.library.entity.BookIssue;

public interface BookIssueDao {

	public void issueBook(BookIssue bookIssue);
	public BookIssue returnBook(BookIssue bookIssue) ;
	public List<BookIssue>getAllIssuedBooks(); 
	public BookIssue getIssueById(Integer id);
}
 