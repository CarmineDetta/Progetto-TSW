<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   Collection<?> ordini = (Collection<?>) request.getAttribute("ordini_data");
	if(ordini == null) {
		response.sendRedirect("./ordine");	
		return;
	}
%>
    
<!DOCTYPE html>
<html lang="it" xml:lang="it">
	<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.OrdineBean, model.UtenteBean"%>
	<link rel="stylesheet" href="style/ordine_admin_data.css" type="text/css">
	
<head>
<title>ShoeMustGoOn | Ordini per data</title>
</head>


<body>

	<jsp:include page="header.jsp" />
	 
<div class="content">

		<h2>Trova Ordine in base alla Data</h2><br>
	
	<form action="ordine" method="post">
		<input type="hidden" name="action" value="visualizza_data">
		
		<label for="dataInizio">Data inizio: </label>
		<input type="date" name="dataInizio" required><br>
		
		<label for="dataFine">Data fine: </label>
		<input type="date" name="dataFine" required><br>
		
		<input type="submit" value="Cerca">
		<input type="reset" value="Reset">
		
	</form>
	
	
	<table border="1">
	<caption>Tabella ordini data Admin</caption>
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
			<td colspan="6">Non è presente alcun ordine tra queste due date</td>
		</tr>
		<%
			}
		%>
	</table>
		
</div>
</body>
</html>