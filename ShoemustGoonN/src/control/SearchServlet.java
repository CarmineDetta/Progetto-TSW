package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;


@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("cerca");
		ProductModelDS model = new ProductModelDS();	
		ProdottoBean prod = new ProdottoBean();
		
		request.getSession().removeAttribute("prodottoDettagli");
		
		try {
			
			prod = model.doRetrieveByKey(id);
		
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
			
		request.getSession().setAttribute("prodottoDettagli", prod);
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Dettaglio_Prodotto.jsp");
		dispatcher.forward(request, response);
	
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
