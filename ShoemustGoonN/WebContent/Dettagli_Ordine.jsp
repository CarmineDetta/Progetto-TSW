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
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*"%>
<head>
<meta charset="UTF-8">
<link href="style/dettaglio.css" rel="stylesheet" type="text/css">

<title>Dettagli Ordine | ShoeMustGoOn</title>
</head>
<body>

	<jsp:include page="header.jsp" />

	<table border="1">
		<tr>
			<th>ID_Ordine</th>
			<th>Data ordine</th>
			<th>Totale</th>
		</tr>
		
		<tr>
			<td><%=ordine.getID_Ordine()%></td>
			<td><%=ordine.getDataOrdine()%></td>
			<td><%=ordine.getTotale()%></td>
			<%
				if(session.getAttribute("AdminLoggato") == null){ 
			%>
					<td><button>Stampa Fattura</button></td><br>
			<%
				}
			%>
		</tr>
	</table><br>
<%
	PortafoglioBean pagamento = ordine.getPagamento();
	RecapitoBean recapito = ordine.getRecapito();
%>	

	<h3>Metodo Pagamento</h3><br>
	<table border="1">
		<tr>
			<th>Numero Carta</th>
			<th>Nome Intestatario</th>
			<th>Scandenza</th>
			<th>CVV</th>
		</tr>
		<tr>
			<td><%=pagamento.getN_carta()%></td>
			<td><%=pagamento.getNome_Intestatario()%></td>
			<td><%=pagamento.getScadenza()%></td>
			<td><%=pagamento.getCvv()%></td>
		</tr>
	</table><br>
	
	<h3>Recapito</h3><br>
	<table border="1">
		<tr>	
			<th>Cap</th>
			<th>Città</th>
			<th>Via/Piazza</th>
			<th>N. civico</th>
		</tr>
		<tr>
			<td><%=recapito.getCap()%></td>
			<td><%=recapito.getCitta()%></td>
			<td><%=recapito.getVia_Piazza()%></td>
			<td><%=recapito.getN_Civico()%></td>
		</tr>
	</table><br>
<%
	ComposizioneModelDS modelComp = new ComposizioneModelDS();
	ProductModelDS modelProd = new ProductModelDS();
	for(ItemCarrello a : modelComp.doRetrieveByOrdine(ordine.getID_Ordine())){
	
		ProdottoBean product = modelProd.doRetrieveByKey(a.getID_ProdottoItemCarrello());
		int quantita = a.getQuantitaItemCarrello();
%>
	
<div class="content">
	<div class="elementi">
		
		<div id="image">
			<img src="<%=product.getImmagine().getPath()%>" alt="Immagine prodotto">
		</div>
		<div id="zoomedImage"></div>

		<div class="text">
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
			
			<div>
				<%=quantita%>
			</div>
	</div>
</div>

<%
	}
%>
	<jsp:include page="footer.jsp" />

</body>
</html>