<html>
	<head>
		<script src="js/jquery.js"></script>
		<link rel="stylesheet" href="Bootstrap/css/bootstrap.css">
		<script src="Bootstrap/js/bootstrap.js"></script>
		<link rel="stylesheet" href="css/default.css">
		<link rel="stylesheet" href="css/createheaderpage.css">
		<script src="js/createheaderpage.js"></script>
	</head>
	<body>
		<nav class="nav flex-column" style="position: fixed;">
			<form action="Navigation" method="post">
					<input class="nav-link active" type="submit" name="GoToNotePage" value="Notes">
					<input class="nav-link active" type="submit" name="GoToCreateHeaderPage" value="Create Header">
					<a class="nav-link" href="#">Link</a>
					<a class="nav-link disabled" href="#">Disabled</a>
			</form>
		</nav>
		<div class="main">
			<!-- will have to freemarker this later -->
			<table id="headers" border="1">
				<tr>
					<td>
						Uncategorized
					</td>
					<td>
						<select id="colorSelect">
							<option>option 0</option>
						</select>
					</td>
					<td>
						<div style="display: none;">
							<button type="button"  class="close" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div id="creation" style="position: fixed; top: 0; right: 0;">	
			<input type="text" id="headerName" placeholder="header name">
			<select id="colorSelect">
				<option>option 0</option>
			</select>
			<button id="createHeader">Create</button>
		</div>
	</body>
</html>