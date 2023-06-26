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

import model.ProdottoBean;
import model.ProductModelDS;


//Servlet per l'uso di AJAX nella ricerca dei prodotti
@WebServlet("/AjaxSuggestControl")
public class AjaxSuggestControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxSuggestControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String oggettoJSON = null;
        		
		ProductModelDS prodotti = new ProductModelDS();
		
		try {
			if(!request.getParameter("stringaRicerca").equalsIgnoreCase("")) {
				Collection<ProdottoBean> prodottiSuggest = prodotti.doRetrieveSuggest(request.getParameter("stringaRicerca"));
				Iterator<ProdottoBean> iter = prodottiSuggest.iterator();
				
				ProdottoBean prodotto = null;
				while(iter.hasNext()) {
					prodotto = iter.next();
				}
				
				oggettoJSON = new Gson().toJson(prodottiSuggest);
				
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
