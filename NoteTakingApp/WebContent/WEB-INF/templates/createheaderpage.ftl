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
		<header class="navbar-expand-lg fixed-top navbar-dark bg-dark">
		<div class="headerItems"><a><span class="navbar-brand">Notetaking Web App</span></a></div>
		<div class="headerItems"><input class="searchbar" placeholder="Search" type="text" name="search"></div>
		<div class="headerItems">
						<form class="headerItems" action="Navigate" method="get">
							<input id="logoutbtn" type="submit" name="logout" value="Logout">
						</form> 
					</div>
		<div class="nav-item accountName"><span>${user}</span></div>
		</header>
		
		<nav id="navigate" class="nav flex-column">
			<form action="Navigate" method="get">
					<input class="nav-link active" type="submit" name="GoToNotePage" value="Notes">
					<input class="nav-link active" type="submit" name="GoToCreateHeaderPage" value="Edit Headers">
					<input class="nav-link active" type="submit" name="GoToArchivePage" value="Archive">
					<input id="username" type="hidden" name="username" value=${user}>
			</form>
		</nav>
		<div id="btnClosePrefab">
			<button type="button" class="close" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="container">
			<table class="table" id="headers">
				<tr>
					<th>
						Category Name
					</th>
					<th>
						Delete
					</th>
				</tr>
				<#list sequence as item>
					<tr>
						<td>
							${item.getCategoryName()}
						</td>
						<td>
							<button type="button" onclick="removeHeader()" class="close" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</td>
					</tr>
				</#list>
			</table>
			<input type="text" id="headerName" placeholder="header name">
			<button id="createHeader">Create</button>
		</div>
	</body>
</html>