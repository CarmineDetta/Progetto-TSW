package control;

import java.io.IOException;
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
			System.out.println("Error:" + e.getMessage());
		}
		
		try {
		
			request.setAttribute("payments", model.doRetrieveByUtente(utente.getID_Utente()));
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Pagamenti_Utente.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
