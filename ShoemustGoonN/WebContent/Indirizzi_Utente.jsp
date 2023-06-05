<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   Collection<?> recapiti = (Collection<?>) request.getAttribute("recapiti");
	if(recapiti == null) {
		response.sendRedirect("./ordine");	
		return;
	}
%>
    
<!DOCTYPE html>
<html>
	<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.RecapitoBean, model.UtenteBean"%>
	<link href="style/indirizzi.css" rel="stylesheet" type="text/css">

<head>
<title>ShoeMustGoOn | Indirizzi di Spedizione</title>
</head>

<style>
body{
	background-image: url("image/sfondo_recapiti.jpg");	
}
</style>

<body>

	<jsp:include page="header.jsp" />
	 
	<%
		UtenteBean utente = (UtenteBean)request.getSession().getAttribute("UtenteLoggato");
	%>
	
	<div class="content">
		
		<div class="title">
			<h2>I tuoi Indirizzi</h2>
		</div>
		
		<div class="elementi">
		<table border="1">
			<tr>
		
				<th>Cap</th>
				<th>Città</th>
				<th>Via/Piazza</th>
				<th>N. civico</th>
			
			</tr>
		<%
			if (recapiti != null && recapiti.size() != 0) {
				Iterator<?> it = recapiti.iterator();
				while (it.hasNext()) {
					RecapitoBean bean = (RecapitoBean) it.next();
		%>
		<tr>
			<td><%=bean.getCap()%></td>
			<td><%=bean.getCitta()%></td>
			<td><%=bean.getVia_Piazza()%></td>
			<td><%=bean.getN_Civico()%></td>
			<td><button><a href="recapiti?action=delete&id=<%=bean.getID_Indirizzo()%>">Elimina</a></button></td><br>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Non hai inserito nessun recapito</td>
		</tr>
		
		</table>
	</div>
		<%
			}
		%>
	
	<div id="insert_button"> 
		<Button><a href ="Inserimento_Indirizzo.jsp">Inserisci un nuovo recapito</a></Button>
	</div>
	
	
</div>
</body>
</html>