package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.Cart;
import model.ItemCarrello;

/**
 * Servlet implementation class AjaxAddRemove
 */
@WebServlet("/AjaxAddRemove")
public class AjaxAddRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(AjaxAddRemove.class.getName());

    public AjaxAddRemove() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione = request.getSession();
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        
        String stringaRicerca =  request.getParameter("stringaRicerca");
        String button =  request.getParameter("button");
        		
		Cart cart = (Cart) sessione.getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			sessione.setAttribute("cart", cart);
		}
		
		try {
			  JsonObject responseObject = new JsonObject();
			  double d = cart.getTotale();

			  if (button.equalsIgnoreCase("add")) {
			    int qty2 = Integer.parseInt(request.getParameter("qty"));
			    ItemCarrello p = new ItemCarrello(stringaRicerca, qty2);

			    cart.addProduct(p);
			  } else if (button.equalsIgnoreCase("remove")) {
				 int qty2 = Integer.parseInt(request.getParameter("qty"));
				 ItemCarrello p = new ItemCarrello(stringaRicerca, qty2);

			    cart.deleteProduct(p);
			  }

			  responseObject.add("cart", new Gson().toJsonTree(cart));
			  responseObject.addProperty("d", d);

			  out.write(responseObject.toString());
			} catch (SQLException e) {
				LOGGER.log(null, "contesto", e);
			}


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
