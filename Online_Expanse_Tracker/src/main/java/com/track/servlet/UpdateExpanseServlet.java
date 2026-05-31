package com.track.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.track.entity.Expanse;
import com.track.helper.FactoryProvider;

@WebServlet("/updateServlet")
public class UpdateExpanseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Session session = FactoryProvider.getFactory().openSession();

        try {
            int id = Integer.parseInt(req.getParameter("id"));

            Query<Expanse> query = session.createQuery(
                    "from Expanse where id=:id", Expanse.class);

            query.setParameter("id", id);

            Expanse ex = query.uniqueResult();

            req.setAttribute("expanse", ex);

            req.getRequestDispatcher("user/edit_expense.jsp")
                    .forward(req, resp);

        } finally {
            session.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Session session = FactoryProvider.getFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            int id = Integer.parseInt(req.getParameter("id"));

            Expanse ex = session.get(Expanse.class, id);

            if (ex != null) {
                ex.setTitle(req.getParameter("title"));
                ex.setDate(req.getParameter("date"));
                ex.setTime(req.getParameter("time"));
                ex.setDesc(req.getParameter("desc"));
                ex.setPrice(Double.parseDouble(req.getParameter("price")));

                session.update(ex);
            }

            tx.commit();

            resp.sendRedirect(req.getContextPath() + "/viewExpanse");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}