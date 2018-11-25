<html>
<head>
	<meta charset="UTF-8">
	<script src="./js/jquery.js"></script>
	<link rel="stylesheet" href="css/bootstrap/bootstrap.css">
	<script src="./js/bootstrap/bootstrap.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet"> 
	<link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/default.css">
	<link rel="stylesheet" type="text/css" href="css/home.css">
	<script src="./js/jscolor.js"></script>
	<script src="./js/archive.js"></script>
</head>
<body>
	<header class="navbar-expand-lg fixed-top navbar-dark bg-dark">
		<div style="display: inline;"><a><span class="navbar-brand">Notetaking Web App</span></a></div>
		<div style="display: inline;"><input class="searchbar" placeholder="Search" type="text" name="search"></div>
		<div style="display: inline; float: right; color: white; padding-top: 6px; padding-right: 6px;" class="nav-item"><span>${user}</span></div>
	</header>
	
	<nav id="navigate" class="nav flex-column" style="position: fixed;">
		<form action="Navigate" method="get">
				<input class="nav-link active" type="submit" name="GoToNotePage" value="Notes">
				<input class="nav-link active" type="submit" name="GoToCreateHeaderPage" value="Edit Headers">
				<input class="nav-link active" type="submit" name="GoToArchivePage" value="Archive">
				<input id="username" type="hidden" name="username" value=${user}>
		</form>
	</nav>

	<div class="main">
		<input type="hidden" id="accountId" value="${userid}"/>
		<div class="grid">	
			<#list usernotes as notes>	
				<div class="note" style="background-color: ${notes.color}">
					<div class="noteTitle">${notes.noteTitle}</div>
					<div class="noteContent">${notes.noteContent}</div>
					<input type="hidden" class="noteId" value="${notes.noteID}"/>
					<input type="hidden" class="categoryId" value="${notes.categoryID}"/>
					<input type="hidden" class="color" value="${notes.color}"/>
					<input type="hidden" class="statusId" value="${notes.statusID}"/>
				</div>
			</#list>
		</div>
	</div>
	
	<div id="modal">
		<div class="jscolor {onFineChange:'update(this)'}" id="modal-content">
			<div class="noteTitle" id="modal-title">Modal Title</div>
			<div class="noteContent" id="modal-text">Modal Text</div>
			<input type="hidden" id="noteId" value=""/>
			<input type="hidden" id="categoryId" value=""/>
			<input type="hidden" id="color" value=""/>
			<input type="hidden" id="statusId" value=""/>
			<div id="charcounter"></div>
			<div id="buttons">
				<input id="trash" type="image" src="images/trash-4x.png">
				<input class="jscolor {onFineChange:'update(this)'}" id="changecolor" type="image" src="images/eyedropper-4x.png">
				<input id="archive" type="image" src="images/box-4x.png">
				<button id="savenote">Save</button>
				<button id="closenote">Close</button>
			</div>
		</div>
	</div>
</body>
</html>