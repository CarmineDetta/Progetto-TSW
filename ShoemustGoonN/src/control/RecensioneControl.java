package control;

import java.io.IOException;

import java.sql.SQLException;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProdottoBean;
import model.ProdottoDAO;
import model.ProductModelDS;
import model.RecensioneBean;
import model.RecensioneDAO;
import model.RecensioneModelDS;
import model.UtenteBean;

public class RecensioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(RecensioneControl.class.getName());

	static RecensioneDAO model = new RecensioneModelDS();
	static ProdottoDAO modelProd = new ProductModelDS();
    
	public RecensioneControl() {
        super();
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				

			String action = (String) request.getParameter("action");
			UtenteBean utente = (UtenteBean) request.getSession().getAttribute(("UtenteLoggato"));

		try {
			if(action != null) {
				if(action.equalsIgnoreCase("Insert")){
					
					float votazione = Float.parseFloat(request.getParameter("Valutazione"));
					String descrizione = request.getParameter("descrizione");
					ProdottoBean p = modelProd.doRetrieveByKey((String) request.getParameter("idProd"));
					
					try {
					
						RecensioneBean recensione = new RecensioneBean();
						
						recensione.setDescrizione(descrizione);
						recensione.setVotazione(votazione);
						recensione.setUtente(utente);
						recensione.setProdotto(p);
				
						model.doSave(recensione, utente, p);
					
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Recensione_Completata.jsp");
						dispatcher.forward(request, response);
					
					} catch (SQLException e) {
						LOGGER.log(null, "contesto", e);
					}
				}
			}
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/InserimentoRecensione.jsp");
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			LOGGER.log(null, "contesto", e);
		}

	}
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
