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
	<script src="./js/home.js"></script>
</head>
<body>
	<header class="navbar-expand-lg fixed-top navbar-dark bg-dark">
		<div class="headerItems"><a><span class="navbar-brand">Notetaking Web App</span></a></div>
		<div class="headerItems"><input class="form-control searchbar" placeholder="Search" type="text" name="search"></div>
		<div class="headerItems">
			<form class="headerItems" action="Navigate" method="get">
				<input class="btn btn-primary" id="logoutbtn" type="submit" name="logout" value="Logout">
			</form>
		</div>
		<div class="nav-item accountName"><span>${user}</span></div>
	</header>
	
	<nav id="navigate" class="nav flex-column">
		<form action="Navigate" method="get">
				<input class="nav-link active btn btn-dark" type="submit" name="GoToNotePage" value="Notes">
				<input class="nav-link active btn btn-dark" type="submit" name="GoToCreateHeaderPage" value="Edit Headers">
				<input class="nav-link active btn btn-dark" type="submit" name="GoToArchivePage" value="Archive">
				<input id="username" type="hidden" name="username" value=${user}>
		</form>
	</nav>

	<div class="main">
		<input type="hidden" id="accountId" value="${userid}"/>
		
		<div>
			<div class="input-group" id="newnotefields">
				<input class="input-group-text" id="newnotetitle" type="text" placeholder="Note Title">
				<textarea class="input-group-text" id="newnotebody" type="text" contenteditable="true" placeholder="Note Body"></textarea>
				<select class="btn btn-outline-secondary dropdown-toggle" id="newnotecategory">
					<div class="dropdown-menu">
						<#list categories as categories>	
							<option class="dropdown-item" value="${categories.categoryID}">${categories.categoryName}</option>
						</#list>
					</div>
				</select>
			</div>
			<button class="btn btn-outline-secondary" id="newnotebtn">Create New Note</button>
			<button class="btn btn-outline-secondary" id="createnote">Create</button>
			<button class="btn btn-outline-secondary" id="cancelnote">Cancel</button>
		</div>
		
		<#list categories as categories>	
			<div class="header" id="${categories.categoryName}">${categories.categoryName}
				<div class="grid ${categories.categoryID}">	
					<#list usernotes as notes>	
						<#if notes.categoryID == categories.categoryID>	
							<div class="note" style="background-color: ${notes.color}">
								<div class="noteTitle">${notes.noteTitle}</div>
								<div class="noteContent">${notes.noteContent}</div>
								<input type="hidden" class="noteId" value="${notes.noteID}"/>
								<input type="hidden" class="categoryId" value="${notes.categoryID}"/>
								<input type="hidden" class="color" value="${notes.color}"/>
								<input type="hidden" class="statusId" value="${notes.statusID}"/>
								<!--<input type="button" class="quickArchive" value="Archive">-->
								<input type="button" class="btn btn-outline-secondary quickTrash" value="Trash">
							</div>
						</#if>
					</#list>
				</div>
			</div>
		</#list>	
	</div>

	<div id="archivedNoteAlert" class="alert alert-success alert-dismissible fade show" role="alert">
		Archived Note Successfully
		<button type="button" class="close" data-dismiss="alert">
			<span aria-hidden="true">&times;</span>
		</button>
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
				<button class="btn btn-outline-secondary" id="savenote">Save</button>
				<button class="btn btn-outline-secondary" id="closenote">Close</button>
			</div>
		</div>
	</div>
</body>
</html>