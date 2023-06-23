package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

public class CatalogControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static ProdottoDAO model = new ProductModelDS();
	
    public CatalogControl() {
        super();
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action"); //Preleva l'azione

		try {
			if (action != null) {//Controlla se action è vuoto
				if (action.equalsIgnoreCase("read")) {

					String id = request.getParameter("id");//Prelevam ID
					request.removeAttribute("product");//Ruomove prodotto "vecchio"
					request.setAttribute("product", model.doRetrieveByKey(id));	//Inserisce il nuovo
				} 
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		String sort = request.getParameter("sort");
		
		try {
			
			request.setAttribute("products", model.doRetrieveAll(sort));
			
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Catalogo_Utente.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
