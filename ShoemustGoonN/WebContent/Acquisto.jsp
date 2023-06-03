<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	UtenteBean utente = (UtenteBean) session.getAttribute("UtenteLoggato");

	Collection<?> recapiti = (Collection<?>) request.getAttribute("recapiti");
	/*if(recapiti == null) {
		response.sendRedirect("./ordine");	
		return;
	}*/
%>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.OrdineBean, model.UtenteBean, model.RecapitoBean"%>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="style/bien.css" rel="stylesheet" type="text/css">
	<title>ShoeMustGoOn | Procedi all'Acquisto</title>
</head>

<style>
body{
	background-image: url("image/sfondo_recapiti.jpg");	
}
</style>
<body>
		<jsp:include page="header.jsp" />

<div class="complete">		
	<div class=titolo>	
		<h2>Ordine</h2>
	</div>
	
	<div class="sottotitolo">	
		<h3>Dove vuoi venga consegnato il tuo ordine?</h4>
		<p>Seleziona uno dei tuoi recapiti oppure inseriscine uno nuovo:</p><br>	
	</div>
	
	<div class="elementi">
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
		</tr>	

	</div>
	
	<div class="form">			
				<form action="ordine" method="post">
					<input type="hidden" name="action" value="selected_addres">	
					<input type="radio" name="indirizzo" value="<%=bean.getID_Indirizzo()%>"><br>
	

		<%
				}
		%>	

					<input type="submit" value="Seleziona">	
				</form>
	</div>

		<%
			} else {
		%>
			<div class="no_rec">	
				<td colspan="6">Non hai inserito nessun recapito</td>
			</div>
		<%
			}
		%>
				
				</table>
						
	<div class="insert_button">
		<br><Button><a href ="Inserimento_Indirizzo.jsp">Inserisci un nuovo recapito</a></Button>
	</div>

</div>

</body>
</html>