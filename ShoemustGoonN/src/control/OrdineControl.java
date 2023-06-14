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
	
    public OrdineControl() {
        super();
    }
    
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UtenteBean utente = (UtenteBean) request.getSession().getAttribute("UtenteLoggato");
		try {
				request.setAttribute("ordini", modelOrdine.doRetrieveByUtente(utente.getID_Utente()));
			
				
		} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Ordini_Utente.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
