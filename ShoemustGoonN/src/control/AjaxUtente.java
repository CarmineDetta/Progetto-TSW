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


//Servlet per l'uso di AJAX nella ricerca degli utenti
@WebServlet("/AjaxUtente")
public class AjaxUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AjaxUtente() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String oggettoJSON = null;
        
		UtenteModelDS utente = new UtenteModelDS();
		
		try {
			if(!request.getParameter("stringaRicerca").equalsIgnoreCase("")) {
				Collection<UtenteBean> utenteSuggest = utente.doRetrieveSuggest(request.getParameter("stringaRicerca"));
				
				Iterator<UtenteBean> iter = utenteSuggest.iterator();
				
				oggettoJSON = new Gson().toJson(utenteSuggest);
				
				response.getWriter().write(oggettoJSON);
			} else {
				oggettoJSON = new Gson().toJson("");
				response.getWriter().write(oggettoJSON);
			}
			
		} catch (SQLException | IOException e) {
		    e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
