<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	ProdottoBean product = (ProdottoBean) request.getAttribute("product");
	if(product == null) {
		response.sendRedirect("./details");	
		return;
	}
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.ProdottoBean"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><%=product.getID_Prodotto()%></title>
</head>
<body>

	<jsp:include page="header.jsp" />
	
	<%
		if (product != null) {
	%>
	
	<h2></h2>
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
			<td>
				<form action="cart" method="post">	
				<input type="hidden" name="action" value="addCart">	
				<input type="hidden" name="id" value="<%=product.getID_Prodotto()%>">
				<input type="hidden" name="qty" value="1">
				<input type="submit" value="Aggiungi al carrello">
				</form>
			</td>		
		</tr>
	</table>
	<%
		}
	%>
	
	<jsp:include page="footer.jsp"/>
	
</body>
</html>