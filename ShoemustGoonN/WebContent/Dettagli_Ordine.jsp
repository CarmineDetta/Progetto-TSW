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
	<title>ShoeMustGoOn | Dettaglio Ordine</title>
</head>

<body>

	<jsp:include page="header.jsp" />

			ID_Ordine
			Data ordine
			Totale
		
			<%=ordine.getID_Ordine()%>ù
			<%=ordine.getDataOrdine()%>
			<%=ordine.getTotale()%>
			<%
				if(session.getAttribute("AdminLoggato") == null){ 
			%>
					<button>Stampa Fattura</button></td>
			<%
				}
			%>

<%
	PortafoglioBean pagamento = ordine.getPagamento();
	RecapitoBean recapito = ordine.getRecapito();
%>	

	<h3>Metodo Pagamento</h3><br>

			<%=pagamento.getN_carta()%>
			<%=pagamento.getNome_Intestatario()%>
			<%=pagamento.getScadenza()%>
			<%=pagamento.getCvv()%>

	<h3>Recapito</h3><br>
		

			<%=recapito.getCap()%>
			<%=recapito.getCitta()%>
			<%=recapito.getVia_Piazza()%>
			<%=recapito.getN_Civico()%>

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