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
</head>

<body>

	<a href="Inserimento_Admin.jsp"><button>Inserisci un prodotto</button></a><br>
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
			<td><%=bean.getQuantita()%></td>
			<td><%=bean.isDisponibilita()%></td>
			<td><%=bean.getDescrizione()%></td>
			<td><%=bean.getCategoria()%></td>
			<td><img src="<%=bean.getImmagine().getPath()%>" alt="Immagine prodotto" width="80" height="80"></td>
			<td><button><a href="admin?action=delete&id=<%=bean.getID_Prodotto()%>">Elimina</a></button></td><br>
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
	
</body>
</html>