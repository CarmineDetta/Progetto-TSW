<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ShoeMustGoOn | Registrazione</title>
</head>
<body>
	<div>
		<h1>Registrazione</h1>
	</div>
	
	<div>
		<form action="login" method="post">
		<input type ="hidden" name="action" value="register">
			<table>
				<tr>
					<td>Inserisci il Nome:</td>
					<td><input type="text" name="nome"></td>
				</tr>
				<tr>
					<td>Inserisci Cognome:</td>					
					<td><input type="text" name="cognome"></td>
				</tr>
				<tr>
					<td>Inserisci la data di nascita:</td>					
					<td><input type="date" name="nascita"></td>
				</tr>
				<tr>
					<td>Inserisci il codice fiscale:</td>					
					<td><input type="text" name="cf"></td>
				</tr>
				<tr>
					<td>Inserisci l'email:</td>					
					<td><input type="email" name="email"></td>
				</tr>
				<tr>
					<td>Inserisci il password:</td>					
					<td><input type="password" id="psw" name="psw"></td>
					<input type="hidden" name="tipo" value="utente">
				</tr>
				<tr>
					<td><input type="submit" value="Registrati"></td>
					<td><input type="reset"></td>
				</tr>
			</table>
	</div>

</body>
</html>