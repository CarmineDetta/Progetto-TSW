package control;

import java.io.IOException;
import java.sql.SQLException;

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
    
	static PortafoglioDAO modelportafoglio = new PortafoglioModelDS();
	static RecapitoDAO modelrecapito = new RecapitoModelDS();
	private OrdineBean ordine = new OrdineBean();
	static OrdineDAO modelordine = new OrdineModelDS();
	static ComposizioneModelDS modelComposizione = new ComposizioneModelDS();
	
    public AcquistoControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UtenteBean utente = (UtenteBean) request.getSession().getAttribute("UtenteLoggato");
		String action = request.getParameter("action");
		System.out.println(action);
		
		try {
			if(action.equalsIgnoreCase("Completo")) {
				
				PortafoglioBean pagamento = modelportafoglio.doRetrieveByKey(Integer.parseInt(request.getParameter("Pagamento")));
				RecapitoBean recapito = (modelrecapito.doRetrieveByKey(Integer.parseInt(request.getParameter("Recapito"))));
			
				Cart carrello = (Cart) request.getSession().getAttribute("cart");
				ordine.setTotale(carrello.getTotale());

				modelComposizione.doSaveAll(ordine, carrello);
				System.out.println("DO SAVE ALL Superata");

				modelordine.doSave(ordine, utente, recapito, pagamento);

				carrello = new Cart();
				request.setAttribute("cart", carrello);
				
				response.sendRedirect("/Acquisto_Completato.jsp");
				
			}
				if(action.equalsIgnoreCase("CheckOut")) {
							
				request.setAttribute("payments", modelportafoglio.doRetrieveByUtente(utente.getID_Utente()));
				request.setAttribute("recapiti", modelrecapito.doRetrieveByUtente(utente.getID_Utente()));
		
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Acquisto.jsp");
				dispatcher.forward(request, response);
			
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
