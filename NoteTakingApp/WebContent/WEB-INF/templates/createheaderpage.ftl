<html>
	<head>
		<meta charset="UTF-8">
		<script src="js/jquery.js"></script>
		<link rel="stylesheet" href="css/bootstrap.css">
		<script src="js/bootstrap.js"></script>
		<link rel="stylesheet" href="css/default.css">
		<link rel="stylesheet" href="css/createheaderpage.css">
		<script src="js/createheaderpage.js"></script>
	</head>
	<body>
		<nav class="navbar">
			<form action="Navigate" method="post" accept-charset=utf-8>
					<input class="nav-link active" type="submit" name="GoToNotePage" value="Notes">
					<input class="nav-link active" type="submit" name="GoToCreateHeaderPage" value="Create Header">
					<input type="hidden" name="username" value=${username}>
			</form>
		</nav>
		<div class="container">
			<!-- will have to freemarker this later -->
			<table class="table" id="headers">
				<tr>
					<th>
						Category Name
					</th>
					<th>
						Color
					</th>
					<th>
						Delete
					</th>
				</tr>
				<tr>
					<td>
						Uncategorized
					</td>
					<td>
						<select id="colorSelect">
							<option>option 0</option>
							<option>option 1</option>
							<option>option 2</option>
						</select>
					</td>
					<td>
						<div style="display:none;">
							<button type="button"  class="close" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
					</td>
				</tr>
				<#list sequence as item>
					<tr>
						<td>
							${item}
						</td>
						<td>
							<select>
								<option>test 0</option>
							</select>
						</td>
						<td>
							<button>remove btn</button>
						</td>
					</tr>
				</#list>
			</table>
			<input type="text" id="headerName" placeholder="header name">
			<button id="createHeader">Create</button>
			<form id="submitHeaderForm" action="CreateHeader" method="post" accept-charset=utf-8>
				<button name="submitHeaders">Submit New Headers</button>
				<input type="hidden" id="headersAddedCount" name="headersAddedCount" value=0>
				<input type="hidden" name="username" value=${username}>
			</form>
		</div>
	</body>
</html>