<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<<<<<<< HEAD



=======
<% String idProd = request.getParameter("idProd"); %>

<!-- prova -->
>>>>>>> branch 'master' of https://github.com/CarmineDetta/Progetto-TSW
<!DOCTYPE html>
<html lang="it" xml:lang="it">
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="style/insert-recensione.css" rel="stylesheet" type="text/css">
	
	<title>ShoeMustGoOn | Recensione</title>
</head>
<body>

	<jsp:include page="header.jsp" />

<div class="totalita">

		<h3>Inserisci Nuova Recensione</h3>

	<%//utenteloggato
	%>

	<form action="RecensioneControl" method="get">
			<input type="hidden" name="action" value="Insert"> 
			<input type="hidden" name="idProd" value="<%= request.getAttribute("idProd")%>">		

	<form action="recensione" method="post">
		<input type="hidden" name="action" value="Insert"> 
		<input type="hidden" name="idProd" value="<%= idProd %>"> <!-- Aggiungi questa riga -->		


		<h5>Scegli Valutazione in Stelle:</h5>
			<select name="Valutazione">
						<option value="0">0</option>
						<option value="1">1</option>					
						<option value="2">2</option>					
						<option value="3">3</option>					
						<option value="4">4</option>
						<option value="5">5</option>
			</select>
		<br>
		
		<h5>Inserisci un Commento:</h5>
			<textarea name="descrizione" maxlength="300"  placeholder="Inserisci" required></textarea>
					<br>
				<input type="submit" value="Inserisci" id="butt_insert">
				<input type="reset" value="Reset" id="butt_reset">
	</form>	
</div>
	<jsp:include page="footer.jsp"/>
	
</body>
</html>