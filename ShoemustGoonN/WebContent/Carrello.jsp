<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Cart cart = (Cart) session.getAttribute("cart");

	if(cart == null) {
		response.sendRedirect("./cart");	
		return;
	}
		
%>
<!DOCTYPE html>
<html lang="it" xml:lang="it">


<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, model.*, java.text.DecimalFormat"%>

<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="style/carrello.css" rel="stylesheet" type="text/css">
	<script src="javascript/Carrello2.js" type="text/javascript"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<title>ShoeMustGoOn | Carrello</title>
	
</head>

<style>
body{
	background-image: url("image/sfondoverdepastello.jpg");	
}
</style>

<body>

	<jsp:include page="header.jsp" />

	<div class="title">
		<p id="titolo">Il tuo Carrello:</p>			
	</div>
	
<div class="conteiner">
		<div class="listaprodotti">
		
		
		
	<%
			if (cart != null && cart.getProducts().size() != 0) {
				ProdottoBean p = new ProdottoBean();
				String id;
				for(ItemCarrello c : cart.getProducts()){
					
					id = c.getID_ProdottoItemCarrello();
					p = cart.findProduct(id);
		%>
	
			
	
				<div class="elementi">	
					
				 		<img src="<%=p.getImmagine().getPath()%>" id="immag" alt="img.jpg">
					
					 <div class="text">
					
						<a class="label_modello"> <br> <%=p.getModello()%></a>	<br>			
					
					
						<a class="label"> <%=p.getColore()%> </a> <br>
			
			
						<a class="label"><%=p.getCategoria()%></a> <br>
			
						<br>
						
					
						<p class="prodPrezzo">Prezzo: <br>  <%=p.getPrezzo()%> &euro; </p>			
				
					<div id="noajax">
  						<label class="labelqty" for="quantità">Quantità: <span id="quantita"><%= c.getQuantitaItemCarrello() %></span></label>
					</div>

					 </div>
					</div>		
				
				<div class="pulsanti">			
					<form action="cart" method="post">	
						<input type="hidden" name="action" value="addCart">	
						<input id="addid" type="hidden" name="id" value="<%=c.getID_ProdottoItemCarrello()%>">
						<input id="qtyadd" type="hidden" name="qty" value="<%=c.getQuantitaItemCarrello()%>">
						<button id="add" type="submit" value="add" onclick="return formAdd('<%=c.getID_ProdottoItemCarrello()%>', '<%=c.getQuantitaItemCarrello()%>');">Add</button>
					</form>
	
					<form action="cart" method="post">	
						<input type="hidden" name="action" value="deleteToCart">	
						<input id="removeid" type="hidden" name="id" value="<%=c.getID_ProdottoItemCarrello()%>">
						<input id="qtyremove" type="hidden" name="qty" value="<%=c.getQuantitaItemCarrello()%>">
						<button id="remove" type="submit" value="remove"  onclick="return formRemove('<%=c.getID_ProdottoItemCarrello()%>', '<%=c.getQuantitaItemCarrello()%>');">Remove</button>
					</form>
				</div>

				<%}%>	
						
				<form action="cart" method="post">	
					<input type="hidden" name="action" value="rmvAll">	
					<input type="hidden" name="qty" value="1">
					<input type="hidden" name="provenienza" value="carrello">					
					<button id="svuota" class="button_svuotaCarrello"><img src="https://www.svgrepo.com/show/434577/basket-error.svg" width="24" height="24" title="basket-icon" alt="basket icon"></button>
				</form>	
		</div>	<!-- chiude div listaprodotti -->
		
		<div class="div_acquisto">
		
				<p class="somma_title">Prezzo Totale</p>
			
			<%
				DecimalFormat df = new DecimalFormat("#.##");
				String n = df.format(cart.getTotale());
			%>	
			
			<div id="prezzo">	
					
			</div>

				<div class="euro">
					<%=n%> &euro;
				</div>

			<%
				UtenteBean utente = (UtenteBean) session.getAttribute("UtenteLoggato");
			
				if(utente != null){
			%>
			<br>
			<button><a href="AcquistoControl?action=CheckOut">Procedi all'acquisto</a></button>
				
			<%
				}else{
			%>
			<br><br>
			<button><a href="Login.jsp">Non hai effettuato nessun login, fai il login?</a></button> 
		
		</div>
		

	
		<%
				}
				
				/*chiude parentesi riga 38*/
			} else {
				%>
				
				<div class="cart_empty">
					<h2>Il carrello è vuoto</h2>
						<img src="image/faccina_triste.png" alt="img.jpg">
					<p>Inizia i tuoi acquisti, vai al catalogo</p>
				
					<button><a href="Catalogo_Utente.jsp">Vai al catalogo   -></a></button>
				</div>
			<%
				}
			%>
</div>
	
						
	
</body>
</html>



