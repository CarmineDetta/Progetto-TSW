<!DOCTYPE html>
<html lang="it">
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <link rel="stylesheet" href="style/header.css" type="text/css">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	   	
	    <title>ShoeMustGoOn | Header</title>
	</head>

	<body>
	    <header id="header-large">
	        <div>
	            <a href="Homepage.jsp"> <img src="image/loggo.jpg" alt="logo" class="logo"></a>
	        </div>
	
	        <div class="parametri">
	        
	        <!-- barra ricerca -->
					<div id="barra">
							
						<img src="https://www.svgrepo.com/show/508177/search-square.svg" width="28" height="28" title="search-icon" alt="search icon"><br>							 
						<form action="SearchServlet" method="get">
							<input placeholder="Cerca Prodotti" type="text" id="search">
							
						</form>			
						<div id ="barra"> <input type="hidden"> </div>
					</div>
					
					
					 
 			<%
                if(session.getAttribute("AdminLoggato") == null){ 
            %>	
		        <a href="Catalogo_Utente.jsp">Catalogo</a>
		        <a href="Carrello.jsp">
		             <img src="https://www.svgrepo.com/show/505594/cart-4.svg"  width="28" height="28" title="cart-icon" alt="cart icon"><br>
				</a>
		        
		     <%
                }else{
             %>
             	<br><a href="Inserimento_Admin.jsp">
             		<img src="https://www.svgrepo.com/show/514200/plus-folder.svg"  width="28" height="28" title="add-icon" alt="add product icon"><br>
             	</a>
             	<br><a href="ordine?action=visualizza_tutti">Gestisci Ordini</a>
             <%  	
                }
              	if(session.getAttribute("UtenteLoggato") != null){
              %>
              		<a href="Profilo_Utente.jsp">
              			<img src="https://www.svgrepo.com/show/510311/user-folder.svg"  width="28" height="28" title="account-icon" alt="account icon"><br>             		
              		</a>
              <%		
              	}
              	if(session.getAttribute("UtenteLoggato") == null && session.getAttribute("AdminLoggato") == null){
              %>
              
		        <a href="Login.jsp">
		           	<img src="https://www.svgrepo.com/show/451412/user-key.svg" width="28" height="28" title="login_icon" alt="login icon"><br>
				</a>
				
			<%
              	}else{
            %>
               		<a href="Logout?action=Logout">
               		 	<img src="https://www.svgrepo.com/show/506720/logout.svg" width="28" height="28" title="logout-icon" alt="logout icon"><br>
               		</a>
		      <%
              	}
              %> 	
	        </div>
	    </header>
	    
	    <!--  
	    	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	    	<script src="javascript/Search.js"></script>
	    -->
	</body>

	
</html>	
	
	
	
	