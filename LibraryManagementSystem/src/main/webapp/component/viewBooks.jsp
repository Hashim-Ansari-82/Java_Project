<%@ page import="com.library.daoimpl.BookDaoImpl"%>
<%@ page import="com.library.entity.Book"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<%
BookDaoImpl dao = new BookDaoImpl();
request.setAttribute("books", dao.getAllBooks());
%>
<body
	class="bg-dark d-flex justify-content-center align-items-center vh-100">

	<div class="container py-5">

		<div class="row justify-content-center">

			<div class="col-lg-11 col-md-12">

				<div class="card shadow-lg">

					<div class="card-header text-center bg-secondary">
						<h3 class="mb-0">Book List</h3>
					</div>

					<div class="card-body">

						<div class="table-responsive">

							<table
								class="table table-bordered table-hover text-center align-middle">

								<thead class="table-dark">
									<tr>
										<th>Book Id</th>
										<th>Book Title</th>
										<th>Author</th>
										<th>Price</th>
										<th>Quantity</th>
										<th>Available Quantity</th>
										<th>Category</th>
										<th>Actions</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach var="b" items="${books}">
										<tr>
											<td>${b.bookId}</td>
											<td>${b.title}</td>
											<td>${b.author}</td>
											<td>${b.price}</td>
											<td>${b.quantity}</td>
											<td>${b.availableQuantity}</td>
											<td>${b.category}</td>

											<td><a href="UpdateBookServlet?id=${b.getBookId()}"
												class="btn btn-warning btn-sm">Update</a> <a
												href="../component/DeleteBookServlet?id=${ b.getBookId()}"
												class="btn btn-danger btn-sm">Delete</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>