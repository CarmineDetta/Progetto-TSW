<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.PortafoglioBean, model.UtenteBean"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Inserimento Carta | ShoeMustGoOn</title>
</head>


<body>

	<jsp:include page="header.jsp" />
	
	<%
		UtenteBean utente = (UtenteBean)request.getSession().getAttribute("UtenteLoggato");
	%>
	
	<h2>Inserisci i dati della nuova carta</h2>
		<form action="utente" method="post">
			<input type="hidden" name="action" value="insert"> 
			
			<label for="n_carta">Numero Carta:</label><br> 
			<input name="n_carta" type="text" maxlength="16" required placeholder="Inserisci il numero della carta"><br> 
			
			<label for="nome_intestatario">Nome intestatario:</label><br> 
			<input name="nome_intestatario" type="text" maxlength="50" required placeholder="Inserisci il nome dell'intestatario"><br> 
			
			<label for="scadenza">Data scadenza:</label><br> 
			<input name="scadenza" type="date"><br> 
			
			<label for="cvv">Cvv:</label><br> 
			<input name="cvv" type="text" maxlength="3"><br> 
			
			<input name="utente" type="hidden" value="<%utente.getNome();%>">
			<br><input type="submit" value="Add"><input type="reset" value="Reset"><br>
	
		</form>
</body>
</html>