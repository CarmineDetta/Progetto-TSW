package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Cart;
import model.ItemCarrello;

/**
 * Servlet implementation class AjaxCart
 */
@WebServlet("/AjaxCart")
public class AjaxCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione = request.getSession();
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        String oggettoJSON = null;
        String stringaRicerca =  request.getParameter("stringaRicerca");
        
		System.out.println("Stringa Ricerca:" + request.getParameter("stringaRicerca"));
		
		Cart cart = (Cart) sessione.getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			sessione.setAttribute("cart", cart);
		}
		
		String action = request.getParameter("action");
		try {
			if(!request.getParameter("stringaRicerca").equalsIgnoreCase("")) {
				
				int qty = Integer.parseInt(request.getParameter("qty"));
				ItemCarrello p = new ItemCarrello(stringaRicerca, qty);
									
				cart.addProduct(p);	

				oggettoJSON = new Gson().toJson(cart);
				System.out.println("Oggetto JSON: "+oggettoJSON);
				
				response.getWriter().write(oggettoJSON.toString());
			 }
			
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
