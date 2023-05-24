<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <link href="style/login.css" rel="stylesheet" type="text/css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ShoeMustGoOn | Login</title>
</head>

<style>
body{
	background-image: url("image/sfondo-login.jpg");
}
</style>
	<body>
	
	<jsp:include page="header.jsp" />
	
<!--
	<div>
		<h1>Login</h1>
	</div>
-->	  		
		<form id="loginForm" class="loginForm" action="login" method="post">
			<input type = "hidden" name="action" value="login">
				
				<h1 id="accedi">Accedi:</h1>
			
			<div class="content">

				
				 <div class="input-field">
				 	<img class="icon" src="https://www.svgrepo.com/show/508196/user-circle.svg" width="28" height="28" title="username-icon" alt="search icon">				 
					<input type="text" placeholder="Username" id="email" name="email">
				 </div>
		
				 <div class="input-field">	
				 	<img class="icon" src="https://www.svgrepo.com/show/340797/password.svg" width="28" height="28" title="password-icon" alt="search icon">
					<input type="password" placeholder="Password" id="password" name="password">
				 </div>
		
			</div>
				
			<div class="action">
				<div class="action-1">
					<button type="submit" id="loginButton">Login</button>
					<button type="reset" id="resetButton">Reset</button>
				</div>	
					<br>

				<button><a href="Registrazione.jsp">Non sei ancora registrato? Registrati</a></button>
			</div>		
		</form>		
		
	<jsp:include page="footer.jsp" />		
	
	</body>
</html>