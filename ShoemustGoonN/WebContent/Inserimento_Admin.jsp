<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.ProdottoBean"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="style/inserimento_admin.css">
	<title>ShoeMustGoOn | Inserimento Prodotti</title>
</head>

<style>
body{
	background-image: url("image/sfondo_insert_admin.png");
}
</style>
<body>

	<jsp:include page="header.jsp" />

	<div class="div_insert">
	
	<a href="Catalogo_Admin.jsp"><button class="homebutton">Home Amministratore</button></a><br>

	<form class="insert_form" action="admin" method="post" enctype="multipart/form-data">
			<input type="hidden" name="action" value="insert"> 
		
		<h1>Inserimento:</h1>
		
			<div class="insert">
				<label  class="label_mod" for="marca">Marca:</label> 
				<input class="box" name="marca" type="text" maxlength="20"  placeholder="Inserisci una marca" required>
			</div>
			
			<div class="insert">
				<label class="label_mod" for="modello">Modello:</label>
				<input class="box"  name="modello" type="text" maxlength="20"  placeholder="Inserisci un modello" required>
			</div>
			
			<div class="insert">
				<label class="label_mod"  for="colore">Colore:</label>
				<input class="box_color" name="colore" type="color" required> 
			</div>
			
			<div class="insert">
				<label class="label_mod"  for="prezzo">Prezzo:</label> 
				<input class="box"  name="prezzo" type="number" min="1" value="1" required>
			</div>
			
			<div class="insert">
				<label class="label_mod"  for="quantita">Quantità:</label> 
				<input class="box" name="quantita" type="number" min="1" value="1" required>
			</div>
			
			<div class="insert">
				<label class="label_mod"  for="disponibilita">Disponibilità:</label>
				<input class="box_disp"  type="radio" name="disp" value="Si">
				<label class="label_mod"  for="Si">Si</label>
				<input class="box_disp"  type="radio" name="disp" value="No">
				<label class="label_mod"  for="No">No</label>
			</div>
			
			<div class="insert">		
				<label class="label_mod"  for="descrizione">Descrizione:</label>
				<textarea class="box"  name="descrizione" maxlength="300"  placeholder="Inserisci una descrizione" required></textarea>
			</div>
			
			<div class="insert">
				<label class="label_mod"  for="immagine">Immagine</label>
				<input type="hidden" name="immagine" value="null">
			</div>
			
			<div class="insert">
				<label class="label_mod"  for="categoria">Categoria:</label>
			
				<select class="box_option"  name="categoria">
					<option value="uomo">Uomo</option>
					<option value="donna">Donna</option>
					<option value="unisex">Unisex</option>
				</select>
			</div>
			
			<div>
				<input class="pulsanti" type="submit" value="Aggiungi">
				<input class="pulsanti" type="reset" value="Reset">
			</div>
		</form>	
	</div>
	
		<jsp:include page="footer.jsp" />
</body>
</html>