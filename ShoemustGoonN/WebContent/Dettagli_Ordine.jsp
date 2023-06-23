<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	OrdineBean ordine = (OrdineBean) request.getAttribute("Dettaglio_ordine");
	if(ordine == null) {
		response.sendRedirect("./ordine");	
		return;
	}
	

%>
<!DOCTYPE html>
<html lang="it" xml:lang="it">
	<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*"%>
	<link rel="stylesheet" href="style/dettagliI_ordine.css" type="text/css">

<head>
	<meta charset="UTF-8">
	<title>ShoeMustGoOn | Dettaglio Ordine</title>
</head>

<body>

	<jsp:include page="header.jsp" />

<div class="totale">

	
	<div class="parte_sup">
		
		<div class=titolo>
			<p id="titolo1">Ordine N°: </p>
			<p id="titolo2"> <%=ordine.getID_Ordine()%></p>	
		</div>
		
			<div class="order_info">
				
				<h4>Info Ordine</h4><br>

				<div class="campo_specifico">
					<p id="label">Data Ordine:</p> 
						<p id="risposta"> <%=ordine.getDataOrdine()%></p>
				</div>
				
				<div class="campo_specifico">
					<p id="label">Totale Speso:</p> 
						<p id="risposta_prezzo"> <%=ordine.getTotale()%> €</p>
				</div>
		
			</div>
	</div>
			

<%
	PortafoglioBean pagamento = ordine.getPagamento();
	RecapitoBean recapito = ordine.getRecapito();
%>	

	<div class="specifiche2">
		<div class="pagaments">
			<h4>Metodo Pagamento</h4><br>
		
				<div class="campo_specifico">
					<p id="label">N° Carta:</p>
						<p id="risposta"> <%=pagamento.getN_carta()%></p>
				</div>
				
				<div class="campo_specifico">
					<p id="label">Nome Intestatario:</p>
						<p id="risposta"> <%=pagamento.getNome_Intestatario()%></p>
				</div>
				
				<div class="campo_specifico">
					<p id="label">Data di Scadenza:</p>
						<p id="risposta"> <%=pagamento.getScadenza()%></p>
				</div>
				
				<div class="campo_specifico">
					<p id="label">CVV:</p>
						<p id="risposta"> <%=pagamento.getCvv()%></p>
				</div>
		</div>
		
		<div class="recapitoo">
			<h4>Recapito</h4><br>
										
				<div class="campo_specifico">
					<p id="label">CAP:</p>
						<p id="risposta"> <%=recapito.getCap()%></p>
				</div>
				
				<div class="campo_specifico">
					<p id="label">Città:</p>
						<p id="risposta"> <%=recapito.getCitta()%></p>
				</div>
				
				<div class="campo_specifico">
					<p id="label">Via/Piazza:</p>
						<p id="risposta"> <%=recapito.getVia_Piazza()%></p>
				</div>
				
				<div class="campo_specifico">
					<p id="label">N° Civico:</p>
						<p id="risposta"> <%=recapito.getN_Civico()%></p>
				</div>
				
		</div>
	</div>
	
	<hr class="linea"><!-- gestisce la linea di separaizione -->

<%
	ComposizioneModelDS modelComp = new ComposizioneModelDS();
	ProductModelDS modelProd = new ProductModelDS();
	for(ItemCarrello a : modelComp.doRetrieveByOrdine(ordine.getID_Ordine())){
	
		ProdottoBean product = modelProd.doRetrieveByKey(a.getID_ProdottoItemCarrello());
		int quantita = a.getQuantitaItemCarrello();
%>
		
	<div class="prodotto">
		
		<div id="image">
			<img src="<%=product.getImmagine().getPath()%>" alt="Immagine prodotto">
		</div>
		
			<div class="specifiche">
				<div id="marca">
					<%=product.getMarca()%>
				</div>		
			
				<div id="modello">
					<%=product.getModello()%>
				</div>
		
				<div id="category">	
					<%=product.getCategoria()%>
				</div>
					
				<div id="prezzo">
					<%=product.getPrezzo()%> €
				</div>
		
				<div id="color">
					<%=product.getColore()%>
				</div>
			
				<div id="qty">
					<p> Quantità: <%=a.getQuantitaItemCarrello() %></p>
				</div>
			</div>
			

		
		</div>
		
			
			<%
				if(session.getAttribute("AdminLoggato") == null){ 
			%>
					<button>Stampa Fattura</button></td>
			<%
				}
			%>
			
			
		<hr class="linea">	<!-- gestisce la linea di separaizione  -->
			
<%
	}
%>

</div>
			
			
	<jsp:include page="footer.jsp" />
</body>
</html>