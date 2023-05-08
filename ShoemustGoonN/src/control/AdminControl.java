package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProdottoBean;
import model.ProdottoDAO;
import model.ProductModelDS;

public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	static ProdottoDAO model = new ProductModelDS();
	
    public AdminControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("read")) {
					String id = request.getParameter("id");
					request.removeAttribute("product");
					request.setAttribute("product", model.doRetrieveByKey(id));
				} else if (action.equalsIgnoreCase("delete")) {
					String id = request.getParameter("id");
					model.doDelete(id);
				} else if (action.equalsIgnoreCase("insert")) {

					String marca = request.getParameter("marca");
					String modello = request.getParameter("modello");
					String colore = request.getParameter("colore");
					double prezzo = Double.parseDouble(request.getParameter("prezzo"));
					int quantita = Integer.parseInt(request.getParameter("quantita"));
					boolean disp = Boolean.parseBoolean(request.getParameter("disp"));
					String descrizione = request.getParameter("descrizione");
					String categoria = request.getParameter("categoria");

					ProdottoBean bean = new ProdottoBean();
					bean.setMarca(marca);
					bean.setModello(modello);
					bean.setColore(colore);
					bean.setPrezzo(prezzo);
					bean.setQuantita(quantita);
					bean.setDisponibilita(disp);
					bean.setDescrizione(descrizione);
					bean.setCategoria(categoria);
					model.doSave(bean);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		String sort = request.getParameter("sort");
		
		try {
			request.removeAttribute("products");
			request.setAttribute("products", model.doRetrieveAll(sort));
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Catalogo_Admin.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
