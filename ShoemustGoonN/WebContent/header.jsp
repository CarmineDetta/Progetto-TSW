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
						<input type="text" placeholder="Cerca Prodotti" class="campoRicerca" id="queryRicerca" onkeyup="funzioneRicerca()"/>
							
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
		$('#pulsanteRicercaResponsive').on('click', function() {
			
			Swal.fire({
				  showConfirmButton: false,	//Questa proprietà nasconde il pulsante di conferma nel popup.
				  html: '<h1>Ricerca</h1><div id="barraRicercaResponsive"><input type="text" placeholder="Cerca prodotti" class="campoRicercaResponsive" id="queryRicercaResponsive" onkeyup="funzioneRicerca()"/><button type="submit" class="bottoneRicercaResponsive"><ion-icon name="search-outline" class="" id=""></ion-icon></button><div id="risultatiResponsive"></div></div>',
				  customClass: { popup: 'borderBoxPopUp'},		//Questa proprietà applica una classe CSS personalizzata al popup. In questo caso, il nome della classe è borderBoxPopUp.
				})
		} )
		
		
		function funzioneRicerca() {
			
			$("#risultati").empty(); //Svuota il contenuto dell'elemento con l'ID "risultati".
        	$("#risultatiResponsive").empty();
        	$("#risultati").removeClass( "DivRisultati" );	//Rimuove la classe "DivRisultati" dall'elemento con l'ID "risultati".
    		$("#barraRicerca").css({"border-bottom-left-radius":"20px"});
        	$("#barraRicerca").css({"border-bottom-right-radius":"20px"});
			
			console.log("Inizia la funzione di ricerca")
			var stringaParziale;

			
			
			if(document.getElementById("queryRicerca").value === ""){
				stringaParziale = document.getElementById("queryRicercaResponsive").value;
				console.log("Ricerca query responsive");
			} else {
				stringaParziale = document.getElementById("queryRicerca").value;
				console.log("Ricerca query normale");
			}
			
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
	            	$("#risultatiResponsive").empty();
	            	
	            	if( data.length >=1) {
	            		$("#risultatiResponsive").empty();
	            		$("#risultati").empty();
		            	$("#barraRicerca").css({"border-bottom-left-radius":"0px"});
		            	$("#barraRicerca").css({"border-bottom-right-radius":"0px"});
		            	$("#risultati").addClass( "DivRisultati" );
		            	for (const i in data) {
		            		$( "#risultati" ).append('<div id=""><a href="details?action=read&id='+data[i].idProdotto+'">'+data[i].marca+'</a></div>');
		            		$( "#risultatiResponsive" ).append('<div id=""><a href="details?action=read&id='+data[i].idProdotto+'">'+data[i].marca+'</a></div>');
						}
		            	
	            	} else {
	            		$("#risultatiResponsive").empty();
	            		$("#risultati").empty();
	            		$("#risultati").removeClass( "DivRisultati" );
	            		$("#barraRicerca").css({"border-bottom-left-radius":"20px"});
		            	$("#barraRicerca").css({"border-bottom-right-radius":"20px"});
	            	}
	            	
	            	if(stringaParziale === ""){
	            		
	            		$("#risultati").removeClass( "DivRisultati" );
	            		$("#barraRicerca").css({"border-bottom-left-radius":"20px"});
		            	$("#barraRicerca").css({"border-bottom-right-radius":"20px"});
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
