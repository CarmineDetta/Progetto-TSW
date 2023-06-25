package control;

import java.io.IOException;

import java.util.logging.Logger;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ProdottoDAO;
import model.ProductModelDS;
import model.RecensioneDAO;
import model.RecensioneModelDS;

public class ProdottoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final Logger LOGGER = Logger.getLogger(ProdottoControl.class.getName());

	
	static ProdottoDAO model = new ProductModelDS();
	static RecensioneDAO modelRec = new RecensioneModelDS();
	
    public ProdottoControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		try {
			if(action != null) {
				if (action.equalsIgnoreCase("read")) {
						String id = request.getParameter("id");
						request.setAttribute("product", model.doRetrieveByKey(id));
						request.setAttribute("recensioni", modelRec.doRetrieveByProdotto(id));

				}
			}
		} catch (SQLException e) {
			LOGGER.log(null, "contesto", e);	//fatto perchè lo chiede sonarcloud dicendo che devo controllare se il questo codice è disattivato quando consegno del condice da eseguire
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Dettaglio_Prodotto.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
