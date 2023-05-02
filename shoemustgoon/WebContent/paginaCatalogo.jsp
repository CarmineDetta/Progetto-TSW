<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- arrivo dalla servlet che chiama questa jsp -->
<%
	request.getSession().getAttribute("");	//richiedo l'apertura della richiesta e prelevo il prodotto inizializzando una collection di prodotti
	Collection<?> prodotto = (Collection<?>) request.getAttribute("prodotto");
	
	//se il prodotto non lo trovo vado nella pagina che specifico sotto (può essere anche una pagina 404.html)
	if(prodotto == null) {
		response.sendRedirect("./prova.jsp");	//pagina di prova per vedere se riesco ad accedere al prodotto	
	}
	
	//creo il prodotto su cui andrò a mettere il prodotto prelevato "product"
	ProdottoBean product = (ProdottoBean) request.getAttribute("product");
	
	//creo la collection di categorie
	Collection<?> categories = (Collection<?>) request.getAttribute("categories");
	
%>

<!DOCTYPE html>
<html>

<!--  include headerfile con tag -->

<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*,control.*"%>
<head>

	<title>Catalogo Prodotti</title>
	<link rel="stylesheet" href="style.css">
</head>

<body>	
	<br></br>
	<br></br>
	
	<main align="center">
		<h2>Prodotti in vendita</h2>
		
		<div class="prodotti">
			<div class="prodotto" >
				<h3>Prodotto</h3>
				<p>Descrizione del prodotto</p>
				<p class="prezzo"> 19,99 </p>
		
				<select name ="mene catalogo" width="300", height="400">
					<option value="uomo"> Uomo</option>
					<option value="donna"> Donna</option>
				</select>
				
				<br></br>
 				<button type="uomo">Uomo</button>
 				<button type="donna">Donna</button>

 
			</div>
	
			<!-- Aggiungere altri prodotti qui -->
		</div>
	
	</main>
	
	<br></br>
	<br></br>
	<br></br>
	
	<footer>
				<nav>
				<h3> Informative:</h3>
				<a href="#">Home</a>
				<a href="#">Prodotti</a>
				<a href="#">Contatti</a>	
		</nav>
	</footer>

</body>
</html>
