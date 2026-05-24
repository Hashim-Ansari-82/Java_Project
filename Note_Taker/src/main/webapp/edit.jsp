<%@page import="com.entity.Note"%>
<%@page import="com.helper.FactoryProvider"%>
<%@page import="org.hibernate.Session"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Edit Note</title>

<%@ include file="all_js_css.jsp"%>

<style>
body {
	background: #f4f6f9;
}

.note-card {
	width: 90%;
	max-width: 650px;
	border-radius: 15px;
}
</style>

</head>

<body>

	<%
	int noteId = Integer.parseInt(request.getParameter("noteId"));

	Session s = FactoryProvider.getFactory().openSession();

	Note note = s.get(Note.class, noteId);
	%>

	<div class="container ">

		<%@ include file="navbar.jsp"%>

		<div class="container d-flex justify-content-center align-items-center" style="min-height: 85vh;">

			<div class="card shadow-lg p-4 note-card">

				<h3 class="text-center mb-4 text-uppercase">Edit Your note</h3>

				<form action="UpdateServlet" method="post">

					<input type="hidden" name="noteId" value="<%=note.getId()%>">

					<div class="form-group">

						<label for="title"> Note Title </label> <input required
							type="text" class="form-control" id="title" name="title"
							value="<%=note.getTitle()%>">

					</div>

					<div class="form-group mt-3">

						<label for="content"> Note Content </label>

						<textarea required id="content" name="content"
							class="form-control" style="height: 180px; resize: none;"><%=note.getContent()%></textarea>

					</div>

					<div class="text-center mt-4">

						<button type="submit" class="btn btn-success px-5">Update Note</button>

					</div>

				</form>

			</div>

		</div>

	</div>

</body>
</html>