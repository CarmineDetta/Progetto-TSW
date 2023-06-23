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
<html lang="it" xml:lang="it">

	<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*"%>
	<link rel="stylesheet" href="style/visualizza-utenti.css" type="text/css">

<head>
	<meta charset="UTF-8">
	<title>ShoeMustGoOn | Utenti Registrati</title>
</head>
<body>

<jsp:include page="header.jsp" />

<div class="content">


			<!-- barra ricerca -->
			<div id="barra_ricerca">
				<img src="https://www.svgrepo.com/show/510179/search-file.svg" width="28" height="28" title="mark-icon" alt="mark icon">
				<input type="text" placeholder="Cerca Utente" class="campoRicerca" id="cerca" onkeyup="funzioneRicercaUtente()"/>
				<div id="Risultati"></div>
			</div>

		<h2>Utenti Registrati</h2>
		
		<table border="1">
			<caption>Tabella che mostra gli utenti che hanno effettuato ordini</caption>
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
</div>

		<jsp:include page="footer.jsp" />
		<script>
		$('#cerca').on('click', function() {
			
			Swal.fire({
				  showConfirmButton: false,
				  html: '<h1>Ricerca</h1><div id="barraricercaResponsive"><input type="text" placeholder="Cerca prodotti" class="campoRicercaResponsive" id="QueryRicercaResponsive" onkeyup="funzioneRicercaUtente()"/><button type="submit" class="bottoneRicercaResponsive"><ion-icon name="search-outline" class="" id=""></ion-icon></button><div id="RisultatiResponsive"></div></div>',
				  customClass: { popup: 'borderBoxPopU'},
				})
		} )
		
		
		function funzioneRicercaUtente() {
			
			$("#Risultati").empty();
        	$("#RisultatiResponsive").empty();
        	$("#Risultati").removeClass( "DivRisultati" );
			
			console.log("Inizia la funzione di ricerca")
			var stringaParziale;

			
			
			if(document.getElementById("cerca").value === ""){
				stringaParziale = document.getElementById("QueryRicercaResponsive").value;
				console.log("Ricerca query responsive");
			} else {
				stringaParziale = document.getElementById("cerca").value;
				console.log("Ricerca query normale");
			}
			
			$.ajax({  
				async: true,
	            //uri della servlet
	            url: "AjaxUtente",  
	            //tipo di richiesta
	            method: "POST",
	            //dati inviati al server
	            data: "stringaRicerca=" + stringaParziale,
	            //tipo dato ricevuto dalla servlet
	            dataType: "json",          
	            success: function(data, textStatus, jqXHR) {
	            	
	            	$("#Risultati").empty();
	            	$("#RisultatiResponsive").empty();
	            	
	            	if( data.length >=1) {
	            		$("#RisultatiResponsive").empty();
	            		$("#Risultati").empty();
		            	$("#Risultati").addClass( "DivRisultati" );
		            	for (const i in data) {
		            		$( "#Risultati" ).append('<div id=""><a href="ordine?action=visualizza_cliente&id='+data[i].ID_Utente+'">'+data[i].Cognome+'</a></div>');
		            		$( "#RisultatiResponsive" ).append('<div id=""><a href="ordine?action=visualizza_cliente&id='+data[i].ID_Utente+'">'+data[i].Cognome+'</a></div>');
						 }
		            	
	            	} else {
	            		$("#RisultatiResponsive").empty();
	            		$("#Rdisultati").empty();
	            		$("#Risultati").removeClass( "DivRisultati" );
	            	}
	            	
	            	if(stringaParziale === ""){
	            		
	            		$("#Risultati").removeClass( "DivRisultati" );
	            	}
	            	
	            },
	            error: function(jqXHR, textStatus, errorThrown){
	            	console.log(jqXHR);
	            } 
	        });
		}
		
	</script>
</body>
</html>