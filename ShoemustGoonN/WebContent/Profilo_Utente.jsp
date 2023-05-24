<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.PortafoglioBean, model.UtenteBean"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Il mio profilo | ShoeMustGoOn</title>
</head>
	
<body>
	
	<jsp:include page="header.jsp" />
	
	<%
		UtenteBean utente = (UtenteBean)request.getSession().getAttribute("UtenteLoggato");
	%>
	
	<h2>Benvenuto</h2><br>
	
	<Button><a href ="Pagamenti_Utente.jsp">Visualizza o aggiorna i tuoi metodi di pagamento</a></Button><br><br>
	
	<Button><a href ="Indirizzi_Utente.jsp">Visualizza o aggiorna i tuoi indirizzi di spedizione</a></Button><br><br>
	
	<Button><a href="Dati_Utente.jsp">Visualizza o aggiorna i tuoi dati personali</a></Button><br><br>
	
	<Button><a href="Ordini_Utente.jsp">Visualizza i tuoi ordini</a></Button><br><br>
	
	<jsp:include page="footer.jsp" />
	
</body>
</html>