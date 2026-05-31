package com.form.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.form.entity.Employee;
import com.form.helper.FactoryProvider;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String department = req.getParameter("department");
		Double salary = Double.parseDouble(req.getParameter("salary"));
		String email = req.getParameter("email");
		String password = (req.getParameter("password"));

		Employee employee = new Employee(name,department,salary,email,password);

		Session session = FactoryProvider.getFactory().openSession();
		Transaction tx = session.beginTransaction();

		int save = (Integer) session.save(employee);

		PrintWriter pw = resp.getWriter();

		tx.commit();

		if (save > 0) {
			pw.println("<div style='text-align:center; margin-top:30px;'>");

			pw.println("<h1 style='color:green;'>Employee Register Successfully</h1>");

			pw.println("<h1>");
			pw.println("<a href='index.jsp'>Go to Home Page</a>");
			pw.println("</h1>");

			pw.println("</div>");

		    System.out.println("Employee Register Successfully");
		} else {
			pw.println("<div style='text-align:center; margin-top:30px;'>");

			pw.println("<h1 style='color:green;'>Employee Register Failed</h1>");

			pw.println("<h1>");
			pw.println("<a href='index.jsp'>Go to Home Page</a>");
			pw.println("</h1>");

			pw.println("</div>");

		    System.err.println("Employee Register Failed");
		}

		session.close();

	}
}
