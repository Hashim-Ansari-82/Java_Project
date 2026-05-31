<%@page import="java.util.List"%>
<%@page import="com.track.entity.Expanse"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>View Expenses</title>
<%@ include file="../component/all_css_js.jsp"%>
</head>

<body>

	<c:if test="${empty loginUser}">
		<c:redirect url="../login.jsp" />
	</c:if>

	<%@ include file="../component/navbar.jsp"%>

	<div class="container py-4">

		<div class="row justify-content-center">

			<div class="col-lg-10">

				<div class="card shadow">

					<div class="card-header text-center">
						<h3>All Expenses</h3>
					</div>

					<div class="card-body table-responsive">

						<table class="table table-bordered table-hover text-nowrap">

							<thead class="table-dark">
								<tr>
									<th>Title</th>
									<th>Description</th>
									<th>Date</th>
									<th>Time</th>
									<th class="text-center">Action</th>
								</tr>
							</thead>

							<tbody>

								<%
								List<Expanse> list = (List<Expanse>) request.getAttribute("exList");

								if (list != null && !list.isEmpty()) {

									for (Expanse e : list) {
								%>

								<tr>
									<td><%=e.getTitle()%></td>
									<td><%=e.getDesc()%></td>
									<td><%=e.getDate()%></td>
									<td><%=e.getTime()%></td>

									<td class="text-center"><a
										href="<%=request.getContextPath()%>/updateServlet?id=<%=e.getId()%>"
										class="btn btn-primary btn-sm"> Edit </a> <a
										href="${pageContext.request.contextPath}/deleteExpense?id=<%=e.getId()%>"
										class="btn btn-sm btn-danger ms-2"
										onclick="return confirm('Are you sure you want to delete this expense?')">
											<i class="fa-solid fa-trash"></i> Delete
									</a>
								</tr>

								<%
								}
								} else {
								%>

								<tr>
									<td colspan="5" class="text-center text-danger">No Data
										Found</td>
								</tr>

								<%
								}
								%>

							</tbody>

						</table>

					</div>

				</div>

			</div>

		</div>

	</div>

</body>
</html>