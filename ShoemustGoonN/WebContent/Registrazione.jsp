<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="style/register.css">	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ShoeMustGoOn | Registrazione</title>
	<script src="javascript/ValidationRegistrazione.js" type="text/javascript"></script>
</head>

<style>
body{
	background-image: url("image/sfondo-register.jpeg");
}
</style>
<body>

    <jsp:include page="header.jsp"/>
	

		<form action="registration" method="post" class="registerForm" id="registerForm">
		<input type ="hidden" name="action" value="register">
		
			<h1 id="title">Compila Form Registrazione:</h1>
			
		<div class="content">

				<div class="input-field-r">
					<p class="text">Inserisci Nome:</p>
						<input type="text" placeholder="Nome" id="nomeInput" name="nome" required>
				</div>
				
				<div class="input-field-r">
					<p class="text">Inserisci Cognome:</p>				
						<input type="text" placeholder="Cognome" id="cognomeInput" name="cognome" required>
				</div>
				
				<div class="input-field-r">
					<p class="text">Inserisci Data di nascita:</p>					
						<input type="date" id="dateInput"name="nascita">
				</div>
				
				<div class="input-field-r">
					<p class="text">Inserisci Codice Fiscale:</p>					
						<input type="text" placeholder="CF" id="cvInput" name="cf" required>
				</div>
				
				<div class="input-field-r">				
					<p class="text">Inserisci Email:</p>				
						<input type="email" placeholder="Email" id="emailInput" name="email" required>
				</div>
				
				<div class="input-field-r">
					<p class="text">Inserisci Password:</p>					
						<input type="password" placeholder="Password" id="pswInput" name="psw" required>
					 	<input type="hidden" name="tipo" value="utente">
				</div>
				
				<div class="input-field-r">
					<p class="text">Ripeti Password:</p>					
						<input type="password" placeholder="Password" id="pswInput2" name="psw2" required>
					 	<input type="hidden" name="tipo" value="utente">
				</div>
				
					<br>
				<div class="action-r">
					<button type="button" id="regButton">Registrati</button>
					<button type="reset" id="resButton">Reset</button>
				</div>
	</div>
 </form>
 
    <jsp:include page="footer.jsp"/>
</body>
</html>