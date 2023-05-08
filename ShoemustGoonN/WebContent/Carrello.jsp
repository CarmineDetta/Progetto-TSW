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
	<title>ShoeMustGoOn | Carrello</title>
</head>

<body>
	
	<jsp:include page="header.jsp" />
	<h2>Carrello</h2>
	
	<table border="1">
		<tr>
			<table border="1">
		<tr>
			<th>ID_Prodotto</th>
			<th>Marca</th>
			<th>Modello</th>
			<th>Colore</th>
			<th>Prezzo</th>
			<th>Disponibilita</th>
			<th>Descrizione</th>
			<th>Categoria</th>
			<th>Quantità carrello</th>
		</tr>
		<%
			if (cart != null && cart.getProducts().size() != 0) {
				ProdottoBean p = new ProdottoBean();
				String id;
				for(ItemCarrello c : cart.getProducts()){
					
					id = c.getID_ProdottoItemCarrello();
					p = cart.findProduct(id);
		%>
		
		
		<tr>
			<td><%=p.getID_Prodotto()%></td>
			<td><%=p.getMarca()%></td>
			<td><%=p.getModello()%></td>
			<td><%=p.getColore()%></td>
			<td><%=p.getPrezzo()%></td>
			<td><%=p.isDisponibilita()%></td>
			<td><%=p.getDescrizione()%></td>
			<td><%=p.getCategoria()%></td>
			<td><%=c.getQuantitaItemCarrello()%></td>
			<td><button><a href="details?action=read&id=<%=p.getID_Prodotto()%>">Dettagli</a></button></td>
			<td>
				<form action="cart" method="post">	
				<input type="hidden" name="action" value="deleteCart">	
				<input type="hidden" name="id" value="<%=c.getID_ProdottoItemCarrello()%>">
				<input type="hidden" name="qty" value="<%=c.getQuantitaItemCarrello()%>">
				<input type="submit" value="Rimuovi dal carrello">
				</form>
			</td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Il carrello è vuoto</td>
		</tr>
		<%
			}
		%>
	</table>
	
	<jsp:include page="footer.jsp"/>
	
</body>
</html>