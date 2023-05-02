package control;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ListaProdottiBean;
import model.ProdottoBean;
import model.ProdottoDAO;

@WebServlet("/cartControl")
public class cartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static ProdottoDAO prodotto;
	static ListaProdottiBean cart;
	
	static{
		if(cart.isIs_Carrello() != true) {
			
			//questa cosa è da vedere (tipo possiamo riportarlo ad un bottone con aggiungi al carrello)
			cart.setIs_Carrello(true);
		}
	}

    public cartControl() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ProdottoDAO prodotto = new ProdottoDAO();
		
		System.out.println("La richiesta per la pagina del carrello e' stata ricevuta");
		

			ListaProdottiBean cart = (ListaProdottiBean)request.getSession().getAttribute("cart");
			if(cart == null) {
				cart = new ListaProdottiBean();
				request.getSession().setAttribute("cart", cart);
			}			
		
			String action = request.getParameter("action");
		
			try {
				if (action != null) {
					
					if (action.equalsIgnoreCase("addC")) {
						int id = Integer.parseInt(request.getParameter("id"));
						cart.addProduct(prodotto.doRetrieveByKey(Integer.toString(id)));
					}
					else if (action.equalsIgnoreCase("deleteC")) {
						int id = Integer.parseInt(request.getParameter("id"));
						cart.deleteProduct(prodotto.doRetrieveByKey(Integer.toString(id)));
					}
					else if (action.equalsIgnoreCase("read")) {
						int id = Integer.parseInt(request.getParameter("id"));
						request.removeAttribute("product");
						request.setAttribute("product", prodotto.doRetrieveByKey(Integer.toString(id)));
					}
					else if (action.equalsIgnoreCase("delete")) {
						int id = Integer.parseInt(request.getParameter("id"));
						prodotto.doDelete(Integer.toString(id));
					}
					else if (action.equalsIgnoreCase("insert")) {
						String name = request.getParameter("name");
						String description = request.getParameter("description");
						double price = Integer.parseInt(request.getParameter("price"));
						int quantity = Integer.parseInt(request.getParameter("quantity"));

						ProdottoBean bean = new ProdottoBean();
						bean.setNome(name);
						bean.setDescrizione(description);
						bean.setPrezzo(price);
						bean.setQuantita(quantity);
						prodotto.doSave(bean);
					}
				}
			
			request.getSession().setAttribute("cart", cart);
			request.setAttribute("cart", cart);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/paginaCarrello.jsp");
			dispatcher.forward(request, response);	
			
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}

			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
