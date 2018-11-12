<html>
	<head>
		<meta charset="UTF-8">
		<script src="js/jquery.js"></script>
		<link rel="stylesheet" href="css/bootstrap/bootstrap.css">
		<script src="js/bootstrap/bootstrap.js"></script>
		<link rel="stylesheet" href="css/default.css">
		<link rel="stylesheet" href="css/createheaderpage.css">
		<script src="js/createheaderpage.js"></script>
	</head>
	<body>
		<nav class="navbar-expand-lg fixed-top navbar-dark bg-dark">
			<ul class="navbar-nav">
				<li><a><span class="navbar-brand">Brand</span></a></li>
				<li><input class="searchbar" placeholder="Search" type="text" name="search"></li>
				<li><span>Username</span></li>
			</ul>
		</nav>
		<nav class="navbar" style="padding: 0px">
			<form action="Navigate" method="post" accept-charset=utf-8>
					<input class="nav-link active" type="submit" name="GoToNotePage" value="Notes">
					<input class="nav-link active" type="submit" name="GoToCreateHeaderPage" value="Create Header">
					<input type="hidden" name="username" value=${username}>
			</form>
		</nav>
		<div class="container" style="margin-top: 40px">
			<!-- will have to freemarker this later -->
			<table class="table" id="headers">
				<tr>
					<th>
						Category Name
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
						<div id="btnClosePrefab" style="display:none;">
							<button type="button" class="close" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
					</td>
				</tr>
				<#list sequence as item>
					<tr>
						<td>
							${item.getCategoryName()}
						</td>
						<td>
							<button type="button" style="float: left;" onclick="removeHeader()" class="close" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</td>
					</tr>
				</#list>
			</table>
			<input type="text" id="headerName" placeholder="header name">
			<button id="createHeader">Create</button>
			<form id="submitHeaderForm" action="HeaderPage" onsubmit="return checkHeadersAdded();" method="post" accept-charset=utf-8>
				<button name="submitHeaders">Submit New Headers</button>
				<input type="hidden" id="username" name="username" value=${username}>
			</form>
		</div>
	</body>
</html>