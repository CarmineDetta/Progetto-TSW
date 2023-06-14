<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*, model.*"%>
    
<% 

	Cart cart = (Cart) session.getAttribute("cart");
	
	if(cart == null) {
		response.sendRedirect("./cart");	
		return;
	}
	
	UtenteBean u = (UtenteBean)request.getSession().getAttribute("Utente loggato");
	
	Collection<?> payments = (Collection<?>) request.getAttribute("payments");
	if(payments == null) {
		response.sendRedirect("./AcquistoControl");	
		return;
	}
	
	Collection<?> recapiti = (Collection<?>) request.getAttribute("recapiti");
	if(recapiti == null) {
		response.sendRedirect("./AcquistoControl");	
		return;
	}
	

%>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Checkout | ShoeMustGoOn</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	
		<form action="AcquistoControl" method="post"> 
		
		<input type="hidden" name="action" value="Completo">
		
		<span>Seleziona il tuo metodo di pagamento</span>
		
		<table border="1">
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
			<td><input type="radio" name="Pagamento" required value="<%= bean.getID_Pagamento()%>"></td><br>
		</tr>
		<%
				}
		%>
		
		</table><br>		
		<button><a href="InserimentoCarta.jsp">Inserisci una nuova carta</a></button><br>
		
		<%
			} else {
		%>
		<button><a href="InserimentoCarta.jsp">Non hai inserto nessuna carta, inserisci una nuova carta</a></button><br>
		<%
			}
		%>
		
		<br><span>Seleziona il recapito dell'ordine</span><br>
		
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
			<td><input type="radio" name="Recapito" required value="<%= bean.getID_Indirizzo()%>"></td><br>
	
		</tr>
		
		
		<%
				}
		%>
		
		</table><br>		
		<Button><a href ="Inserimento_Indirizzo.jsp">Inserisci un nuovo recapito</a></Button><br>
		
		<%
			} else {
		%>
			<br><Button><a href ="Inserimento_Indirizzo.jsp">Non hai inserito alcun recapito, inserisci un nuovo recapito</a></Button><br>
			
		<%
			}
		%>
		
	<% if (payments.isEmpty() || recapiti.isEmpty()){ %>
					<input type="submit" value="Completa ordine" disabled>
	
				<% } else { %>
					<input type="submit" value="Completa ordine">
					
				<% } %>
		
</body>
</html>