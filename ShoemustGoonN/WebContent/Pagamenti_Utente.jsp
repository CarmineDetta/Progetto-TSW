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
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.PortafoglioBean, model.UtenteBean"%>

<head>
<title>Metodi di pagamento | ShoeMustGoOn</title>
</head>
<body>

	<jsp:include page="header.jsp" />
	 
	<%
		UtenteBean utente = (UtenteBean)request.getSession().getAttribute("UtenteLoggato");
	%>
	
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
			<td><button><a href="payments?action=delete&id=<%=bean.getID_Pagamento()%>">Elimina</a></button></td><br>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Non hai inserto nessuna carta</td>
		</tr>
		<%
			}
		%>
		
		<Button><a href ="InserimentoCarta.jsp">Inserisci una nuova carta</a></Button>
		
	</table>
	
</body>
</html>