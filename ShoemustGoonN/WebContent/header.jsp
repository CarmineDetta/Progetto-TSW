<!DOCTYPE html>
<html lang="it">
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <link rel="stylesheet" href="style/Header.css" type="text/css">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	   	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	    <title>ShoeMustGoOn | Header</title>
	</head>

	<body>
	    <header id="header-large">
	        <div>
	            <a href="Homepage.jsp"> <img src="image/loggo.jpg" alt="logo" class="logo"></a>
	        </div>
	
	        <div class="parametri">
	        
					<div id="barra" class="ricerca"> 
						<img src="https://www.svgrepo.com/show/508177/search-square.svg" width="28" height="28" title="search-icon" alt="search icon"><br>							 
						<input placeholder="Cerca Prodotti" class="search" type="text" id="search">
					</div>
					
					<div id ="ricerca" class="barra_ricerca"> <input type="hidden"> </div>
					 
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
             	<a href="Inserimento_Admin.jsp"><button>Inserisci un prodotto</button></a><br>
             	<a href="ordine?action=visualizza_tutti"><Button>Gestisci ordini</Button></a>
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
	</body>

	
</html>	
	
	
	
	