<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body class="bg-light">

	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3 mt-2">
				<div class="card">
					<h1>
						<div class="card-header text-center fs-3">
							Employee Register</div>
					</h1>
					<div class="card-body">
						<form method="post" action="RegisterServlet">

							<div class="mb-3">
								<label for="employeeName" class="form-label">Employee Name</label> 
								<input type="text" class="form-control" name="name" placeHolder="Enter name here">
							</div>

							<div class="mb-3">
								<label for="employeeDepartment" class="form-label">Employee Department</label> 
								<input type="text" class="form-control" name="department" placeHolder="Enter department here">
							</div>

							<div class="mb-3">
								<label for="employeeSalary" class="form-label">Employee Salary</label> 
								<input type="text" class="form-control" name="salary" placeHolder="Enter salary here">
							</div>

							<div class="mb-3">
								<label for="employeeEmail" class="form-label">Employee Email</label>
								 <input type="text" class="form-control" name="email" placeHolder="Enter email here">
							</div>

							<div class="mb-3">
								<label for="employeeName" class="form-label">Employee Password</label> 
								<input type="text" class="form-control" name="password" placeHolder="Enter password here">
							</div>

							<div class="text-center mt-4">
								<button type="submit" class="btn btn-success ">Register</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
