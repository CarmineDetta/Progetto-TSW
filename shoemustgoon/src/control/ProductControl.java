package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoriaDAO;
import model.ProdottoDAO;

//servlet che prende i prodotti dal catalogo e li apre in una pagina
@WebServlet("/ProductControl")
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static ProdottoDAO prodotto = new ProdottoDAO();	//prendo il prodotto dal DB
	static CategoriaDAO categoria = new CategoriaDAO();	//stessa cosa con categoria
    
	public ProductControl() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");	//prelevo l'id del prodotto (su cui clicco)
		
		try {
			if (id != null && !id.isEmpty()) {	//controllo se è vuoto o non esiste
				request.removeAttribute("product");	//rimuovo i valori in "product" che c'erano prima
				request.setAttribute("product", prodotto.doRetrieveByKey(id));	//lo aggiorno con l'id prelevato
			}
		} catch (SQLException e) {	//genero questo errore se non riesce a far accesso al DB
			System.out.println("Error products:" + e.getMessage());
		}
			
		try {	//faccio le stesse cose con categoria
			request.removeAttribute("categories");
			request.setAttribute("categories", categoria.doRetrieveAll(id));
		}catch (SQLException e) {
			System.out.println("Error categories:" + e.getMessage());
		}
		
		if(request.getParameter("categoria") != null) {
			request.removeAttribute("products");
			try {
				request.setAttribute("products", prodotto.doRetrieveByCategory(request.getParameter("categoria")) );
			} catch (SQLException e) {				
				e.printStackTrace();
			
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		
		//accedo alla pagina del catalogo jsp
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/paginaCatalogo.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
