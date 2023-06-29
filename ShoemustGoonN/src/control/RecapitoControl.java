package control;

import java.io.IOException;
import java.util.logging.Logger;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RecapitoBean;
import model.RecapitoDAO;
import model.RecapitoModelDS;
import model.UtenteBean;

public class RecapitoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(RecapitoControl.class.getName());

	static RecapitoDAO model = new RecapitoModelDS();

    public RecapitoControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UtenteBean utente = (UtenteBean) request.getSession().getAttribute("UtenteLoggato");
		String action = request.getParameter("action");
		
		try {
			if (action != null) {
				if (action.equalsIgnoreCase("delete")) {
					int id = Integer.parseInt(request.getParameter("id"));
					model.doDelete(id);
				}
				else if (action.equalsIgnoreCase("insert")) {

					String citta = request.getParameter("citta");			
					String via = request.getParameter("via_piazza");
					int cap = Integer.parseInt(request.getParameter("cap"));
					int n = Integer.parseInt(request.getParameter("n_civico"));
					
					RecapitoBean r = new RecapitoBean();
					r.setCitta(citta);
					r.setViaPiazza(via);
					r.setcap(cap);
					r.setNCivico(n);
					model.doSave(r, utente);				
				}
			}
		}catch (SQLException e) {
			LOGGER.log(null, "contesto", e);	//fatto perchè lo chiede sonarcloud dicendo che devo controllare se il questo codice è disattivato quando consegno del condice da eseguire
		}
		
		try {
		
			request.setAttribute("recapiti", model.doRetrieveByUtente(utente.getID_Utente()));
			
		} catch (SQLException e) {
			LOGGER.log(null, "contesto", e);	//fatto perchè lo chiede sonarcloud dicendo che devo controllare se il questo codice è disattivato quando consegno del condice da eseguire
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Indirizzi_Utente.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
