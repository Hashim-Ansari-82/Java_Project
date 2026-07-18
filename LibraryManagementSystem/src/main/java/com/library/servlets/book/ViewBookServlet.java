package com.library.servlets.book;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dao.BookDao;
import com.library.daoimpl.BookDaoImpl;
import com.library.entity.Book;

@WebServlet("/component/ViewBookServlet")
public class ViewBookServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
          BookDao book=new BookDaoImpl();
		  List<Book> list = book.getAllBooks();
          
		  req.setAttribute("books", list);
		  
		  System.out.println("Servlet are hiting");
		  
		  req.getRequestDispatcher("/component/viewBooks.jsp").forward(req, resp);
	}

}
