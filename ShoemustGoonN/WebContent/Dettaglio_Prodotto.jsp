<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

ProdottoBean product = (ProdottoBean) request.getAttribute("product");
if(product == null) {
	response.sendRedirect("./details");	
	return;
}

Collection<?> recensioni = (Collection<?>) request.getAttribute("recensioni");
if(recensioni == null) {
	response.sendRedirect("./details");	
	return;
}

UtenteBean utente = (UtenteBean) request.getSession().getAttribute("UtenteLoggato");
/*Ottenere l'oggetto "UtenteBean" dalla sessione*/
%>

<!-- prova -->
<!DOCTYPE html>
<html lang="it" xml:lang="it">
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="style/Deettaglio.css" rel="stylesheet" type="text/css">
	<link href="style/recension.css" rel="stylesheet" type="text/css">
	<script src="javascript/Dettagli_Prodotto.js" type="text/javascript"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<title><%=product.getMarca() +" "+ product.getModello()%></title>
</head>
<body>

	<jsp:include page="header.jsp" />
	
	<%
		if (product != null) {
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
			

		<div id="form_prod">
		  <form id="cartForm" action="cart" method="post" onsubmit="return formValidation();">	 
		    <input type="hidden" name="action" value="addCart">	
		    <input id="productId" type="hidden" name="id" value="<%=product.getID_Prodotto()%>">
		    
		    <input type="number" name="qty" value="1" min="1" id="numeri">
		    <input type="submit" value="Aggiungi al carrello" id="butt">
		  </form>
		</div>

		
		</div>

	</div>			
			<div id="descrizione">
				
				<div class="title_descr">
					<p>Descrizione</p> <!-- bottone come quelli dei social, in cui quando clicco con javascript mi apre la descrizione -->
					
					<button type="button" onclick="document.getElementById('descr').innerHTML = '<%=product.getDescrizione()%>' "> <img src="https://www.svgrepo.com/show/512676/plus-1512.svg"  title="description" alt="Description icon" width="15" height="15"></button>
				</div>
				
				<a id="descr"> </a>
			
			</div>
	
	<div class="recensioni">
	 	
		 	<div id="title_rec">
		 		<p>Recensioni</p>
		<%
			if(utente != null){
		%>		 	
			<button><a href="recensione?idProd=<%=product.getID_Prodotto()%>">Inserisci una recensione </a></button>
		<%
			} 
		%>
			</div>
		
		<hr class="linea">
		<% 
			if (recensioni != null && recensioni.size() != 0) {
				Iterator<?> it = recensioni.iterator();
			
		%>
	
	<div class="elementi-recensione">
					
					
		<%
			while (it.hasNext()) {
				RecensioneBean bean = (RecensioneBean) it.next();
		%>
		
		<div id="votazione">
			<p id="labell">Votazione: </p>
			
			<%
				if(bean.getVotazione() == 0){
			%>
			
			   	<img id="stelle" src="image/zerostelle.png" title="star-icon" alt="star icon"><br>							 
			
			<%
				}else if(bean.getVotazione() == 1){
			%>
				<img id="stelle" src="image/unastella.png" title="star-icon" alt="star icon"><br>							 
			
			<%
				}else if(bean.getVotazione() == 2){
			%>
				<img id="stelle" src="image/duestelle.png" title="star-icon" alt="star icon"><br>							 

			<%
				}else if(bean.getVotazione() == 3){
			%>

				<img id="stelle" src="image/trestelle.png"title="star-icon" alt="star icon"><br>							 

			<%
				}else if(bean.getVotazione() == 4){
			%>

				<img id="stelle" src="image/quattrostelle.png" title="star-icon" alt="star icon"><br>							 

			<%
				}else if (bean.getVotazione() == 5){
			%>
			
				<img id="stelle" src="image/cinquestelle.png"  title="star-icon" alt="star icon"><br>							 
		
			<%
				}else {
			%>			
			
			<p>Troppe Stelle bro</p>
			
			<%
				}
			%>
				
		</div>
		
		<div id="commento">
			<p id="labell">Commento:</p>
				<p id="rispostaa"><%=bean.getDescrizione()%></p>
		</div>
		
		<div id="utente">
			<p id="labell">Utente:</p>
				<p id="rispostaa"><%=bean.getUtente().getNome()%></p>
		</div>	
				
				<hr class="linea">
				
		<%
				}
		}else{
		%>		
			<div id="rec-vuote">
				<p>Non ci sono recensioni per questo prodotto</p>			
			</div>
		<% 
			}
		%>
				
		</div>
	<%
		}
	%>

		
	</div>

</div>						

	<jsp:include page="footer.jsp"/>
	
</body>
</html>