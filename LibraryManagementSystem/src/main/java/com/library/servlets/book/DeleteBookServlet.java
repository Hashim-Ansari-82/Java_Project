package com.library.servlets.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dao.BookDao;
import com.library.daoimpl.BookDaoImpl;

public class DeleteBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.parseInt(req.getParameter("bookId"));

		BookDao dao = new BookDaoImpl();
		dao.deleteBook(id);

		resp.sendRedirect("/component/viewBooks.jsp");
	}

}
