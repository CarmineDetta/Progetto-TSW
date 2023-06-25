package control;

import java.io.IOException;

import java.util.logging.Logger;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

public class CatalogControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private static final Logger LOGGER = Logger.getLogger(CatalogControl.class.getName());

	static ProdottoDAO model = new ProductModelDS();
	
    public CatalogControl() {
        super();
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action"); //Preleva l'azione

		try {
			if (action != null) {//Controlla se action Ã¨ vuoto
				if (action.equalsIgnoreCase("read")) {

					String id = request.getParameter("id");//Prelevam ID
					request.removeAttribute("product");//Ruomove prodotto "vecchio"
					request.setAttribute("product", model.doRetrieveByKey(id));	//Inserisce il nuovo
				} 
			}
		} catch (SQLException e) {
			LOGGER.log(null, "contesto", e);	//fatto perchè lo chiede sonarcloud dicendo che devo controllare se il questo codice è disattivato quando consegno del condice da eseguire
		}

		String sort = request.getParameter("sort");
		
		try {
			
			request.setAttribute("products", model.doRetrieveAll(sort));
			
		} catch (SQLException e) {
			LOGGER.log(null, "contesto", e);	//fatto perchè lo chiede sonarcloud dicendo che devo controllare se il questo codice è disattivato quando consegno del condice da eseguire
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Catalogo_Utente.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
