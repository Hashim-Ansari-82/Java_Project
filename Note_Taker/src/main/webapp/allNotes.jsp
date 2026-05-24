<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Session"%>

<%@page import="com.entity.Note"%>
<%@page import="com.helper.FactoryProvider"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All notes : Note Taker</title>

<%@ include file="all_js_css.jsp"%>

</head>
<body>

	<div class="container">

		<%@ include file="navbar.jsp"%>
   <br>

		<div class="row">
			<div class="col-12">

				<%
				Session s = FactoryProvider.getFactory().openSession();

				Criteria c = s.createCriteria(Note.class);

				List<Note> list = c.list();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

				for (Note note : list) {
				%>

				<div class="card mb-4" style="width: 65%; margin: auto;">

					<div class="row">

						<div class="col-md-2 text-center p-3">

							<img src="image/img.jpeg"
								style="width: 100px; height: 100px; object-fit: cover;"
								alt="Note Image">

						</div>

						<div class="col-md-10">

							<div class="card-body">

								<h5 class="card-title">
									#<%=note.getId()%>
									-
									<%=note.getTitle()%>
								</h5>

								<p class="card-text">
									<%=note.getContent()%>
								</p>

								<p class="card-text">

									<b class="text-primary"> <%=sdf.format(note.getAddDate())%>
									</b>

								</p>

								<a href="DeleteServlet?noteId=<%=note.getId()%>"
									class="btn btn-danger"> Delete </a> <a
									href="edit.jsp?noteId=<%=note.getId()%>"
									class="btn btn-primary"> Update </a>

							</div>

						</div>

					</div>

				</div>

				<%
				}

				s.close();
				%>

			</div>
		</div>

	</div>

</body>
</html>