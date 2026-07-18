<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Manage page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body
	class="bg-light d-flex justify-content-center align-items-center vh-100">
	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card" style="width: 500px;">
					<div class="card-header bg-dark text-white text-center">
						<h2>Welcome to Book Manage page</h2>
					</div>
					<div class="card-body">
						<div class="text-center p-2">
							<a href="../component/addBooks.jsp" class="btn btn-success col-md-6">Add Books</a>
						</div>
						<div class="text-center p-2">
							<a href="../component/viewBooks.jsp" class="btn btn-primary col-md-6">View Books</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous">
	</script>
</body>
</html>