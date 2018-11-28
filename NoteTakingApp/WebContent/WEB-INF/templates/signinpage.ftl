<!DOCTYPE html>
<html>
<head>
	<script src="js/signinpage.js"></script>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="css/signinpage.css">
	<title>User Login</title>
</head>
<body>
	<#assign x = incorrectUsernameOrPassword>
	<#if x == true>
		 <script>badUsernameOrPasswordPopUp()</script>
	</#if>
	<form class="login" action="Login" method="post">
		<h1 class="login-title">User Login</h1>
		<input class="login-input" placeholder="Username" type="text" name="username"/>
		<input class="login-input" placeholder="Password" type="password" name="password"/>
		<input class="login-button" type="submit" name="signIn" value="Sign In"/>
		<input class="login-button" type="submit" name="signUp" value="Sign Up"/>
	</form>
</body>
</html>