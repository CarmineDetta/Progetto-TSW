<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   Collection<?> payments = (Collection<?>) request.getAttribute("payments");
	if(payments == null) {
		response.sendRedirect("./utente");	
		return;
	}
%>
 
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.PortafoglioBean, model.UtenteBean"%>

<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" href="style/utente.css">
	<title>ShoeMustGoOn | Il mio profilo</title>
</head>

<body>
	
	<jsp:include page="header.jsp" />
	 
	<%
		UtenteBean utente = (UtenteBean)request.getSession().getAttribute("UtenteLoggato");
	%>
	
	<div class="content">
		<h2 class="title">Benvenuto...</h2>
	
	<table class="table" border="1">
		<tr>			
			<th>ID_Pagamento</th>
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
			<td><%=bean.getID_Pagamento()%></td>
			<td><%=bean.getN_carta()%></td>
			<td><%=bean.getNome_Intestatario()%></td>
			<td><%=bean.getScadenza()%></td>
			<td><%=bean.getCvv()%></td>
			<td><button class="rmv_button"><a href="utente?action=delete&id=<%=bean.getID_Pagamento()%>">Elimina</a></button></td><br>
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
		
		<div class="add_button">
			<button class="uname_insert"><a href ="InserimentoCarta.jsp">Inserisci una nuova carta</a></button>
		</div>
	</table>
	</div>
	
		<jsp:include page="footer.jsp" />
	
	
</body>
</html>