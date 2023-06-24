<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   Collection<?> payments = (Collection<?>) request.getAttribute("payments");
	if(payments == null) {
		response.sendRedirect("./payments");	
		return;
	}
%>
    
<!DOCTYPE html>
<html lang="it" xml:lang="it">
	<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.PortafoglioBean, model.UtenteBean"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="style/payements.css" rel="stylesheet" type="text/css">
	<title>ShoeMustGoon | Metodi di Pagamento</title>
</head>
<style>
body{
	background-image: url("image/sfondo_payements.jpg");	
}
</style>
<body>

	<jsp:include page="header.jsp" />
	 
	<%
		UtenteBean utente = (UtenteBean)request.getSession().getAttribute("UtenteLoggato");
	%>
	
<div class="totale">
	<div id="insert_button">	
		<button><a href ="InserimentoCarta.jsp">Inserisci una nuova carta</a></button>
	</div>	
	
<div class="contenuto">

		<div class=title>	
			<h2>Portafoglio</h2>
		</div>
		
	<div class="elementi">
	
	<table border="1">
		<caption>tabella per visualizzare le informazioni sulla carta</caption>
		<tr>
			<th>Numero Carta</th>
			<th>Nome Intestatario</th>
			<th>Scandenza</th>
			<th>CVV</th>
			
		</tr>
		<%
			if (payments != null && payments.size() != 0) {
				Iterator<?> it = payments.iterator();
				while (it.hasNext()) {
					PortafoglioBean bean = (PortafoglioBean) it.next();
		%>
		<tr>
			<td><%=bean.getN_carta()%></td>
			<td><%=bean.getNome_Intestatario()%></td>
			<td><%=bean.getScadenza()%></td>
			<td><%=bean.getCvv()%></td>
			<td><button id="rmv_butt"><a href="payments?action=delete&id=<%=bean.getID_Pagamento()%>">Elimina</a></button></td><br>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Non hai inserto nessuna carta</td>
		</tr>
	</table>
		<%
			}
		%>
	</div>
	
</div>

</div>	
</body>
</html>