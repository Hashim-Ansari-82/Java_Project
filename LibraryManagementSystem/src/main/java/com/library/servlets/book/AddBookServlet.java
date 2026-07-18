package com.library.servlets.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dao.BookDao;
import com.library.daoimpl.BookDaoImpl;
import com.library.entity.Book;

@WebServlet("/component/AddBookServlet")
public class AddBookServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String category = req.getParameter("category");
		Integer price = Integer.parseInt(req.getParameter("price"));
		Integer quantity = Integer.parseInt(req.getParameter("quantity"));
		Integer availableQuantity = Integer.parseInt(req.getParameter("availableQuantity"));
		
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		book.setCategory(category);
		book.setPrice(price);
		book.setQuantity(quantity);
		book.setAvailableQuantity(availableQuantity);
		
		BookDao dao = new BookDaoImpl();
		dao.saveBook(book);
		
		resp.sendRedirect(req.getContextPath() + "/component/ViewBookServlet?success=Book Added Successfully");
	}

}
