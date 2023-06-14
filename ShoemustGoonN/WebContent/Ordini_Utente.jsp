<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   Collection<?> ordini = (Collection<?>) request.getAttribute("ordini");
	if(ordini == null) {
		response.sendRedirect("./ordine");	
		return;
	}
%>
    
<!DOCTYPE html>
<html>
	<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.OrdineBean, model.UtenteBean"%>
	<link href="style/order.css" rel="stylesheet" type="text/css">

<head>
<title>ShoeMustGoOn | I miei Ordini</title>
</head>

<style>
body{
	background-image: url("image/sfondo_order.jpg");	
}
</style>

<body>

	<jsp:include page="header.jsp" />
	 
	<%
		UtenteBean utente = (UtenteBean)request.getSession().getAttribute("UtenteLoggato");
	%>
<div class="content">

	<div class="title">
		<h2>I miei Ordini</h2>
	</div>
	<table border="1">
		<tr>
			<th>ID_Ordine</th>
			<th>Data ordine</th>
			<th>Totale</th>
		</tr>
		<%
			if (ordini != null && ordini.size() != 0) {
				Iterator<?> it = ordini.iterator();
				while (it.hasNext()) {
					OrdineBean bean = (OrdineBean) it.next();
		%>
		<tr>
			<td><%=bean.getID_Ordine()%></td>
			<td><%=bean.getDataOrdine()%></td>
			<td><%=bean.getTotale()%></td>
			<td><button>Stampa Fattura</button></td><br>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Non hai ancora effettuato alcun ordine!</td>
		</tr>
		<%
			}
		%>
	</table>
		
</div>
</body>
</html>