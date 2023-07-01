package control;

import java.io.IOException;

import java.sql.SQLException;

import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.ComposizioneModelDS;
import model.OrdineBean;
import model.OrdineDAO;
import model.OrdineModelDS;
import model.PortafoglioBean;
import model.PortafoglioDAO;
import model.PortafoglioModelDS;
import model.RecapitoBean;
import model.RecapitoDAO;
import model.RecapitoModelDS;
import model.UtenteBean;



public class AcquistoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(AcquistoControl.class.getName());

	static PortafoglioDAO modelportafoglio = new PortafoglioModelDS();
	static RecapitoDAO modelrecapito = new RecapitoModelDS();
	static OrdineDAO modelordine = new OrdineModelDS();
	static ComposizioneModelDS modelComposizione = new ComposizioneModelDS();
	
    public AcquistoControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UtenteBean utente = (UtenteBean) request.getSession().getAttribute("UtenteLoggato");
		String action = request.getParameter("action");
		
		try {
			if(action.equalsIgnoreCase("Completo")) {		//questa invece è la seconda fase in cui scegliamo le carte e l'indirizzo
				
				PortafoglioBean pagamento = modelportafoglio.doRetrieveByKey(Integer.parseInt(request.getParameter("Pagamento")));
				RecapitoBean recapito = (modelrecapito.doRetrieveByKey(Integer.parseInt(request.getParameter("Recapito"))));
				
				OrdineBean ordine = new OrdineBean();
				Cart carrello = (Cart) request.getSession().getAttribute("cart");
				ordine.setTotale(carrello.getTotale());

				modelordine.doSave(ordine, utente, recapito, pagamento);

				modelComposizione.doSaveAll(ordine, carrello);

				carrello = new Cart();
				request.getSession().setAttribute("cart", carrello);
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Acquisto_Completato.jsp");
				dispatcher.forward(request, response);				
			}
				if(action.equalsIgnoreCase("CheckOut")) {		//la prima fase dell'acquisto, quella in cui clicchiamo su "acquista"
							
				request.setAttribute("payments", modelportafoglio.doRetrieveByUtente(utente.getID_Utente()));
				request.setAttribute("recapiti", modelrecapito.doRetrieveByUtente(utente.getID_Utente()));
		
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Acquisto.jsp");
				dispatcher.forward(request, response);
			
			}
		} catch (SQLException e){
			 LOGGER.log(null, "contesto", e);	//fatto perchè lo chiede sonarcloud dicendo che devo controllare se il questo codice è disattivato quando consegno del condice da eseguire
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
