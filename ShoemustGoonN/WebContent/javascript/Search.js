	//search è l'id dell'input della ricerca di ricerca
	$('#search').on('input focus', function(e) {
	
			let value = $('search').val();
	
			//ricerca è l'id della div della ricerca di ricerca
		$("#ricerca").html("");
		hideSearcResult();
	
	
		if(value && value.lenght > 0){
			
				let url = "SearchAjaxServlet?cerca="+value;
			
			$.get( url , function( data ){
				
				$("#ricerca").html("");
				
				if (data && data.length > 0){
					
					for (i = 0; i < min(data.length,5); i++){
						
						let elem = data[i];
						
						$("#ricerca").append("<div class=\"searchableitem\" data-cod=\""+elem.ID_Prodotto +"\" onclick=\"openSearch(this)\"><div class=\"item-name\">"+elem.Marca +"</div></div>");
					}
				
					$("#ricerca").show();
				}else {
					
					$("#ricerca").append("<div class=\"searchableitem\">Nessun elemento trovato</div>");
					$("#ricerca").show();
				}
			});
		}
	});	
	
	
	var hovered = false;

	$("#ricerca").bind("mouseover",function(){
		 hovered = true;
		 
	}).bind("mouseout",function() {	
		 hovered = false;
	});
	
	function showSearchResult(){
		$("#ricerca").show();
	}
	
	function hideSearchResult(){
		if (!hovered)
		$("#ricerca").hide();
	}

	function min(a,b){
		if ( a <= b) return a;
		return b
	}
	
	function openSearch(elem){
		let codice = $(elem).data("cod");
		let url = "SearchServlet?cerca="+codice; 
		$.get( url , function( data ){
			window.location.href = 'Dettaglio_Prodotto.jsp';
		});
	}
