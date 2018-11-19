<html>
<head>
	<meta charset="UTF-8">
	<script src="js/jquery.js"></script>
	<link rel="stylesheet" href="css/bootstrap/bootstrap.css">
	<script src="./js/bootstrap/bootstrap.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet"> 
	<link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/default.css">
	<link rel="stylesheet" type="text/css" href="css/home.css">
	<script src="js/jscolor.js"></script>
	<script src="js/home.js"></script>
</head>
<body>
	<header class="navbar-expand-lg fixed-top navbar-dark bg-dark">
		<div><a><span class="navbar-brand">Notetaking Web App</span></a></div>
		<div><input class="searchbar" placeholder="Search" type="text" name="search"></div>
		<div class="nav-item"><span>${user}</span></div>
	</header>
	
	<nav id="navigate" class="nav flex-column" style="position: fixed;">
		<form action="Navigate" method="get">
				<input class="nav-link active" type="submit" name="GoToNotePage" value="Notes">
				<input type="hidden" name="username" value=${user}>
		</form>
	</nav>

	<div class="main">
		<input type="hidden" id="accountId" value="${userid}"/>
		<div>
			<button id="newnotebtn">Create New Note</button>
			<div id="newnotefields" style="display:none">
				<input id="newnotetitle" type="text" placeholder="Note Title">
				<div id="newnotebody" type="text" contenteditable="true"></div>
				<select id="newnotecategory">
					<#list categories as categories>	
						<option value="${categories.categoryID}">${categories.categoryName}</option>
					</#list>
				</select>
			</div>
			<button id="createnote" style="display:none">Create</button>
			<button id="cancelnote" style="display:none">Cancel</button>
		</div>
		
		<#list categories as categories>	
			<div class="header">${categories.categoryName}</div>
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
						</div>
					</#if>
				</#list>
			</div>
		</#list>	
	</div>
	
	<div id="modal">
		<div id="modal-content" class="modal-content">
			<div class="noteTitle" id="modal-title">Modal Title</div>
			
			<div class="noteContent" id="modal-text">Modal Text</div>
			
			<div class="noteBtns">
				<input class="jscolor {onFineChange:'changeColor()'}">
				<button id="archiveNote">Archive</button>
				<button id="saveNote">Save</button>
				<button id="closeNote">Close</button>
				<span id="updateLog"></span>
			</div>
		</div>
	</div>
</body>
</html>
