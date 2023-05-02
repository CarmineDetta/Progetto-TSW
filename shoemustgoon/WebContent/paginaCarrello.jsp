
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./prova.jsp");	
		return;
	}
	
	ProdottoBean product = (ProdottoBean) request.getAttribute("product");
	
	ListaProdottiBean cart = (ListaProdottiBean) request.getAttribute("cart");
%>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,control.*, model.*"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
	<title>Storage</title>
</head>

<body>

	<!--  aggiungere include file="Header.jsp" -->
		
		<h2>Products</h2>
	<a href="product">List</a>
	<table border="1">
		<tr>
			<th>Code <a href="product?sort=code">Sort</a></th>
			<th>Name <a href="product?sort=name">Sort</a></th>
			<th>Description <a href="product?sort=description">Sort</a></th>
			<th>Action</th>
		</tr>
		
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProdottoBean bean = (ProdottoBean) it.next();
		%>
		
		<tr>
			<td><%=bean.getID_Prodotto()%></td>
			<td><%=bean.getNome()%></td>
			<td><%=bean.getDescrizione()%></td>
			<td><a href="product?action=delete&id=<%=bean.getID_Prodotto()%>">Delete</a><br>
				<a href="product?action=read&id=<%=bean.getID_Prodotto()%>">Details</a><br>
				<a href="product?action=addC&id=<%=bean.getID_Prodotto()%>">Add to cart</a>
				</td>
		</tr>
		
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">No products available</td>
		</tr>
		<%
			}
		%>
		</table>
		<h2>Details</h2>
	<%
		if (product != null) {
	%>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Nome</th>
			<th>Descrizione</th>
			<th>Prezzo</th>
			<th>Quantita</th>
		</tr>
		<tr>
			<td><%=product.getID_Prodotto()%></td>
			<td><%=product.getNome()%></td>
			<td><%=product.getDescrizione()%></td>
			<td><%=product.getPrezzo()%></td>
			<td><%=product.getQuantita()%></td>
		</tr>
	</table>
	<%
		}
	%>
	
	<h2>Insert</h2>
	<form action="product" method="post">
		<input type="hidden" name="action" value="insert"> 
		
		<label for="name">Name:</label><br> 
		<input name="name" type="text" maxlength="20" required placeholder="enter name"><br> 
		
		<label for="description">Description:</label><br>
		<textarea name="description" maxlength="100" rows="3" required placeholder="enter description"></textarea><br>
		
		<label for="price">Price:</label><br> 
		<input name="price" type="number" min="0" value="0" required><br>

		<label for="quantity">Quantity:</label><br> 
		<input name="quantity" type="number" min="1" value="1" required><br>

		<input type="submit" value="Add"><input type="reset" value="Reset">
	</form>
	
	<% List<ProdottoBean> prodcart = cart.getProdotto(); 	
		   for(ProdottoBean beancart: prodcart) {
		%>
		<tr>
			<td><%=beancart.getNome()%></td>
			<td><a href="product?action=deleteC&id=<%=beancart.getID_Prodotto()%>">Delete from cart</a></td>
		</tr>
		<% } %>
	</table>		

	<!-- footer -->
	</body>
</html>