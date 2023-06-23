<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*"%>


<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="style/homepage.css">
	<title>ShoeMustGoOn | HomePage</title>
</head>


<style>
body{
	background-image: url("image/imm_home_nike.jpg");	
}
</style>

<body>

	<jsp:include page="header.jsp"/>
	
	<div class="center">
			
	
		<div class="title">
			<span id="testo">ShoeMustGoOn</span>
		</div>
		
		<%
                if(session.getAttribute("AdminLoggato") == null){ 
         %>
         
		<div class="button">
			<button class="uname" ><a href="Catalogo_Utente.jsp">Vai al Catalogo</a></button>
		</div>
		
		<%
              }else{
         %>
         
         <div class="button">
			<button class="uname" ><a href="Catalogo_Admin.jsp">Vai al Catalogo</a></button>
		</div>
		<%
         	}
         %> 
	</div>	
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		<jsp:include page="footer.jsp"/>
	
</body>
</html>