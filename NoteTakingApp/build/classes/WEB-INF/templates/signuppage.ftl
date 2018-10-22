<html>
<head>
	<script src="js/signuppage.js"></script>
	<style>
		.signup{
			margin: 20px auto;
    		width: 300px;
    		padding: 30px 25px;
  			background: white;
  			border: 1px solid #c4c4c4;
		}
		.signup-input {
		  	width: 285px;
		  	height: 50px;
			margin-bottom: 25px;
			padding-left:10px;
			font-size: 15px;
			background: #fff;
			border: 1px solid #ccc;
		  	border-radius: 4px;
		}
		h1.signup-title {
			margin: -28px -25px 25px;
			padding: 15px 25px;
			line-height: 30px;
			font-size: 25px;
			font-weight: 300;
			color: #000;
			text-align:center;
			background: #fff;
		}
		.signup-button {
			width: 100%;
			height: 50px;
			padding: 0;
			margin-bottom:25px;
			font-size: 20px;
			color: #fff;
			text-align: center;
			background: #f0776c;
			border: 0;
			border-radius: 5px;
			cursor: pointer; 
			outline:0;
		}
		.signup-button:hover{
			background: #8B2525;
		}
	</style>
</head>
<body>
	<form class="signup" action="MyServlet" method="post">
		<h1 class="signup-title">User Sign Up</h1>
		<input 
			class="signup-input" 
			placeholder="Username" 
			type="text" 
			name="newUsername" 
			id="username" 
			required/>
		<input 
			onchange="validatePassword()" 
			class="signup-input" 
			placeholder="Password" 
			type="password" 
			name="newPassword" 
			id="password" 
			required/>
		<input 
			onchange="validatePassword()" 
			class="signup-input" 
			placeholder="Reenter Password" 
			type="password" 
			id="confirm_password" 
			required/>
		<input 
			onchange="validateEmail()" 
			class="signup-input" 
			placeholder="Email" 
			type="email" 
			name="email" 
			id="email" 
			required/>
		<input 
			onchange="validateEmail()" 
			class="signup-input" 
			placeholder="Reenter Email" 
			type="email" 
			id="confirm_email" 
			required/>
		<input 
			class="signup-input" 
			placeholder="First Name" 
			type="text" 
			name="firstName" 
			id="firstname" 
			required/>
		<input 
			class="signup-input" 
			placeholder="Last Name" 
			type="text" 
			name="lastName" 
			id="lastname" 
			required/>
		<input 
			class="signup-button" 
			type="submit" 
			name="createUser" 
			value="Sign Up" 
			required/>
	</form>
</body>
</html>