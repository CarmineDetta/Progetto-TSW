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
	<link href="style/Dettaglio.css" rel="stylesheet" type="text/css">
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
			<form action="cart" method="post">	
				<input type="hidden" name="action" value="addCart">	
				<input type="hidden" name="id" value="<%=product.getID_Prodotto()%>">
				
				<input type="number" name="qty" value="1" min="1" id="numeri">
				<input type="submit" value="Aggiungi al carrello" id="butt">
			</form>
		</div>
		
		</div>

	</div>			
			<div id="descrizione">
				
				<div class="title_descr">
					<p>Descrizione</p> <!-- bottone come quelli dei social, in cui quando clicco con javascript mi apre la descrizione -->
					
					<button tyype="button" onclick="document.getElementById('descr').innerHTML = '<%=product.getDescrizione()%>' "> <img src="https://www.svgrepo.com/show/512676/plus-1512.svg"  title="description" alt="Description icon" width="15" height="15"></button>
				</div>
				
				<a id="descr"> </a>
			
			</div>
		
</div>						

	<%
		}
	%>

	<jsp:include page="footer.jsp"/>
	
</body>
</html>