package com.track.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.track.entity.Expanse;
import com.track.helper.FactoryProvider;

@WebServlet("/deleteExpense")
public class DeleteExpenseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Session session = FactoryProvider.getFactory().openSession();
        Transaction tx = null;

        try {
            int id = Integer.parseInt(req.getParameter("id"));

            tx = session.beginTransaction();

            Expanse ex = session.get(Expanse.class, id);

            if (ex != null) {
                session.delete(ex);
            }

            tx.commit();

            // redirect back to list
            resp.sendRedirect(req.getContextPath() + "/viewExpanse");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}