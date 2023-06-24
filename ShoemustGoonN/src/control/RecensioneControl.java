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
		
		UtenteBean utente = (UtenteBean) request.getSession().getAttribute("UtenteLoggato");
		
		if(utente == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request, response);
		}else {
			
			String action = (String) request.getParameter("action");
		
		try {
		/*
			String c = request.getParameter("idProd");
			System.out.println("ID Prodotto:" + c);
		*/
			
			ProdottoBean p = modelProd.doRetrieveByKey((String) request.getParameter("idProd"));
			request.setAttribute("idProd", p.getID_Prodotto());

			//System.out.println("ID Prodotto aaaaa: " + p.getID_Prodotto());
			
			if(action != null) {
				if(action.equalsIgnoreCase("Insert")){
					
					String c = request.getParameter("idProd");
					System.out.println("ID Prodotto:" + c);
					
					
					System.out.println("ID Prodotto 22222: " + p.getID_Prodotto());
					
					float votazione = Float.parseFloat(request.getParameter("Valutazione"));
					String descrizione = request.getParameter("descrizione");
					
					
					try {
						
						System.out.println(utente.getNome());
						System.out.println(p.getID_Prodotto());
					
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
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/InserimentoRecensione.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
