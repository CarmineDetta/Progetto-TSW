<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ShoeMustGoOn | Login</title>
</head>
	<body>
	
	<jsp:include page="header.jsp" />
	
	<div>
		<h1>Login</h1>
	</div>
	  		
		<form action="login" method="post">
			<input type = "hidden" name="action" value="login">
		
			<table>
				<tr>
					<td>Inserisci il nome:</td>
					<td><input type="text" id="email" name="email"></td>
				</tr>
				<tr>
					<td>Inserisci Password:</td>					
					<td><input type="password" id="password" name="password"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Login"></td>
					<td><input type="reset"></td>
				</tr>
			</table><br>
			
			<button><a href="Registrazione.jsp">Non sei ancora registrato?</a></button>
			
		
		</form>
		
		
		
		
		
		
	</body>
</html>