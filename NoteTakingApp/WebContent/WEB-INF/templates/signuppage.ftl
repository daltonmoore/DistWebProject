<html>
<head>
	<script src="./js/jquery.js"></script>
	<script src="js/signuppage.js"></script>
	<link rel="stylesheet" href="css/signuppage.css">
</head>
<body>
	<form class="signup" action="Login" method="post">
		<h1 class="signup-title">User Sign Up</h1>
		<input class="signup-input" placeholder="Username" type="text" name="newUsername" id="username" required/>
		<input class="signup-input p" placeholder="Password" type="password" name="newPassword" id="password" required/>
		<input class="signup-input p" placeholder="Reenter Password" type="password" id="confirm_password" required/>
		<input class="signup-input e" placeholder="Email" type="email" name="email" id="email" required/>
		<input class="signup-input e" placeholder="Reenter Email" type="email" id="confirm_email" required/>
		<input class="signup-input" placeholder="First Name" type="text" name="firstName" id="firstname" required/>
		<input class="signup-input" placeholder="Last Name" type="text" name="lastName" id="lastname" required/>
		<input class="signup-button" type="submit" name="createUser" value="Sign Up" required/>
	</form>
</body>
</html>