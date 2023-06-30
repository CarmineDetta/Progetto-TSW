package control;

import java.io.IOException;


import java.sql.SQLException;

import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.UtenteBean;
import model.UtenteModelDS;


@WebServlet("/AjaxEmailControl")
public class AjaxEmailControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger LOGGER = Logger.getLogger(AjaxEmailControl.class.getName());


    public AjaxEmailControl() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String oggettoJSON = null;
        
        String stringaRicerca = request.getParameter("stringaRicerca");
		
		UtenteModelDS model = new UtenteModelDS();
		
		try {
			if(!stringaRicerca.equalsIgnoreCase("")) {
				
				UtenteBean utente = model.doRetriveByEmail(stringaRicerca);
				if(utente.getEmail().equalsIgnoreCase("")) {
					stringaRicerca = "true";
					oggettoJSON = new Gson().toJson(stringaRicerca);

					response.getWriter().write(oggettoJSON);
				} else {
					stringaRicerca = "false";
					oggettoJSON = new Gson().toJson(stringaRicerca);
					
					response.getWriter().write(oggettoJSON);
				}

			} else {
				oggettoJSON = new Gson().toJson("");
				response.getWriter().write(oggettoJSON);
			}
			
		} catch (SQLException | IOException e) {
			LOGGER.log(null, "contesto", e);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}