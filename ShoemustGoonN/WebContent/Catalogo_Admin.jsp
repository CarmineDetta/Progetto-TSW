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
<html lang="it" xml:lang="it">
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.ProdottoBean"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ShoeMustGoOn | Catalogo Admin</title>
	<link rel="stylesheet" href="style/catalogo_admin.css" type="text/css">
</head>

<body>
	<header>
		<jsp:include page="header.jsp" />
	</header>

<div class="total_cont">

		<div class="form_scelte">
            
          	<form>
				<label for="opzioni">Ordina Elemento per:</label>
			  	<select id="opzioni" name="opzioni" onchange="redirectOnChange(this)">
			    	<option value="opzione1">Seleziona</option>
			    	<option value="opzione2">ID</option>
			    	<option value="opzione3">Marca</option>
			    	<option value="opzione4">Modello</option>
			    	<option value="opzione5">Colore</option>
			    	<option value="opzione6">Descrizione</option>
			    	<option value="opzione7">Categoria</option>
			  	</select>
			</form>
    
            <a href="Inserimento_Admin.jsp"><button id="butt_insert">Inserisci un prodotto</button></a>
		</div>
		
		
	<h2>Prodotti</h2>
     
     <table border="1">
     <caption>Tabella Catalogo</caption>
     
		<tr>
			<th>ID_Prodotto</th>
			<th>Marca</th>
			<th>Modello</th>
			<th>Colore</th>
			<th>Prezzo</th>
			<th>Quantita</th>
			<th>Disponibilita</th>
			<th>Descrizione</th>
			<th>Categoria</th>
			<th>Immagine</th>
		</tr> 
        <%
            if (products != null && products.size() != 0) {
                Iterator<?> it = products.iterator();
                while (it.hasNext()) {
                    ProdottoBean bean = (ProdottoBean) it.next();
        %>   
   
		
			<tr>
                <td><p><%=bean.getID_Prodotto()%></p></td>
                <td><p><%=bean.getMarca()%></p></td>
                <td><p><%=bean.getModello()%></p></td>
                <td><p><%=bean.getColore()%></p></td>
                <td><p><%=bean.getPrezzo()%></p></td>
                <td><p><%=bean.getQuantita()%></p></td>
                <td><p><%=bean.isDisponibilita()%></p></td>
                <td><p><%=bean.getDescrizione()%></p></td>
                <td><p><%=bean.getCategoria()%></p></td>
                <td><img src="<%=bean.getImmagine().getPath()%>" alt="Immagine prodotto" width="90" height="100"> </td>
                <td><button class="button-elimina"><a href="admin?action=delete&id=<%=bean.getID_Prodotto()%>">Elimina</a></button></td>
			</tr>
    
        <%
            }
        } else {
        %>
        <td colspan="9">Nessun prodotto disponibile</td>
        <%
            }
        %>          
	</table>
</div>

    <footer>
    	<jsp:include page="footer.jsp" />
    </footer>
    

<script>

	/*script per far funzionare la select sopra*/
	
  function redirectOnChange(select) {
    var selectedValue = select.value;
    if (selectedValue === "opzione2") {
      window.location.href = "admin?sort=ID_Prodotto";
    } else if (selectedValue === "opzione3") {
      window.location.href = "admin?sort=Marca";
    }else if (selectedValue === "opzione4") {
        window.location.href = "admin?sort=modello";
    }else if (selectedValue === "opzione5") {
        window.location.href = "admin?sort=colore";
    }else if (selectedValue === "opzione6") {
        window.location.href = "admin?sort=descrizione";
    }else if (selectedValue === "opzione7") {
        window.location.href = "admin?sort=categoria";
    }
  }
 </script>
    
</html>