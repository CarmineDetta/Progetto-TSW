<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	UtenteBean update = (UtenteBean) request.getAttribute("a");
%>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="model.UtenteBean"%>
<head>
	<meta charset="UTF-8">
	<link href="style/dati_utentE.css" rel="stylesheet" type="text/css">
	<title>ShoeMustGoOn | I mie Dati</title>
	<script src="javascript/Dati_Utente.js" type="text/javascript"></script>
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
			<p><%=utente.getEmail()%></p>
		</div>
	</div>
	
		<div class="particolar">		
			
			<table>
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
				<th>Password:</th>
				<td><%=utente.getPassword()%></td>
			</table>
		</div>
	</div>
	
	</div>	<!-- chiude la class elementi -->

	<div id="update_button">
		<button><a href="utente?action=update&email=<%=utente.getEmail()%>">Vuoi aggiornare i tuoi dati?</a></button><br>
		<br>
	</div>
	<%
		if(update != null){	
	%>
	
	<div class="update_form">
		<form action="utente" method="post"  onsubmit="return formValidation();">
		
			<label for="scelta">Cosa vuoi aggiornare?</label>
			<select name="scelta">
				<option value="email">Email</option>
				<option value="password">Password</option>
			</select>
			
			<label for="valore">Inserisci Nuovi Valori</label>
			<input id="valore" type="text" name="valore" maxlength=50 required placeholder="Inserisci">
			
			<input type="hidden" value ="<%=update.getNome()%>" name="utente">
			
			<div id="input_field">
				<input class="pulsanti" type="submit" value="Aggiorna">
				<input class="pulsanti" type="reset" value="Reset">
			</div>
		</form>
		</div>

	<% 
		} 
	%>
</div>
</body>
</html>