<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet"> 
	<link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/home.css">
	<script src="js/home.js"></script>
</head>
<body>
	<ul class="horizontalUL">
		<li class="horizontalLI"><input onfocus="searchBar()" class="searchbar" placeholder="Search" type="text" name="search"/></li>
		<li class="horizontalLI" style="float:right"><a>Test</a></li>
	</ul>
	
	<div>
		<ul class="sideBar">
			<li class="sideBarItem" role="menuitem" onclick="sideBarClick() "><a>Note Page</a></li>
			<li class="sideBarItem" role="menuitem"><a>Create Header</a></li>
			<li class="sideBarItem" role="menuitem"><a>Archive</a></li>
			<li class="sideBarItem" role="menuitem"><a>Trash</a></li>
			<li class="sideBarItem" role="menuitem"><a>Settings</a></li>
			<li class="sideBarItem" role="menuitem"><a>Create New Note</a></li>
		</ul>
		<div class="notes-container">
			<div class="header">Header One</div>
			<div onclick="noteClick(this)" class="note" style="transform: translate(0px, 40px);">
				<div class="noteTitle">Note Title 1</div>
				<div class="noteContent">This is a test</div>
			</div>
			<div onclick="noteClick(this)" class="note" style="transform: translate(256px, 40px);">
				<div class="noteTitle">Note Title 2</div>
				<div class="noteContent">This is also a test</div>
			</div>
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
					<button onclick="closeNote()" style="width: 50px; height: 25px;">Close</button>
				</div>
			</div>
		</div>
		<p id="out" style="bottom: 0px; position: fixed;">Paragraph</p>
	</div>
</body>
</html>