<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	UtenteBean utente = (UtenteBean) session.getAttribute("UtenteLoggato");

	Collection<?> recapiti = (Collection<?>) request.getAttribute("recapiti");
	if(recapiti == null) {
		response.sendRedirect("./ordine");	
		return;
	}
%>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.OrdineBean, model.UtenteBean, model.RecapitoBean"%>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Procedi all'acquisto | ShoeMustGoOn</title>
</head>
<body>
		<jsp:include page="header.jsp" />
	
		<h1>Ordine</h2>
		
		<h3>Dove vuoi fare consegnato il tuo ordine?</h4>
		<h6>Seleziona uno dei tuoi recapiti oppure inseriscine uno nuovo:</h6><br>	
		
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
			<td>
				<form action="ordine" method="post">
					<input type="hidden" name="action" value="selected_addres">	
					<input type="radio" name="indirizzo" value="<%=bean.getID_Indirizzo()%>"><br>
			</td>
		</table>
		<%
				}
		%>	
			<br><input type="submit" value="Seleziona">	
			</form>
		<%
			} else {
		%>
		<table>
			<tr>
				<td colspan="6">Non hai inserito nessun recapito</td>
			</tr>
		</table>
			
		<%
			}
		%>
							
	</table>
	
		<br><Button><a href ="Inserimento_Indirizzo.jsp">Inserisci un nuovo recapito</a></Button>
	
		<jsp:include page="footer.jsp"/>
	
</body>
</html>