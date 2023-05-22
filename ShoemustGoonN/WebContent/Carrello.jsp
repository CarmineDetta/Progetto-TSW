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
<html>

<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, model.*"%>

<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="style/carrello.css">
	<title>ShoeMustGoOn | Carrello</title>
</head>

<body>
	
	<jsp:include page="header.jsp" />

<div id="carrello">
 <div class="listaProdCarrello">
	<div class="titoloCarrello">
			
			<p style="width:20%; margin-right:auto">Carrello</p>
			
				<form action="cart" method="post">	
					<input type="hidden" name="action" value="rmvAll">	
					<input type="hidden" name="qty" value="1">
					<input type="hidden" name="provenienza" value="carrello">					
					<button class="svuotaCarrello"><img src="https://www.svgrepo.com/show/434577/basket-error.svg" width="28" height="28" title="basket-icon" alt="basket icon"></button>
				</form>
 	</div>
 	
		<%
			if (cart != null && cart.getProducts().size() != 0) {
				ProdottoBean p = new ProdottoBean();
				String id;
				for(ItemCarrello c : cart.getProducts()){
					
					id = c.getID_ProdottoItemCarrello();
					p = cart.findProduct(id);
		%>
		
		<div class="prodottoCarrello">
	

			<p class="label"> Scarpa: <%=p.getModello()%></p>
					
					<br>
					
			<p class="label"> Colore: <%=p.getColore()%> </p>

			<div>
			 	<img src="<%=p.getImmagine().getPath()%>" id="immag">
			</div>
					

			<p class="label"><%=p.getCategoria()%></p>
			
			<div>
				<p class="prodPrezzo">Prezzo: <%=p.getPrezzo()%> &euro;</p>			
			</div>
				
			<div>
				<label class="labelqty" for="quantità">Quantità: <%=c.getQuantitaItemCarrello()%></label>
			
				<form action="cart" method="post">	
					<input type="hidden" name="action" value="deleteToCart">	
					<input type="hidden" name="id" value="<%=c.getID_ProdottoItemCarrello()%>">
					<input type="hidden" name="qty" value="<%=c.getQuantitaItemCarrello()%>">
					<input type="submit" value="Rimuovi dal carrello" class="pulsanti"> &nbsp;
				</form>
		
				<form action="cart" method="post">	
					<input type="hidden" name="action" value="addCart">	
					<input type="hidden" name="id" value="<%=c.getID_ProdottoItemCarrello()%>">
					<input type="hidden" name="qty" value="<%=c.getQuantitaItemCarrello()%>">
					<input type="submit" value="Aggiungi al carrello" class="pulsanti"> &nbsp;
				</form>
			</div>
			
			<br>
		
		</div>
		<%
				}
			} else {
		%>
		
			 <p>Il carrello è vuoto</p>
		
		<%
			}
		%>
	</div>
</div>
	
	<jsp:include page="footer.jsp"/>
	
</body>
</html>