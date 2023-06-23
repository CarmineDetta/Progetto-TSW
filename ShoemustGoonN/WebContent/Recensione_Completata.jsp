<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8" import="java.util.*, model.*"%>
    
<!DOCTYPE html>
<html lang="it" xml:lang="it">

	<head>
 		<meta charset="utf-8">
 		<link href="style/Acquisto_Completato.css" rel="stylesheet" type="text/css">
 		<title>Recensione Completato!</title>
	</head>
	
	<body>
		<jsp:include page="header.jsp"/>

<div class="contenuto">

		<div class="text">
			<h1> Grazie per aver aggiunto una tua recensione</h1><br>
				<img id="imagine" src="https://www.svgrepo.com/show/381969/complete-ok-accept-good-tick.svg" width="70" height="70" title="complete-icon" alt="complete icon"><br>							 
		</div>
		
		<div class="pollice">
			<img src="image/smile_ok.png">							 
		</div>
		
			<a href="Catalogo_Utente.jsp" ><input type="button" value="Torna all'Homepage"></a>
		
</div>
			
		<jsp:include page="footer.jsp"/>
	</body>
	
</html>