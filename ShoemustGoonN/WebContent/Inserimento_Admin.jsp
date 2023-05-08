<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.ProdottoBean"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Inserimento Prodotti</title>
</head>
<body>

	<a href="Catalogo_Admin.jsp"><button>Home Amministratore</button></a><br>

	<h2>Inserimento</h2>
		<form action="admin" method="post">
			<input type="hidden" name="action" value="insert"> 
			
			<label for="marca">Marca:</label><br> 
			<input name="marca" type="text" maxlength="20" required placeholder="Inserisci una marca"><br> 
			
			<label for="modello">Modello:</label><br> 
			<input name="modello" type="text" maxlength="20" required placeholder="Inserisci un modello"><br> 
			
			<label for="colore">Colore:</label><br> 
			<input name="colore" type="color"><br> 
			
			<label for="prezzo">Prezzo:</label><br> 
			<input name="prezzo" type="number" min="1" value="1"><br> 
			
			<label for="quantita">Quantità:</label><br> 
			<input name="quantita" type="number" min="1" value="1"><br>
			
			<label for="disponibilita">Disponibilità:</label><br>
			<input type="radio" name="disp" id="Si">
			<label for="Si">Si</label><br>
			<input type="radio" name="disp" id="No">
			<label for="No">No</label><br>		
			
			<label for="descrizione">Descrizione:</label><br>
			<textarea name="descrizione" maxlength="100" rows="3" required placeholder="Inserisci una descrizione"></textarea><br>
			
			<label for="categoria">Categoria:</label><br>
			
			<select name="categoria"><br>
				<option value="uomo">Uomo</option>
				<option value="donna">Donna</option>
				<option value="unisex">Unisex</option>
			</select><br>
	
			<br><input type="submit" value="Add"><input type="reset" value="Reset"><br>
	
		</form>
</body>
</html>