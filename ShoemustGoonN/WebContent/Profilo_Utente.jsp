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
		
		
	<Button><a href ="payments"><img src="https://www.svgrepo.com/show/502788/payment-card.svg"  title="payement" alt="Payement icon">Visualizza o aggiorna i tuoi metodi di pagamento</a></Button><br><br>
	
	<Button><a href ="recapiti"><img src="https://www.svgrepo.com/show/447599/delivery-fast.svg"  title="spedizione" alt="Delivery icon">Visualizza o aggiorna i tuoi indirizzi di spedizione</a></Button><br><br>
	
	<Button><a href="utente"><img src="https://www.svgrepo.com/show/513868/user.svg"  title="user" alt="User icon">Visualizza o aggiorna i tuoi dati personali</a></Button><br><br>
	
	<Button><a href="ordine"><img src="https://www.svgrepo.com/show/376272/task-done.svg"  title="order" alt="Order icon">Visualizza i tuoi ordini</a></Button><br><br>
	
  </div>
  
  	<div id="logos">
		<img src="image/myprofile.png" width="300" height="250"  title="imaaeprofile" alt="img-profile">
	</div>
 </div>
</div>
	<jsp:include page="footer.jsp" />
	
</body>
</html>