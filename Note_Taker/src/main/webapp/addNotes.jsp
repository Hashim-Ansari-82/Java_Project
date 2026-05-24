<!-- addNotes.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Add note page</title>

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
	<div class="container">
		<%@ include file="navbar.jsp"%>

		<div
			class="container d-flex justify-content-center align-items-center" style="min-height: 85vh;">

			<div class="card shadow-lg p-4 note-card">

				<h3 class="text-center mb-4 text-uppercase">Add Your Note Here</h3>

				<form action="SaveNoteServlet" method="post">

					<div class="form-group">

						<label for="title"> Note Title </label> <input required
							type="text" class="form-control" id="title" name="title"
							placeholder="Enter Title Here">

					</div>

					<div class="form-group mt-3">

						<label for="content"> Note Content </label>

						<textarea required id="content" name="content"
							class="form-control" placeholder="Enter Your Content Here"
							style="height: 180px; resize: none;"></textarea>

					</div>

					<div class="text-center mt-4">

						<button type="submit" class="btn btn-primary px-5">Save Notes</button>

					</div>

				</form>

			</div>

		</div>
	</div>

</body>
</html>