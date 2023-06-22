<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- prova -->
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Recensione</title>
</head>
<body>

	<jsp:include page="header.jsp" />
	
	<form action="RecensioneControl" method="post">
		<input type = "hidden" name = "action" value = "Insert">
		<input type = "hidden" name = "idProd" value ="<%= request.getAttribute("idProd")%>">
		
		<select name="Valutazione">
					<option value="0.5">0.5</option>
					<option value="1">1</option>
					<option value="1.5">1.5</option>
					<option value="2">2</option>
					<option value="2.5">2.5</option>
					<option value="3">3</option>
					<option value="3.5">3.5</option>
					<option value="4">4</option>
					<option value="4.5">4.5</option>
					<option value="5">5</option>
		</select>
		
		<textarea name="descrizione" maxlength="300"  placeholder="Inserisci una descrizione" required></textarea>
				
		<input type = "submit" value="invia">
		<input type = "reset" value="reset">
	</form>	
	
	<jsp:include page="footer.jsp"/>
	
</body>
</html>