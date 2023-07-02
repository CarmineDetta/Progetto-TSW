package control;

import java.io.IOException;

import java.util.logging.Logger;
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

import model.ProdottoBean;
import model.ProductModelDS;


//Servlet per l'uso di AJAX nella ricerca dei prodotti
@WebServlet("/AjaxSuggestControl")
public class AjaxSuggestControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(AjaxSuggestControl.class.getName());
   

    public AjaxSuggestControl() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String stringaRicerca = request.getParameter("stringaRicerca");        
        
        String oggettoJSON = null;
        		
		ProductModelDS prodotti = new ProductModelDS();
		
		try {
			if(!request.getParameter("stringaRicerca").equalsIgnoreCase("")) {
				Collection<ProdottoBean> prodottiSuggest = prodotti.doRetrieveSuggest(request.getParameter("stringaRicerca"));
	
				oggettoJSON = new Gson().toJson(prodottiSuggest);
				response.getWriter().write(oggettoJSON);
			} else {
				oggettoJSON = new Gson().toJson("");
				response.getWriter().write(oggettoJSON);
			}
			
		}catch (SQLException | IOException e) {
			LOGGER.log(null, "contesto", e);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
