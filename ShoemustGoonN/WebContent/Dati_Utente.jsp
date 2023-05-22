<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	UtenteBean update = (UtenteBean) request.getAttribute("a");
%>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="model.UtenteBean"%>
<head>
<meta charset="UTF-8">
<title>Dati utente |ShoeMustGoOn</title>
</head>
<body>

<jsp:include page="header.jsp" />
	 
	<%
		UtenteBean utente = (UtenteBean)request.getSession().getAttribute("UtenteLoggato");
		
	%>
	
	<table border="1">
		<tr>
		
			<th>ID_Utente</th>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Codice Fiscale</th>
			<th>Data nascita</th>
			<th>Email</th>
			<th>Password</th>
		</tr>
		<tr>
			<td><%=utente.getID_Utente()%></td>
			<td><%=utente.getNome()%></td>
			<td><%=utente.getCognome()%></td>
			<td><%=utente.getCF()%></td>
			<td><%=utente.getDataNascita()%></td>
			<td><%=utente.getEmail()%></td>
			<td><%=utente.getPassword()%></td>
		</tr>
		
	</table>
		<button><a href="utente?action=update&email=<%=utente.getEmail()%>">Vuoi aggiornare i tuoi dati?</a></button><br>
		<br>

	<%
		if(update != null){	
	%>
	
		<form action="utente" method="post">
		
			<label for="scelta">Cosa vuoi aggiornare?</label>
			<select name="scelta">
				<option value="email">Email</option>
				<option value="password">Password</option>
			</select>
			
			<label for="valore">Inserisci il nuovo valore</label>
			<input type="text" name="valore" maxlength=50 required placeholder="Inserisci il nuovo valore">
			
			<input type="hidden" value ="<%=update.getNome()%>" name="utente">
			
				<div>
				<input class="pulsanti" type="submit" value="Aggiorna">
				<input class="pulsanti" type="reset" value="Reset">
			</div>
		</form>
	
	<% 
		} 
	%>
</body>
</html>