<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home page</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
</head>
<body
	class="bg-dark d-flex justify-content-center align-items-center vh-100">
	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card" style="width: 500px;">
					<div class="card-header bg-dark text-white text-center">
						<h2>Welcome to Home page</h2>
					</div>
					<div class="card-body">
						<div class="text-center p-2">
							<a href="component/book.jsp"
								class="btn btn-success col-md-4 me-3">Books</a> <a
								href="component/member.jsp" class="btn btn-primary col-md-4">Members</a>
						</div>
						<div class="text-center p-2">
							<a href="component/issueBook.jsp"
								class="btn btn-warning col-md-4">Issue Book</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>

</body>
</html>