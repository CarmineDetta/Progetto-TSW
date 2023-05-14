package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PortafoglioDAO;
import model.PortafoglioModelDS;
import model.ProdottoBean;
import model.ProdottoDAO;
import model.ProductModelDS;
import model.UtenteBean;

@WebServlet("/utente")
public class UtenteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	static PortafoglioDAO model = new PortafoglioModelDS();

    public UtenteControl() {
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
			}
		}catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		try {
		
			if(utente != null)
			//request.getSession().setAttribute("payments", model.doRetrieveByUtente(utente.getID_Utente()));
			request.setAttribute("payments", model.doRetrieveByUtente(utente.getID_Utente()));

			
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Utente.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
