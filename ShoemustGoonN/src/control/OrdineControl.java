package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdineDAO;
import model.OrdineModelDS;
import model.UtenteBean;

public class OrdineControl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	 
	static OrdineDAO modelOrdine = new OrdineModelDS();
	
    public OrdineControl() {
        super();
    }
    
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UtenteBean utente = (UtenteBean) request.getSession().getAttribute("UtenteLoggato");
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			if(action != null) {
				if(action.equalsIgnoreCase("details")){
					int id = Integer.parseInt(request.getParameter("id"));
					request.setAttribute("Dettaglio_ordine", modelOrdine.doRetriveByKey(id));
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Dettagli_Ordine.jsp");
					dispatcher.forward(request, response);
				}
				if(action.equalsIgnoreCase("visualizza_tutti")) {
					request.setAttribute("all_orders", modelOrdine.doRetrieveAll());
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Ordini_Admin.jsp");
					dispatcher.forward(request, response);
				}
				if(action.equalsIgnoreCase("visualizza_cliente")){
					String id = request.getParameter("id");
					request.setAttribute("ordini_utente", modelOrdine.doRetrieveByUtente(id));
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Ordini_Admin_Cliente.jsp");
					dispatcher.forward(request, response);
				}
				if(action.equalsIgnoreCase("visualizza_data")){
					
					String dataInizio = request.getParameter("dataInizio");
					String dataFine = request.getParameter("dataFine");
					
					request.setAttribute("ordini_data", modelOrdine.doRetrieveByDate(dataInizio, dataFine));
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Ordine_Admin_Data.jsp");
					dispatcher.forward(request, response);
				}
				
				
			}else {
				try {	
					request.setAttribute("ordini", modelOrdine.doRetrieveByUtente(utente.getID_Utente()));
				} catch (SQLException e) {
						System.out.println("Error: " + e.getMessage());
				}
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Ordini_Utente.jsp");
				dispatcher.forward(request, response);
			}
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
