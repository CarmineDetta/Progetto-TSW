<% 
ProdottoBean prodotto = (ProdottoBean) request.getAttribute("IDproduct");

if (prodotto == null)
{	
    response.sendRedirect("./prova.jsp");
    return;
}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>   
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*,control.*,java.text.DecimalFormat"%>
 <!-- include file="Header.jsp"  con tag apertura e chiusura codice java in html --> 


<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!--<link href="CSS/oggetto.css" rel="stylesheet" type="text/css">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1"> -->

	<title><%=prodotto.getNome()%></title>
</head>

<body>
<!-- FACCIO QUESTO PER FORMATTARE I NUMERI IN DECIMALI, SETTANTO IL MAX E IL MIN DI CIFRE DOPO LA VIRGOLA CON DUE CIFRE, 
USATO PER GLI ELEMENTI CHE IDENTIFICANO IL PREZZO DI QUALCOSA  -->
<%
	DecimalFormat formatoPrezzo = new DecimalFormat();
	formatoPrezzo.setMaximumFractionDigits(2);
	formatoPrezzo.setMinimumFractionDigits(2);
%>

	<div class="content"></div>
<!--  controllo sulle immagini del prodotto (DA SVILUPPARE GETIMMAGINE IN PRODOTTOBEAN)
	<div class="containerDettagli">
        <div class="sezioneFoto">
        	tag
	            if(prodotto.getImmagineProdotto() == null) {
	            	tag
	            		<img src="Image/noImage.png" alt="" class="fotoProdottoAssente">
	            	tag	
	            } else {
	            	tag
	        			<img src="data:image/png;base64,tagconuguale prodotto.getImmagineProdotto() tag" alt="" class="fotoProdotto">
	            	tag
	            }
            tag 
            
            </div>-->
                         
         <div class="sezioneTesto">
            <div class="nomeProdotto"><%=prodotto.getNome()%></div>
            <div class="categoriaProdotto">Categoria: <%=prodotto.getCategoria()%></div>
            <div class="prezzoProdotto">€ <%=formatoPrezzo.format(prodotto.getPrezzo())%></div>
		</div>

		<div class="descrizioneProdotto">Descrizione:<br><br><%=prodotto.getDescrizione()%></div>
            
         <div class="sezioneFormPulsante">
            <%
	            if(prodotto.getQuantita() == 0) {
	            	%>
	            		<button class="pulsanteNonDisponibile"><ion-icon class="iconaPulsanteCarrello" name="close-outline" size="large"></ion-icon>NON DISPONIBILE</button>
	            	<%	
	            } else {
	            	%>
            		<form action="cart" method="post">
			        	<input type="hidden" name="addID" value="<%=prodotto.getID_Prodotto()%>">
			        	<input name="quantity" class="quantitaProdotto" type="number" value="1" min="1"  max="<%=prodotto.getQuantita()%>" required>
			        	<button type="submit" class="pulsanteCarrello"><ion-icon class="iconaPulsanteCarrello" name="cart-outline" size="large"></ion-icon>AGGIUNGI AL CARRELLO</button>  
			        </form>
	            	<%
	            }
            %>
            </div>

</body>

<div class="content"></div><div class="content"></div><div class="content"></div>
 <!--  include file="Footer.html" -->

</html>