<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it" xml:lang="it">
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.PortafoglioBean, model.UtenteBean"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="style/insert_card.css">
	<title>ShoeMustGoOn | Inserimento Carta</title>
			<script src="javascript/CartaInserimento.js" type="text/javascript"></script>
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
	
	<div class="div_inserimento">	
		<div class="div_form">
		
			<h2 class="title"> Inserisci i dati della nuova carta</h2>
		
		<form action="payments" method="post" onsubmit="return validateCard()">
			<input class="label_mod" type="hidden" name="action" value="insert"> 
			
			<div class="inserimento">
				<label  class="label_mod" for="n_carta">Numero Carta:</label>
				<input id="n_carta" class="box" name="n_carta" type="text" maxlength="16"  placeholder="Numero Carta" required><br>
			</div>
			
			<div class="inserimento">
				<label  class="label_mod" for="nome_intestatario">Nome intestatario:</label>
				<input class="box"  name="nome_intestatario" type="text" maxlength="50" placeholder="Nome Intestatario" required><br> 
			</div>
			
			<div class="inserimento">
				<label  class="label_mod" for="scadenza">Data scadenza:</label> 
				<input class="box"  name="scadenza" type="date" required><br>
			</div>
			
			<div class="inserimento">
				<label class="label_mod"  for="cvv">Cvv:</label> 
				<input class="box"  name="cvv" type="text" maxlength="4" placeholder="CVV" required><br> 
			</div>
			
			<input name="utente" type="hidden" value="<%=utente.getNome()%>">
			
			<div>
				<input class="button" type="submit" value="Add">
				<input class="button" type="reset" value="Reset">
			</div>
		 
		 </form>
		</div>
		
	
	</div>
</body>
</html>