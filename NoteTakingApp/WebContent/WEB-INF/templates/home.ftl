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
	<script src="js/home.js"></script>
	<style>
		.note{
			position:relative;
			margin-top:50px;
			
		}
		table{
			border-collapse: collapse;
		}

		tr.header{
			height: 50px;
		}
		
		tr.notes{
			height: 300px;
		}

		td{
			vertical-align: top;
		}
		
		.grid{
			display: grid;
			grid-template-columns: 300px 300px 300px 300px 300px;
		
		}
		
		.header{
			position: relative;
			margin: 22px 16px;
		}
		
	</style>

</head>
<body>
	<header class="navbar-expand-lg fixed-top navbar-dark bg-dark">
		<div style="display: inline;"><a><span class="navbar-brand">Notetaking Web App</span></a></div>
		<div style="display: inline;"><input class="searchbar" placeholder="Search" type="text" name="search"></div>
		<div style="display: inline; float: right; color: white; padding-top: 6px; padding-right: 6px;" class="nav-item"><span>${user}</span></div>
	</header>
	
	<nav class="nav flex-column" style="position: fixed;">
		<form action="Navigate" method="get">
				<input class="nav-link active" type="submit" name="GoToNotePage" value="Notes">
				<input class="nav-link active" type="submit" name="GoToCreateHeaderPage" value="Create Header">
				<input type="hidden" name="username" value=${user}>
		</form>
	</nav>

	<div class="main" style="margin-top: 40px;">
		<input type="hidden" id="accountId" value="${userid}"/>
		
		<div>
			<button id="newnotebtn">Create New Note</button>
			<div id="newnotefields" style="display:none">
				<input id="newnotetitle" type="text" placeholder="Note Title">
				<textarea id="newnotebody" type="text" placeholder="Note Body"></textarea>
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
						<div class="note">
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
		<div class="modal-content">
			<div class="noteTitle" id="modal-title" style="height: 20px;">Modal Title</div>
			<div class="noteContent" id="modal-text" style="height: 20px;">Modal Text</div>
			<input type="hidden" id="noteId" value=""/>
			<input type="hidden" id="categoryId" value=""/>
			<input type="hidden" id="color" value=""/>
			<input type="hidden" id="statusId" value=""/>
			<div style="text-align: right; padding-top: 20px">
				<input type="image" src="images/paintbrush.png" style="width: 50px; height: 50px; transform: translate(0px, 20px);">
				<input
					onclick="changeColor()"
					type="image" 
					src="images/archive.png" 
					style="width: 50px; height: 50px; transform: translate(0px, 20px);">
				<div class="dropdown">
					<button id="optionbutton" 
						class="optionbutton" 
						onclick="noteOptions()" 
						style="width: 80px; height: 25px;">
						Options
					</button>
					<div id="options" class="options">
						<a>Delete note</a>
					</div>
				</div>
				<button id="savenote" style="width: 50px; height: 25px;">Save</button>
				<button id="closenote" style="width: 60px; height: 25px;">Close</button>
			</div>
		</div>
	</div>
</body>
</html>