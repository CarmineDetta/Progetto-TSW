<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   Collection<?> ordini = (Collection<?>) request.getAttribute("ordini_utente");
	if(ordini == null) {
		response.sendRedirect("./ordine");	
		return;
	}
%>
    
<!DOCTYPE html>
<html>
	<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.OrdineBean, model.UtenteBean"%>
	<link href="style/Ordini_admin_cliente.css" rel="stylesheet" type="text/css">
	
<head>
<title>ShoeMustGoOn | Ordini Cliente</title>
</head>

<body>

	<jsp:include page="header.jsp" />
	 
<div class="contenutoo">


		<h2>Ordini Cliente</h2>

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
			<td><button><a href="ordine?action=details&id=<%=bean.getID_Ordine()%>">Dettagli</a></button>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Non ha ancora effettuato alcun ordine!</td>
		</tr>
		<%
			}
		%>
	</table>
		
</div>
</body>
</html>