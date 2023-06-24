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



/**
 * Servlet implementation class AjaxEmailControl
 */
@WebServlet("/AjaxEmailControl")
public class AjaxEmailControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxEmailControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        String oggettoJSON = null;
        
        String stringaRicerca = request.getParameter("stringaRicerca");
		System.out.println(stringaRicerca);
		
		UtenteModelDS Utente = new UtenteModelDS();
		
		try {
			if(!stringaRicerca.equalsIgnoreCase("")) {
				
				UtenteBean UtenteSuggest = Utente.doRetriveByEmail(stringaRicerca);
				if(UtenteSuggest.getEmail() == "") {
					stringaRicerca = "true";
					oggettoJSON = new Gson().toJson(stringaRicerca);
					System.out.println("Oggetto JSON: "+oggettoJSON);
					response.getWriter().write(oggettoJSON.toString());
				} else {
					stringaRicerca = "false";
					oggettoJSON = new Gson().toJson(stringaRicerca);
					System.out.println("Oggetto JSON: "+oggettoJSON);
					response.getWriter().write(oggettoJSON.toString());
				}

			} else {
				oggettoJSON = new Gson().toJson("");
				response.getWriter().write(oggettoJSON.toString());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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