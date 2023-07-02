<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it" xml:lang="it">
<%@ page contentType="text/html; charset=UTF-8" import="model.UtenteBean"%>
<head>
	<meta charset="UTF-8">
	<link href="style/dati-utente.css" rel="stylesheet" type="text/css">
	<title>ShoeMustGoOn | I mie Dati</title>
</head>

<style>
body{
	background-image: url("image/sfondo_datiutente.jpeg");	
}
</style>
<body>

<jsp:include page="header.jsp" />
	 
	<%
		UtenteBean utente = (UtenteBean)request.getSession().getAttribute("UtenteLoggato");
		String email = utente.getEmail();
	%>
<div class="division">
	<div class=titolo>	
		<h2>I miei Dati</h2>
	</div>
	
	<div class="elementi">
	
	<div class="persona">
		<div class="nome">
			<p><%=utente.getNome()%> <%=utente.getCognome()%></p>
		</div>
		
		<div class="email">
			<p><%=email%></p>
		</div>
	</div>
	
		<div class="particolar">		
			
			<table>
				<caption>Tabella per i dati sull'utente</caption>

				<tr>
					<th>ID_Utente</th>
					<td><%=utente.getID_Utente()%></td>
				</tr>
				<tr>
					
				</tr>
				<tr>
					<th>Codice Fiscale</th>
					<td><%=utente.getCF()%></td>
				</tr>
				<tr>
					<th>Data nascita</th>
					<td><%=utente.getDataNascita()%></td>
				</tr>
			</table>
		
		
		<div class="pass">
			<table>
				<caption>Tabella che contiene le credenziali dell'utente</caption>
				<th>Password:</th>
				<td><%=utente.getPassword()%></td>
			</table>
		</div>
	</div>
	
	</div>	<!-- chiude la class elementi -->

	<div id="update_button">
		<button><a href="utente?action=update" target="_blank">Vuoi aggiornare i tuoi dati?</a></button><br>
		<br>
	</div>

</div>
</body>
</html>