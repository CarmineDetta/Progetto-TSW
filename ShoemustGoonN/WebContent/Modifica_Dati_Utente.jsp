<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="it" xml:lang="it">

<head>
	<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.PortafoglioBean, model.UtenteBean"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="style/modifica-dati-utente.css" type="text/css">
		<script src="javascript/Dati_Utente.js" type="text/javascript"></script>
		<title>ShoeMustGoOn | Modifica Dati</title>
</head>

<body>
	<jsp:include page="header.jsp" />


	<%
		UtenteBean update = (UtenteBean)request.getSession().getAttribute("UtenteLoggato");
	%>


	
<div class="update_form">

		<form action="utente" method="post"  onsubmit="return formValidation();">
		<input type="hidden" name="action" value="update">
			
	<div class="cosa_aggiornare">
		<label for="scelta">Cosa vuoi aggiornare?</label>
			<select name="Scelta">
				<option value="email">Email</option>
				<option value="password">Password</option>
			</select>
		</div>
			
		<div class="valori-aggior">
			<label for="valore">Inserisci Nuovi Valori:</label>
			<input id="valore" type="text" name="valore" maxlength=50 required placeholder="Inserisci">
		</div>
		
			<input type="hidden" value ="<%=update.getID_Utente() %>" name="utente">
		
			<div id="input_field">
				<input class="pulsanti" type="submit" value="Aggiorna">
				<input class="pulsanti" type="reset" value="Reset">
			</div>
		</form>

</div>

</body>
</html>