<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.RecapitoBean, model.UtenteBean"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Inserimento recapito | ShoeMustGoOn</title>
</head>


<body>

	<jsp:include page="header.jsp" />
	
	<%
		UtenteBean utente = (UtenteBean)request.getSession().getAttribute("UtenteLoggato");
	%>
	
	<h2>Inserisci i dati della nuova carta</h2>
		<form action="recapiti" method="post">
			<input type="hidden" name="action" value="insert"> 
			
			<label for="citta">Città:</label><br> 
			<input name="citta" type="text" maxlength="25" required placeholder="Inserisci la città"><br> 
			
			<label for="cap">CAP:</label><br> 
			<input name="cap" type="text" maxlength="5" required placeholder="Inserisci il cap della città"><br> 
			
			<label for="via_piazza">Via/Piazza:</label><br> 
			<input name="via_piazza" type="text" maxlength="30" required placeholder="Inserisci la via/piazza"><br> 
			
			<label for="n_civico">N. Civico:</label><br> 
			<input name="n_civico" type="text" maxlength="3"><br> 
			
			<input name="utente" type="hidden" value="<%utente.getNome();%>">
			
			<br><input type="submit" value="Aggiungi"><input type="reset" value="Reset"><br>
	
		</form>
</body>
</html>