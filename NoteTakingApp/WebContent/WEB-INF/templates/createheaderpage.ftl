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
			<div><a><span class="navbar-brand">Notetaking Web App</span></a></div>
			<div class="nav-item"><span>${username}</span></div>
		</header>
		<nav class="navbar" style="padding: 0px">
			<form action="Navigate" method="post" accept-charset=utf-8>
					<input class="nav-link active" type="submit" name="GoToNotePage" value="Notes">
					<input class="nav-link active" type="submit" name="GoToCreateHeaderPage" value="Edit Headers">
					<input class="nav-link active" type="submit" name="GoToArchivePage" value="Archive">
					<input type="hidden" name="username" value=${username}>
			</form>
		</nav>
		<div id="btnClosePrefab" style="display:none;">
			<button type="button" class="close" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
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
				<#list sequence as item>
					<tr>
						<td>
							${item.getCategoryName()}
						</td>
						<td>
							<#if item.getCategoryName() != "General Notes">
								<button type="button" style="float: left;" onclick="removeHeader()" class="close" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</#if>
						</td>
					</tr>
				</#list>
			</table>
			<input type="text" id="headerName" placeholder="header name">
			<button id="createHeader">Create</button>
		</div>
	</body>
</html>