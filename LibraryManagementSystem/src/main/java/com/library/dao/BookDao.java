package com.library.dao;

import java.util.List;

import com.library.entity.Book;

public interface BookDao {

	public void saveBook(Book book);
	public Book updateBook(Book book);
	public boolean deleteBook(Integer id);
	public Book getBookById(Integer id);
	public List<Book> getAllBooks();
	public List<Book> searchBook(String name);
}
