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
	<link href="style/ORder.css" rel="stylesheet" type="text/css">

<head>
<title>ShoeMustGoOn | I miei Ordini</title>
</head>

<!-- 
<style>
body{
	background-image: url("image/sfondo_order.jpg");	
}
</style>
 -->
 
<body>

	<jsp:include page="header.jsp" />
	 
	<%
		UtenteBean utente = (UtenteBean)request.getSession().getAttribute("UtenteLoggato");
	%>
	
		<div id="title">
			<h2>I miei Ordini</h2>
		</div>
		
<div class="content">

		<%
			if (ordini != null && ordini.size() != 0) {
				Iterator<?> it = ordini.iterator();
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
			
		
			<button id="stampa">Stampa Fattura</button>
	</div>	

		<%
				}
			} else {
		%>
		
			 <p colspan="6">Non hai ancora effettuato alcun ordine!</p>
		
		<%
			}
		%>

</div>
</body>
</html>