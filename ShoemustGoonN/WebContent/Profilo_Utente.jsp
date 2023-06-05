<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.PortafoglioBean, model.UtenteBean"%>

<head>
    <link href="style/profiloutente.css" rel="stylesheet" type="text/css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ShoeMustGoOn ! Il mio profilo</title>
</head>
	
<body>
	
	<jsp:include page="header.jsp" />
	
	<%
		UtenteBean utente = (UtenteBean)request.getSession().getAttribute("UtenteLoggato");
	%>
 <div class="content">
 
	<div class="title">
		<h2>Benvenuto...</h2><br>
	</div>
	
  <div class="content2">
	<div class="pulsanti">
		
		
	<Button><a href ="payments">Visualizza o aggiorna i tuoi metodi di pagamento</a></Button><br><br>
	
	<Button><a href ="recapiti">Visualizza o aggiorna i tuoi indirizzi di spedizione</a></Button><br><br>
	
	<Button><a href="utente">Visualizza o aggiorna i tuoi dati personali</a></Button><br><br>
	
	<Button><a href="ordine">Visualizza i tuoi ordini</a></Button><br><br>
	
	<div id="logos">
		<img src="image/myprofile.png" width="300" height="250"  title="imaaeprofile" alt="img-profile">
	</div>
  </div>
 </div>
	<jsp:include page="footer.jsp" />
	
</body>
</html>