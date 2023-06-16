<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Collection<?> utenti = (Collection<?>) request.getAttribute("all_utents");
		if(utenti == null) {
			response.sendRedirect("./utente");	
		return;
	}    
%>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*"%>
<head>
	<meta charset="UTF-8">
	<title>ShoeMustGoOn | Utenti registrati</title>
</head>
<body>

<jsp:include page="header.jsp" />
		
		<h2>Utenti registrati</h2><br>

		<table border="1">
		<tr>
			<th>ID_Utente</th>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Codice fiscale</th>
			<th>Data Nascita</th>
			<th>Email</th>
			<th>Password</th>

		</tr>
		<%
			if (utenti != null && utenti.size() != 0) {
				Iterator<?> it = utenti.iterator();
				while (it.hasNext()) {
					UtenteBean utente = (UtenteBean) it.next();
		%>
		<tr>
			<td><%=utente.getID_Utente()%></td>
			<td><%=utente.getNome()%></td>
			<td><%=utente.getCognome()%></td>
			<td><%=utente.getCF()%></td>
			<td><%=utente.getDataNascita()%></td>
			<td><%=utente.getEmail()%></td>
			<td><%=utente.getPassword()%></td>
			<td><button><a href="ordine?action=visualizza_cliente&id=<%=utente.getID_Utente()%>">Visualizza ordini</a></button>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Non sono presenti utenti registrati al sito!</td>
		</tr>
		<%
			}
		%>
	</table>
	
</body>
</html>