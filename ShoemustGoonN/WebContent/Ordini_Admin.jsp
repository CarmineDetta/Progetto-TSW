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
<html>
	<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, model.*"%>

<head>
	<meta charset="UTF-8">
	<title>Visualizza ordini | ShoemustGoOn</title>
</head>

<body>
	<jsp:include page="header.jsp" />
	
	<h6>Visualizzazione degli ordini di tutti i clienti</h6><br>
	<table border="1">
		<tr>
			<th>ID_Ordine</th>
			<th>Data ordine</th>
			<th>Totale</th>
			<th>ID_Utente</th>
		</tr>
		<%
			if (orders != null && orders.size() != 0) {
				Iterator<?> it = orders.iterator();
				while (it.hasNext()) {
					OrdineBean bean = (OrdineBean) it.next();
		%>
		<tr>
			<td><%=bean.getID_Ordine()%></td>
			<td><%=bean.getDataOrdine()%></td>
			<td><%=bean.getTotale()%></td>
			<td><%=bean.getUtente().getID_Utente()%></td>
			<td><button><a href="ordine?action=details&id=<%=bean.getID_Ordine()%>">Dettagli</a></button></td>
		</tr>
		
		<%
				}
			}else{
		%>
			<p>Non è stato registrato alcun ordine!</p> 
		<%
				}
		%>
	</table><br>
	
	<button><a href="utente?action=visualizza_tutti">Visualizzazione per cliente</a></button>
	<button><a href="ordine?action=visualizza_data">Visualizzazione per data</a></button>
	
	<jsp:include page="footer.jsp" />
	
</body>
</html>