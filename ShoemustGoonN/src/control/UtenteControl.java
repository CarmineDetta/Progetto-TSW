package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UtenteBean;
import model.UtenteDAO;
import model.UtenteModelDS;

public class UtenteControl extends HttpServlet{

	private static final long serialVersionUID = 1L;

	static UtenteDAO model = new UtenteModelDS();
	
    public UtenteControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action = request.getParameter("action");
		System.out.println(action);
		
		if (action != null) {
			if (action.equalsIgnoreCase("update")) { 
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Modifica_Dati_Utente.jsp");
				dispatcher.forward(request, response);
			
			}else if(action.equalsIgnoreCase("completa_update")) {
				
					UtenteBean u = (UtenteBean) request.getSession().getAttribute("UtenteLoggato");
								
					String email = u.getEmail();
					
					String scelta =  request.getParameter("Scelta");
					String valore =  request.getParameter("valore");
					String utente =  request.getParameter("utente");
						
				try {
			
					request.setAttribute("UtenteLoggato", model.doRetriveByEmail(email));
						
					if(scelta.equalsIgnoreCase("password")) {
							
						model.doUpdatePassword(valore, utente);
					
					}else {
							model.doUpdateEmail(valore, utente);
					}
						
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Dati_Utente.jsp");
				dispatcher.forward(request, response);
			}
			if(action.equalsIgnoreCase("visualizza_tutti")) {
				try {
					request.setAttribute("all_utents", model.doRetrieveAllUtente());
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Visualizza_utenti.jsp");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Dati_Utente.jsp");
			dispatcher.forward(request, response);
			
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	doGet(request, response);
	}
}
