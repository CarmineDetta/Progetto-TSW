<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./admin");	
		return;
	}
	ProdottoBean product = (ProdottoBean) request.getAttribute("product");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.ProdottoBean"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Catalogo Admin</title>
	<link rel="stylesheet" href="style/catalogoadmin2.css" type="text/css">
</head>
<body>
	<header>
		<jsp:include page="header.jsp" />
	</header>
    <section id="content-main">
        <div id="insert">
            <a href="Inserimento_Admin.jsp"><button>Inserisci un prodotto</button></a>
        </div>
       	<div id=sidebar>

<<<<<<< HEAD
	<jsp:include page="header.jsp" />
	
	<h2>Prodotti</h2>
	
	<table border="1">
		<tr>
			<th>ID_Prodotto<button><a href="admin?sort=ID_Prodotto"> Ordina</a></button></th>
			<th>Marca<button><a href="admin?sort=marca"> Ordina</a></button></th>
			<th>Modello<button><a href="admin?sort=modello"> Ordina</a></button></th>
			<th>Colore<button><a href="admin?sort=colore"> Ordina</a></button></th>
			<th>Prezzo<button><a href="admin?sort=descrizione"> Ordina</a></button></th>
			<th>Quantita<button><a href="admin?sort=quantita"> Ordina</a></button></th>
			<th>Disponibilita<button><a href="admin?sort=disponibilita"> Ordina</a></button></th>
			<th>Descrizione<button><a href="admin?sort=descrizione"> Ordina</a></button></th>
			<th>Categoria<button><a href="admin?sort=categoria"> Ordina</a></button></th>
=======
		</div>
		<div id =sidebar2>
>>>>>>> branch 'master' of https://github.com/CarmineDetta/Progetto-TSW
			
		</div>
        <%
            if (products != null && products.size() != 0) {
                Iterator<?> it = products.iterator();
                while (it.hasNext()) {
                    ProdottoBean bean = (ProdottoBean) it.next();
        %>       
        <section id="container">
            <div id="image">
                <img src="<%=bean.getImmagine().getPath()%>" alt="Immagine prodotto" width="300" height="430">
            </div>
            <div id="product-item">
                <p>ID Prodotto: <%=bean.getID_Prodotto()%></p>
                <p>Marca: <%=bean.getMarca()%></p>
                <p>Modello: <%=bean.getModello()%></p>
                <p>Colore: <%=bean.getColore()%></p>
                <p>Prezzo: <%=bean.getPrezzo()%></p>
                <p>Quantità: <%=bean.getQuantita()%></p>
                <p>Disponibilità: <%=bean.isDisponibilita()%></p>
                <p>Descrizione: <%=bean.getDescrizione()%></p>
                <p>Categoria: <%=bean.getCategoria()%></p>
                <div>
                    <button class="button-elimina">
                        <a href="admin?action=delete&id=<%=bean.getID_Prodotto()%>">Elimina</a>
                    </button>
                </div>
           </div>
        </section>
        <%
            }
        } else {
        %>
        <div class="table-cell" colspan="9">Nessun prodotto disponibile</div>
        <%
            }
        %>          
    </section>
    <footer>
    	<jsp:include page="footer.jsp" />
    </footer>
</html>