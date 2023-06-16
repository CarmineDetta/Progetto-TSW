<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./product");	
		return;
	}
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.ProdottoBean"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Catalogo | ShoeMustGoOn</title>
	<link rel="stylesheet" href="style/Catalogo_Utente.css" type="text/css">
	<link rel="stylesheet" href="style/footercatalogo.css" type="text/css">
	<link rel="stylesheet" href="style/neon.css" type="text/css">
</head>
<body>
    <section id="content-main">
        <header>
            <jsp:include page="header.jsp" />
        </header>
		<div id=sidebar>

		</div>
		<div id =sidebar2>
			
		</div>
    <section>
    <section id ="Neon">
		<div id="container">
			<span id="txt" >ShoeMustGoOn</span>
			<span id="gradient"></span>
			<span id="light"></span>
		</div>
	</section>
	   <div class="product">
			<% if (products != null && products.size() != 0) {
			       Iterator<?> it = products.iterator();
		    	   while (it.hasNext()) {
			          ProdottoBean bean = (ProdottoBean) it.next();
			          if (bean.isDisponibilita() == true) {
			 %>
			<div class="product-item">
			     <button>
			         <a href="details?action=read&id=<%=bean.getID_Prodotto()%>">
			           <img src="<%=bean.getImmagine().getPath()%>" alt="Immagine prodotto" width="201" height="251" id="imgmod">
			         </a>
			     </button>
		         <h3>
			        <%=bean.getMarca()%>
			          		-
			        <%=bean.getModello()%>
		         </h3>
		         <p>
		            <%=bean.getDescrizione()%>
		            <br>
		            <br>
			        <%=bean.getPrezzo()%>€
			     </p>
	       </div>
			    <% }
			      }
			    } else { %>
			    <h1 colspan="6">Nessun prodotto disponibile</h1>
			    <% } %>
		   </div>
     </section>
    </section>
            <footer>
            <jsp:include page="footer.jsp"/>
        </footer>
</body>
</html>