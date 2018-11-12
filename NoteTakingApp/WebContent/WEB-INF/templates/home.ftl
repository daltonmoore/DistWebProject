<html>
<head>
	<meta charset="UTF-8">
	<script src="js/jquery.js"></script>
	<link rel="stylesheet" href="Bootstrap/css/bootstrap.css">
	<script src="Bootstrap/js/bootstrap.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet"> 
	<link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/default.css">
	<link rel="stylesheet" type="text/css" href="css/home.css">
	<script src="js/home.js"></script>
<style>
	tr{
		height: 300px;
	}
	td{
		vertical-align: top;
	}
</style>

</head>
<body>
	<nav class="navbar-expand-lg fixed-top navbar-dark bg-dark">
		<ul class="navbar-nav">
			<li><a><span class="navbar-brand">Brand</span></a></li>
			<li><input class="searchbar" placeholder="Search" type="text" name="search"></li>
			<li><span>Username</span></li>
		</ul>
	</nav>
	
	<nav class="nav flex-column" style="position: fixed;">
		<form action="Navigate" method="get">
				<input class="nav-link active" type="submit" name="GoToNotePage" value="Notes">
				<input class="nav-link active" type="submit" name="GoToCreateHeaderPage" value="Create Header">
				<input type="hidden" name="username" value=${user}>
		</form>
	</nav>

	<div class="main" style="margin-top: 40px;">
		<div>
			<button id="newnotebtn" onclick="showNewNoteFields()">Create New Note</button>
			<div id="newnotefields" style="display:none">
				<input id="newnotetitle" type="text" placeholder="Note Title">
				<textarea id="newnotebody" type="text" placeholder="Note Body"></textarea>
				<select id="newnotecategory">
					<option>Category 0</option>
				</select>
			</div>
			<button id="createnote" onclick="createNewNote()" style="display:none">Create</button>
			<button id="cancelnote" onclick="cancelNewNote()" style="display:none">Cancel</button>
		</div>
		
		<div class="categories">
		<table>
			<#list categories as categories>
			<tr><td>
				<div class="category">
					<div class="header">${categories.categoryName}</div>	
						
						<div onclick="noteClick(this)" class="note" style="transform: translate(0px, 40px);">
							<div class="noteTitle">Note Title 1</div>
							<div class="noteContent">This is a test</div>
						</div>
						<div onclick="noteClick(this)" class="note" style="transform: translate(256px, 40px);">
							<div class="noteTitle">Note Title 2</div>
							<div class="noteContent">This is also a test</div>
						</div>
					</div>
			</tr></td>
			</#list>
		</table>
		
	<!--	
		<div class="header">Uncategorized</div>
		<div onclick="noteClick(this)" class="note" style="transform: translate(0px, 40px);">
			<div id="noteTitle" class="noteTitle">Data Comm</div>
			<div class="noteContent">abcd</div>
		</div>
		<div onclick="noteClick(this)" class="note" style="transform: translate(256px, 40px);">
			<div id="noteTitle" class="noteTitle">Computer Arch</div>
			<div class="noteContent">efgh</div>
		</div>
		<div onclick="noteClick(this)" class="note" style="transform: translate(512px, 40px);">
			<div id="noteTitle" class="noteTitle">Database</div>
			<div class="noteContent">hijk</div>
		</div>
		<div onclick="noteClick(this)" class="note" style="transform: translate(768px, 40px);">
			<div id="noteTitle" class="noteTitle">Systems Software</div>
			<div class="noteContent">lmno</div>
		</div>
	
		<table class="table" id="test">
			<tr>
				<th height="100">Note 0</th>
				<th height="100">Note 1</th>
				<th height="100">Note 2</th>
				<th height="100">Note 3</th>
				<th height="100">Note 4</th>
			</tr>
			<tr>
			</tr>
		</table>
	-->

	</div>
	<div id="modal">
		<div class="modal-content">
			<div class="noteTitle" id="modal-title" style="height: 20px;">Modal Title</div>
			
			<div class="noteContent" id="modal-text" style="height: 20px;">Modal Text</div>
			
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
				<button style="width: 50px; height: 25px;">Save</button>
				<button onclick="closeNote()" style="width: 60px; height: 25px;">Close</button>
			</div>
		</div>
	</div>
</body>
</html>