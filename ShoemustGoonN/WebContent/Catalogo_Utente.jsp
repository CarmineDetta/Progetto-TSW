<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./product");	
		return;
	}
	ProdottoBean product = (ProdottoBean) request.getAttribute("product");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.ProdottoBean"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Catalogo | ShoeMustGoOn</title>
</head>

<body>

	<jsp:include page="header.jsp" />
	<h2>Prodotti</h2>
	
	<table border="1">
		<tr>
			<th>ID_Prodotto<button><a href="product?sort=ID_Prodotto"> Ordina</a></button></th>
			<th>Marca<button><a href="product?sort=marca"> Ordina</a></button></th>
			<th>Modello<button><a href="product?sort=modello"> Ordina</a></button></th>
			<th>Colore<button><a href="product?sort=colore"> Ordina</a></button></th>
			<th>Prezzo<button><a href="product?sort=descrizione"> Ordina</a></button></th>
			<th>Disponibilita<button><a href="product?sort=disponibilita"> Ordina</a></button></th>
			<th>Descrizione<button><a href="product?sort=descrizione"> Ordina</a></button></th>
			<th>Categoria<button><a href="product?sort=categoria"> Ordina</a></button></th>
		</tr>
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProdottoBean bean = (ProdottoBean) it.next();
		%>
		<tr>
			<td><%=bean.getID_Prodotto()%></td>
			<td><%=bean.getMarca()%></td>
			<td><%=bean.getModello()%></td>
			<td><%=bean.getColore()%></td>
			<td><%=bean.getPrezzo()%></td>
			<td><%=bean.isDisponibilita()%></td>
			<td><%=bean.getDescrizione()%></td>
			<td><%=bean.getCategoria()%></td>
			<td><button><a href="details?action=read&id=<%=bean.getID_Prodotto()%>">Dettagli</a></button></td>
			<td>
				<form action="cart" method="post">	
				<input type="hidden" name="action" value="addCart">	
				<input type="hidden" name="id" value="<%=bean.getID_Prodotto()%>">
				<input type="hidden" name="qty" value="1">
				<input type="submit" value="Aggiungi al carrello">
				</form>
			</td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Nessun prodotto disponibile</td>
		</tr>
		<%
			}
		%>
	</table>
	
	
	<%
		if (product != null) {
	%>
	
	<h2>Dettagli</h2>
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
		</tr>
		<tr>
			<td><%=product.getID_Prodotto()%></td>
			<td><%=product.getMarca()%></td>
			<td><%=product.getModello()%></td>
			<td><%=product.getColore()%></td>
			<td><%=product.getPrezzo()%></td>
			<td><%=product.isDisponibilita()%></td>
			<td><%=product.getDescrizione()%></td>
			<td><%=product.getCategoria()%></td>
		</tr>
	</table>
	<%
		}
	%>
	
	<jsp:include page="footer.jsp"/>
	
</body>
</html>