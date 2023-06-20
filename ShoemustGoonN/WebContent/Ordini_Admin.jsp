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
	<link href="style/OOrder.css" rel="stylesheet" type="text/css">

<head>
	<meta charset="UTF-8">
	<title>Visualizza ordini | ShoemustGoOn</title>
</head>

<body>
	<jsp:include page="header.jsp" />
	
	<div id="title">
		<h2>Visualizzazione degli ordini di tutti i clienti</h2>
	</div>

<div class="content">
		<%
			if (orders != null && orders.size() != 0) {
				Iterator<?> it = orders.iterator();
				while (it.hasNext()) {
					OrdineBean bean = (OrdineBean) it.next();
		%>
		<div class="completo">
	
		<div class="elements1">
			<div id="price_order">
				<p>Totale: <%=bean.getTotale()%> €</p>
			</div>

			<div id="date_order">
				<p>In data:  <%=bean.getDataOrdine()%>
			</div>
		</div>
		
		<div class="elements2">
			<div id="id_order">
				<p> ID-Articolo: <br>
					N° <%=bean.getID_Ordine()%></p>
			</div>
				
			<button id="butt_dettagli"><a href="ordine?action=details&id=<%=bean.getID_Ordine()%>">Dettagli</a></button>
		</div>
		
		<div id="utente">
			<p> Utente: 
				<%=bean.getUtente().getID_Utente()%></p>
		</div>

	</div>
		<%
				}
			}else{
		%>
			<p>Non è stato registrato alcun ordine!</p> 
		<%
				}
		%>

	
	<button><a href="utente?action=visualizza_tutti">Visualizzazione per cliente</a></button>
	<button><a href="ordine?action=visualizza_data">Visualizzazione per data</a></button>
</div>
	<jsp:include page="footer.jsp" />

</body>
</html>