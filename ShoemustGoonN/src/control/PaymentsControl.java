package control;

import java.io.IOException;
import java.util.logging.Logger;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PortafoglioBean;
import model.PortafoglioDAO;
import model.PortafoglioModelDS;
import model.UtenteBean;

public class PaymentsControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(PaymentsControl.class.getName());

	static PortafoglioDAO model = new PortafoglioModelDS();

    public PaymentsControl() {
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

					String num_carta = request.getParameter("n_carta");			
					String nome = request.getParameter("nome_intestatario");
					String scadenza = request.getParameter("scadenza");
					int cvv = Integer.parseInt(request.getParameter("cvv"));
					
					PortafoglioBean pagamento = new PortafoglioBean();
					pagamento.setN_carta(num_carta);
					pagamento.setNome_Intestatario(nome);
					pagamento.setScadenza(scadenza);
					pagamento.setCvv(cvv);
					model.doSave(pagamento, utente);				
				}
			}
		}catch (SQLException e) {
			LOGGER.log(null, "contesto", e);	//fatto perchè lo chiede sonarcloud dicendo che devo controllare se il questo codice è disattivato quando consegno del condice da eseguire
		}
		
		try {
		
			request.setAttribute("payments", model.doRetrieveByUtente(utente.getID_Utente()));
			
		} catch (SQLException e) {
			LOGGER.log(null, "contesto", e);	//fatto perchè lo chiede sonarcloud dicendo che devo controllare se il questo codice è disattivato quando consegno del condice da eseguire
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Pagamenti_Utente.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
