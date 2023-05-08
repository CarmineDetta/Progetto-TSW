<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
  </head>
<body>
        <div>
			<a href="homepage.jsp"> <img src="image/Logo.jpg" alt="Logo del tuo ecommerce" width=50 height=50 align=left></a>
        </div>       
    	<div>
        	<form action="product" align=right>
                  <input type="text" required placeholder="Cerca prodotti">
                  <button type="submit">Cerca</button>
            </form>
        </div><br>
      <div>
          <nav>
            <ul>
              <li><button><a href="Catalogo_Utente.jsp">Catalogo</a></button></li>
              <li><button><a href="Carrello.jsp">Carrello</a></button></li>
              <li><button><a href="Login.jsp">Login</a></button></li>
            </ul>
          </nav>
        </div>		
</body>
</html>