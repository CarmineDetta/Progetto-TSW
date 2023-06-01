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
		<Button><a href ="Pagamenti_Utente.jsp"> <img src="https://www.svgrepo.com/show/442554/pay.svg" title="payement" alt="payement icon">Visualizza o aggiorna i tuoi metodi di pagamento</a></Button>
	
		<Button><a href ="Indirizzi_Utente.jsp"> <img src="https://www.svgrepo.com/show/252952/lorry.svg" title="delivery" alt="delivery icon">Visualizza o aggiorna i tuoi indirizzi di spedizione</a></Button>
		
		<Button><a href="Dati_Utente.jsp"> <img src="https://www.svgrepo.com/show/514283/user.svg" title="user" alt="user icon">Visualizza o aggiorna i tuoi dati personali</a></Button>
	
		<Button><a href="Ordini_Utente.jsp"> <img src="https://www.svgrepo.com/show/493960/order.svg" title="order" alt="ordini icon"> Visualizza i tuoi ordini</a></Button>
	</div>
	
	<div id="logos">
		<img src="image/myprofile.png" width="300" height="250"  title="imaaeprofile" alt="img-profile">
	</div>
  </div>
 </div>
	<jsp:include page="footer.jsp" />
	
</body>
</html>