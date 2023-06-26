package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.UtenteBean;
import model.UtenteModelDS;


//Servlet per l'uso di AJAX nella ricerca dei prodotti
@WebServlet("/AjaxUtente")
public class AjaxUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AjaxUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String oggettoJSON = null;
        
		UtenteModelDS Utente = new UtenteModelDS();
		
		try {
			if(!request.getParameter("stringaRicerca").equalsIgnoreCase("")) {
				Collection<UtenteBean> UtenteSuggest = Utente.doRetrieveSuggest(request.getParameter("stringaRicerca"));
				Iterator<UtenteBean> iter = UtenteSuggest.iterator();
				
				UtenteBean utente = null;
				while(iter.hasNext()) {
					utente = iter.next();
				}
				
				oggettoJSON = new Gson().toJson(UtenteSuggest);
				
				response.getWriter().write(oggettoJSON.toString());
			} else {
				oggettoJSON = new Gson().toJson("");
				response.getWriter().write(oggettoJSON.toString());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
