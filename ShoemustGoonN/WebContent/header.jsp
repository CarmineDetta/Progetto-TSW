<!DOCTYPE html>
<html lang="it" xml:lang="it">
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <link rel="stylesheet" href="style/Header.css" type="text/css">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	    <title>ShoeMustGoOn | Header</title>
	</head>

	<body>
	
	    <header id="header-large">
	        <div>
	            <a href="Homepage.jsp"> <img src="image/loggo.jpg" alt="logo" class="logo"></a>
	        </div>
	
	        <div class="parametri">
			        
			        <!-- barra ricerca -->
					
					<div id="barraRicerca">
						<input type="text" placeholder="Barra di ricerca" class="campoRicerca" id="queryRicerca" onkeyup="funzioneRicerca()"/>
							
							<button type="submit" class="bottoneRicerca">
								<ion-icon name="search-outline" class="" id=""></ion-icon>
							</button>
						
						<div id="risultati"></div>
					</div>
		
			       
			        <% if(session.getAttribute("AdminLoggato") == null) { %>	
			        
			        <div id="icona">	
			        	<a href="Catalogo_Utente.jsp">Catalogo</a>
			        </div>
			        
			        <div id="icona">
				        <a href="Carrello.jsp">
				            <img src="https://www.svgrepo.com/show/505594/cart-4.svg"  width="28" height="28" title="cart-icon" alt="cart icon"><br>
				       	</a>
			        </div>
			        <% 
				        } 
				        	else 
				        { 
			        %>
			        
			        <br>
			        <div id="icona">
				        <a href="Inserimento_Admin.jsp">
				            <img src="https://www.svgrepo.com/show/514200/plus-folder.svg"  width="28" height="28" title="add-icon" alt="add product icon"><br>
				        </a>
			        </div>
			        
			        <br>
			        
			        <div id="icona">
			        	<a href="ordine?action=visualizza_tutti">Gestisci Ordini</a>
			        </div>
			        <% 
			        	}
			        %>
			        
			        <% if(session.getAttribute("UtenteLoggato") != null) { %>
			        
			        <div id="icona">
				        <a href="Profilo_Utente.jsp">
				            <img src="https://www.svgrepo.com/show/510311/user-folder.svg"  width="28" height="28" title="account-icon" alt="account icon"><br>
				        </a>
			        </div>
			        <% 
			       		} 
			        %>
			        
			        <% if(session.getAttribute("UtenteLoggato") == null && session.getAttribute("AdminLoggato") == null) { %>
			        
			        <div id="icona">
				        <a href="Login.jsp">
				            <img src="https://www.svgrepo.com/show/451412/user-key.svg" width="28" height="28" title="login_icon" alt="login icon"><br>
				        </a>
			        </div>
			        <% 
			        	} 
			        		else 
			        	{ 
			        %>
			        
			        <div id="icona">
				        <a href="Logout?action=Logout">
				            <img src="https://www.svgrepo.com/show/506720/logout.svg" width="28" height="28" title="logout-icon" alt="logout icon"><br>
				        </a>
			        </div>
			        
			        <% 
			        	} 
			        %> 	
	   		</div>
	        
	    </header>
	     
	       <script>


	  function funzioneRicerca() {
			
			var stringaParziale = document.getElementById("queryRicerca").value;

			$.ajax({  
				async: true,
	            //uri della servlet
	            url: "AjaxSuggestControl",  
	            //tipo di richiesta
	            method: "POST",
	            //dati inviati al server
	            data: "stringaRicerca=" + stringaParziale,
	            //tipo dato ricevuto dalla servlet
	            dataType: "json",          
	            success: function(data, textStatus, jqXHR) {
	            	
	            	$("#risultati").empty();
	            	
	            	if( data.length >=1) {
	            		$("#risultati").empty();
		            	$("#risultati").addClass( "DivRisultati" );
		            	for (const i in data) {
		            		$( "#risultati" ).append('<div id=""><a href="details?action=read&id='+data[i].idProdotto+'">'+data[i].marca+'</a></div>');
						}
		            	
	            	} else {
	            		$("#risultati").empty();
	            		$("#risultati").removeClass( "DivRisultati" );
	            	}
	            	
	            	if(stringaParziale === ""){
	            		
	            		$("#risultati").removeClass( "DivRisultati" );
	            	}
	            	
	            },
	            error: function(jqXHR, textStatus, errorThrown){
	            	console.log(jqXHR);
	            } 
	        });
		}
		
	</script>
	</body>
</html>
