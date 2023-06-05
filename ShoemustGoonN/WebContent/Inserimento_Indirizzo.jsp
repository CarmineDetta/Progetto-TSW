<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.RecapitoBean, model.UtenteBean"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="style/insert_indirizzo.css">
	<title>ShoeMustGoOn | Inserisci Nuovo Recapito</title>
</head>

<style>
body{
	background-image: url("image/sfondo_insert_card.jpeg");
}
</style>

<body>

	<jsp:include page="header.jsp" />
	
	<%
		UtenteBean utente = (UtenteBean)request.getSession().getAttribute("UtenteLoggato");
	%>
	
	<div class="div_complete">
		<div class="div_form">
		<h2 class="title">Inserisci i dati del nuovo Indirizzo</h2>
		<form action="recapiti" method="post">
		

			<input type="hidden" name="action" value="insert"> 
			
			<div class="insert">
				<label class="label_mod" for="citta">Città:</label><br> 
				<input class="box" name="citta" type="text" maxlength="25" required placeholder="Inserisci Città"><br> 
			</div>
			
			<div class="insert">
				<label class="label_mod" for="cap">CAP:</label><br> 
				<input class="box" name="cap" type="text" maxlength="5" required placeholder="Inserisci CAP"><br> 
			</div>
			
			<div class="insert">
				<label class="label_mod" for="via_piazza">Via/Piazza:</label><br> 
				<input class="box" name="via_piazza" type="text" maxlength="30" required placeholder="Inserisci Via/Piazza"><br> 
			</div>
			
			<div class="insert">
				<label class="label_mod" for="n_civico">N. Civico:</label><br> 
				<input class="box" name="n_civico" type="text" maxlength="3" placeholder="Inserisci Numero Civico"><br> 
			</div>
			
			<input name="utente" type="hidden" value="<%utente.getNome();%>">
			
			<br>
			
			<div>
				<input class="button" type="submit" value="Aggiungi">
				<input class="button" type="reset" value="Reset"><br>
			</div>
			
		</form>
	</div>
</div>

</body>
</html>