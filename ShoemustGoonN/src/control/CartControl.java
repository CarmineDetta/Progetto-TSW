package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

public class CartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProductModelDS model = new ProductModelDS();
	
    public CartControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione = request.getSession();
		
		Cart cart = (Cart) sessione.getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			sessione.setAttribute("cart", cart);
		}
		
		String action = request.getParameter("action");
		try {
			if (action != null) {
				if (action.equalsIgnoreCase("addCart")) {
					String id = request.getParameter("id");
					int qty = Integer.parseInt(request.getParameter("qty"));
					
					ItemCarrello p = new ItemCarrello(id, qty);
										
					cart.addProduct(p);	

				} else if (action.equalsIgnoreCase("deleteCart")) {
					
					String id = request.getParameter("id");
					int qty = Integer.parseInt(request.getParameter("qty"));
					
					ItemCarrello p = new ItemCarrello(id, qty);
										
					cart.deleteProduct(p);
					if(cart == null) {
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Carrello.jsp");
						dispatcher.forward(request, response);
					}
				} else if (action.equalsIgnoreCase("read")) {
					String id = request.getParameter("id");
					request.setAttribute("product", model.doRetrieveByKey(id));
				}
			}			
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		sessione.setAttribute("cart", cart);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Carrello.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
