<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Note Taker</title>
<%@ include file="all_js_css.jsp"%>
</head>
<body>

	<div class="container">
		<%@ include file="navbar.jsp"%>
		<br>

		<div class="card mx-auto mt-4 p-4 text-center"
			style="width: 45%; border-radius: 30px;">

			<img alt="" src="image/img.jpeg"
				class="img-fluid rounded mx-auto d-block" style="width: 70%;">

			<h1 class="text-primary mt-3">
				<b> Start Writing Your Note</b>
			</h1>
			<div class="container text-center>">
			<a href="addNotes.jsp">
			<button class="btn btn-outline-primary text-center mt-2 ">Start Here</button>		
			</a>	
			</div>
		</div>
	</div>
</body>
</html>