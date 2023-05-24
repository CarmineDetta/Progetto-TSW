package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdineDAO;
import model.OrdineModelDS;
import model.RecapitoBean;
import model.RecapitoDAO;
import model.RecapitoModelDS;
import model.UtenteBean;

@WebServlet("/ordine")
public class OrdineControl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	 
	static OrdineDAO modelOrdine = new OrdineModelDS();
	static RecapitoDAO modelRecapito = new RecapitoModelDS();
	
    public OrdineControl() {
        super();
    }
    
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UtenteBean utente = (UtenteBean) request.getSession().getAttribute("UtenteLoggato");
		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("selected_addres")) {
					
					int id = Integer.parseInt(request.getParameter("indirizzo"));
					RecapitoBean indirizzo = modelRecapito.doRetrieveByKey(id);
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Acquisto.jsp");
					dispatcher.forward(request, response);
					
					
				}
			}
					
			request.setAttribute("ordini", modelOrdine.doRetrieveByUtente(utente.getID_Utente()));
			request.setAttribute("recapiti", modelRecapito.doRetrieveByUtente(utente.getID_Utente()));
					
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Acquisto.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
