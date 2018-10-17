<html>
<head>
	<style>
		.horizontalUL{
			list-style-type: none;
			margin: 0;
			padding: 0;
			overflow: hidden;
			background-color: #333;
		}
		.horizontalLI {
    		float: left;
		}
		.horizontalLI a {
    		display: block;
		    color: white;
		    text-align: center;
		    padding: 14px 16px;
		    text-decoration: none;
		}
		.horizontalLI a:hover {
    		background-color: #111;
		}
		.searchbar{
			margin-left: 200px;
			height: 46px;
			width: 300px;
		}
		.verticalUL{
			list-style-type: none;
		    margin: 0;
		    padding: 0;
		    width: 200px;
		    background-color: #f1f1f1;
		}
		.verticalLI a{
			display: block;
		    color: #000;
		    padding: 8px 16px;
		    text-decoration: none;
		}
		.verticalLI a:hover{
			background-color: #555;
    		color: white;
		}
	</style>
</head>
<body>
	<ul class="horizontalUL">
		<li class="horizontalLI"><input class="searchbar" placeholder="Search" type="text" name="search"/></li>
		<li class="horizontalLI" style="float:right"><a>Home</a></li>
	</ul>
	<ul class="verticalUL">
		<li class="verticalLI"><a>Note Page</a></li>
		<li class="verticalLI"><a>Create Header</a></li>
		<li class="verticalLI"><a>Archive</a></li>
		<li class="verticalLI"><a>Trash</a></li>
		<li class="verticalLI"><a>Settings</a></li>
		<li class="verticalLI"><a>Create New Note</a></li>
	</ul>
</body>
</html>