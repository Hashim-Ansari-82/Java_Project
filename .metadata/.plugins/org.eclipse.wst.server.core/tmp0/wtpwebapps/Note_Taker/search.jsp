<%@page import="java.util.List"%>
<%@page import="org.hibernate.query.Query"%>
<%@page import="org.hibernate.Session"%>

<%@page import="com.entity.Note"%>
<%@page import="com.helper.FactoryProvider"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Notes</title>

<%@ include file="all_js_css.jsp"%>

</head>
<body>

	<div class="container">

		<%@ include file="navbar.jsp"%>

		<h2 class="text-center mt-4">Search Results</h2>

		<%
		String keyword = request.getParameter("keyword");

		Session s = FactoryProvider.getFactory().openSession();

		Query<Note> q = s.createQuery("from Note where lower(title) like :x or lower(content) like :x", Note.class);

		q.setParameter("x", "%" + keyword.toLowerCase() + "%");

		List<Note> list = q.list();

		if (list.isEmpty()) {
		%>

		<h4 class="text-danger text-center mt-4">No Record Found</h4>

		<%
		} else {

		for (Note note : list) {
		%>

		<div class="card mt-4 shadow-sm">

			<div class="card-body">

				<h4>
					#<%=note.getId()%>
					-
					<%=note.getTitle()%>
				</h4>

				<p>
					<%=note.getContent()%>
				</p>

				<p class="text-primary">
					<b><%=note.getAddDate()%></b>
				</p>

			</div>

		</div>

		<%
		}
		}

		s.close();
		%>

	</div>

</body>
</html>