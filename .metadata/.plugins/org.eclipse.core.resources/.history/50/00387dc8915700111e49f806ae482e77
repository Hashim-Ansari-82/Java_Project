package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Note;
import com.helper.FactoryProvider;

public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			int noteId = Integer.parseInt(request.getParameter("noteId").trim());

			Session session = FactoryProvider.getFactory().openSession();

			Transaction tx = session.beginTransaction();

			Note note = session.get(Note.class, noteId);

			if (note != null) {
				session.delete(note);
			}

			tx.commit();

			session.close();

			response.sendRedirect("allNotes.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}