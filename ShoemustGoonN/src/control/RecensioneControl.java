package control;

import java.io.IOException;
import java.sql.SQLException;

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

@WebServlet("/RecensioneControl")
public class RecensioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static RecensioneDAO model = new RecensioneModelDS();
	static ProdottoDAO modelProd = new ProductModelDS();
    
	public RecensioneControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = (String) request.getAttribute("action");
		UtenteBean utente = (UtenteBean) request.getSession().getAttribute("UtenteLoggato");
		ProdottoBean p;
		
		if(action != null) {
			if(action.equalsIgnoreCase("Insert")){
				float votazione = Float.parseFloat(request.getParameter("Votazione"));
				String descrizione = request.getParameter("Descrizione");
				try {
					p = modelProd.doRetrieveByKey((String) request.getAttribute("idProd"));
					
					RecensioneBean recensione = new RecensioneBean();
					recensione.setDescrizione(descrizione);
					recensione.setVotazione(votazione);
					recensione.setUtente(utente);
					recensione.setProdotto(p);
					
					model.doSave(recensione, utente, p);
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Recensione_Completata.jsp");
					dispatcher.forward(request, response);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else {
			try {
				p = modelProd.doRetrieveByKey((String) request.getAttribute("idProd"));
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/InserimentoRecensione.jsp");
				dispatcher.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}