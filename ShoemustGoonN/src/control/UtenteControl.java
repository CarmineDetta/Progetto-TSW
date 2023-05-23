package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		if (action != null) {
			if (action.equalsIgnoreCase("update")) { 
				String email = (String) request.getParameter("email");
				try {
					
					request.setAttribute("a", model.doRetriveByEmail(email));
					
					String scelta =  request.getParameter("scelta");
					String valore =  request.getParameter("valore");
					String utente =  request.getParameter("utente");
					
					System.out.println(scelta);
					System.out.println(valore);
					System.out.println(utente);
					
					model.doUpdateUtente(valore, scelta, utente);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
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
