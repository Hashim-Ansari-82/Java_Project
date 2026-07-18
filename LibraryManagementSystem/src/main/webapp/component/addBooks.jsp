<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Book</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body class="bg-dark">

	<div class="container mt-3">

		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card">

					<div class="card-header text-center bg-secondary text-white">
						<h3>Add New Book</h3>
					</div>

					<div class="card-body">

						<form action="AddBookServlet" method="post">

							<div class="mb-2">
								<label class="form-label">Book Title</label> <input type="text"
									class="form-control" name="title"
									placeholder="Enter Book Title" required>
							</div>

							<div class="mb-2">
								<label class="form-label">Author</label> <input type="text"
									class="form-control" name="author"
									placeholder="Enter Author Name" required>
							</div>

							<div class="mb-2">
								<label class="form-label">Category</label> <input type="text"
									class="form-control" name="category"
									placeholder="Enter Category" required>
							</div>

							<div class="mb-2">
								<label class="form-label">Price</label> <input type="number"
									class="form-control" name="price" placeholder="Enter Price"
									required>
							</div>

							<div class="mb-2">
								<label class="form-label">Quantity</label> <input type="number"
									class="form-control" name="quantity"
									placeholder="Enter Quantity" required>
							</div>
							<div class="mb-2">
								<label class="form-label">Available Quantity</label> <input
									type="number" class="form-control" name="availableQuantity"
									placeholder="Enter AvailableQuantity" required>
							</div>
							<div class="text-center">
								<button type="submit" class="btn btn-success col-md-3">Save
									Book</button>

								<button type="reset" class="btn btn-danger col-md-3">Reset</button>
							</div>
						</form>

					</div>

				</div>
			</div>
		</div>

	</div>

</body>
</html>