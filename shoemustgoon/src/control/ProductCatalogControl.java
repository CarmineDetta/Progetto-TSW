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

//gestisce la pagina del catalogo, riportando alla pagina del prodotto specifico
@WebServlet("/ProductCatalogControl")
public class ProductCatalogControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductCatalogControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//prelevo l'id 
		String id = request.getParameter("id");
		//creo il prodotto
		ProdottoDAO prodotto = new ProdottoDAO();
		
		try {//e gli assegno i valori
			ProdottoBean oggetto = prodotto.doRetrieveByKey(id);
			request.setAttribute("id", oggetto);
		
			//mando la richiesta alla pagina jsp che mi produrra la risposta che voglio
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/paginaProdotto.jsp");
			dispatcher.forward(request, response);
		
			//nel caso in cui ci fossero degli errori
		}catch (SQLException e) {
				System.out.println("Prodotto non trovato nel DB");
				//Sarebbe l'errore 404
				//errori dovuti al db allora mi rimanda alla pagina che voglio (DA CAMBIARE, L'HO MESSA PER PROVARE)
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/prova.jsp");
				dispatcher.forward(request, response);
				
			} catch (IOException e) {
				System.out.println("Errore di IO");
				e.printStackTrace();
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
