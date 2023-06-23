<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Collection<?> orders = (Collection<?>) request.getAttribute("all_orders");
	if(orders == null) {
		response.sendRedirect("./ordine");	
	return;
}    
%>
<!DOCTYPE html>
<html lang="it" xml:lang="it">
	<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, model.*"%>
	<link href="style/ordini_admin.css" rel="stylesheet" type="text/css">

<head>
	<meta charset="UTF-8">
	<title>Visualizza ordini | ShoemustGoOn</title>
</head>

<body>
	<jsp:include page="header.jsp" />
	
	<div id="title">
		<h2>Visualizzazione degli ordini di tutti i clienti</h2>
	</div>

<div class="combo_bottoni">
	<div id="visual_button">
		<a href="utente?action=visualizza_tutti">Visualizzazione degli Ordini in base al Cliente</a>
	</div>
	<br>
	<div id="visual_button">
		<a href="ordine?action=visualizza_data">Visualizzazione degli Ordini in base alla Data</a>
	</div>
</div>
	
<div class="content">
		<%
			if (orders != null && orders.size() != 0) {
				Iterator<?> it = orders.iterator();
				while (it.hasNext()) {
					OrdineBean bean = (OrdineBean) it.next();
		%>
		
	<div class="completo">
	
		<table border="1">
		<tr>
			<th>ID_Ordine</th>
			<th>Data ordine</th>
			<th>Totale</th>
			<th>Utente</th>
			<th><button><a href="ordine?action=details&id=<%=bean.getID_Ordine()%>">Dettagli</a></button></th>	
		</tr>
	
		<tr>
			<td><%=bean.getID_Ordine()%></td>
			<td><%=bean.getDataOrdine()%></td>
			<td><%=bean.getTotale()%></td>
			<td><%=bean.getUtente().getID_Utente()%></td>
		</tr>

	
	</div>
		<%
				}
			}else{
		%>
			<td colspan="6">Non è stato registrato alcun ordine!</td> 
		<%
				}
		%>
		</table>
</div>

	
</body>
</html>