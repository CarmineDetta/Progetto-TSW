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
	<link rel="stylesheet" href="style/NEon.css" type="text/css">
	<link rel="stylesheet" href="style/Sidebar.css" type="text/css">
	
	<link rel="stylesheet" href="style/catalogo_Utente.css" type="text/css">
	
	<title>ShoeMustGoOn | Catalogo Utente</title>
</head>

<body>
        <header>
            <jsp:include page="header.jsp" />
        </header>
  
  <!--          
		<div id=sidebar></div>
		
		<div id =sidebar2></div>
	-->
	  
<section id="content-main">
         
    
    <!--  scritta neon del titolo -->
    <div id ="Neon">	
		<div id="container">
			<div id="txt" >ShoeMustGoOn</div>
			<div id="light"></div>
		</div>
	</div>


	   <div class="product">
			<% if (products != null && products.size() != 0) {
			       Iterator<?> it = products.iterator();
		    	   while (it.hasNext()) {
			          ProdottoBean bean = (ProdottoBean) it.next();
			          if (bean.isDisponibilita() == true) {
			 %>
			 
			
			<div class="product-item">
			     <button>
			     	<a href="details?action=read&id=<%=bean.getID_Prodotto()%>"><img src="<%=bean.getImmagine().getPath()%>" alt="Immagine prodotto" width="175" height="240" id="imgmod"></a>
			     </button>
		         
		         <div class="marca-modello">
			        <%=bean.getMarca()%>
			          		-
			        <%=bean.getModello()%>
		         </div>
		         
		         <div id="prezz">
		            <br>
			        <%=bean.getPrezzo()%>€
			     </div>
	       </div>
	       
			<%
			    	}
			      }
			    
			} else { 
			
			%>
			    	<h1 colspan="6">Nessun prodotto disponibile</h1>
			 <% 
			   	} 
			  %>
		   </div>
    
</section>
          
       <footer>
            <jsp:include page="footer.jsp"/>
        </footer>
</body>
</html>