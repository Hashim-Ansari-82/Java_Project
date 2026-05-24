<nav class="navbar navbar-expand-lg navbar-dark purple shadow-sm"
	style="width: 90%; margin: 10px auto; border-radius: 10px; padding: 5px 15px;">

	<a class="navbar-brand font-weight-bold" style="font-size: 20px;"
		href="index.jsp"> Note Taker </a>

	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">

		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">

		<ul class="navbar-nav mr-auto">

			<li class="nav-item active"><a class="nav-link"
				style="font-size: 15px;" href="index.jsp"> Home </a></li>

			<li class="nav-item"><a class="nav-link"
				style="font-size: 15px;" href="addNotes.jsp"> Add Note </a></li>

			<li class="nav-item"><a class="nav-link"
				style="font-size: 15px;" href="allNotes.jsp"> Show Notes </a></li>

		</ul>

		<form class="form-inline my-1 my-lg-0" action="search.jsp"
			method="get">

			<input class="form-control form-control-sm mr-sm-2" type="search"
				name="keyword" placeholder="Search Here..." required>

			<button class="btn btn-outline-light btn-sm my-1 my-sm-0"
				type="submit">Search</button>

		</form>

	</div>

</nav>