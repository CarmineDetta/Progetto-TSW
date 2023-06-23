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
<html lang="it" xml:lang="it">
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.ProdottoBean"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="style/NEOn.css" type="text/css">
	<link rel="stylesheet" href="style/side-bar.css" type="text/css">
	
	<link rel="stylesheet" href="style/catalogo-Utente.css" type="text/css">
	
	<title>ShoeMustGoOn | Catalogo Utente</title>
</head>

<body>
        <header>
            <jsp:include page="header.jsp" />
        </header>

<section id="content-main">
         
    
    <div class="part-title">

    	<div class="testo_immagini">
		    	<img id="imagine" src="image/freddy.jpg" width="90" height="90" title="complete-icon" alt="complete icon"><br>							 
		    	
		    	 <!--  scritta neon del titolo -->
			    <div id ="Neon">	
					<div id="container">
						<div id="txt" >ShoeMustGoOn</div>
						<div id="light"></div>
					</div>
				</div>
		    
		    	<img id="imagine" src="image/freddy.jpg" width="90" height="90" title="complete-icon" alt="complete icon"><br>							 
    	</div>
    	
    	<div class="video">
		    <video id="video-element" autoplay muted>
		  		<source src="image/video-catalog.mp4"  type="video/mp4" >
		 	</video>
		    
		    <video id="video-element2" autoplay muted>
		  		<source src="image/video-catalogo2.mp4"  type="video/mp4" >
		 	</video>
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
			          		<br>-<br>
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
        
        <script>
	
        document.addEventListener('DOMContentLoaded', function() {
        	  var video = document.getElementById('video-element');

        	  video.addEventListener('ended', function() {
        	    video.currentTime = 0; // Riavvia il video dall'inizio
        	    video.play(); // Avvia nuovamente la riproduzione automatica
        	  });
        	});
	
        document.addEventListener('DOMContentLoaded', function() {
      	  var video = document.getElementById('video-element2');

      	  video.addEventListener('ended', function() {
      	    video.currentTime = 0; // Riavvia il video dall'inizio
      	    video.play(); // Avvia nuovamente la riproduzione automatica
      	  });
      	});

	</script>
	
</body>





</html>