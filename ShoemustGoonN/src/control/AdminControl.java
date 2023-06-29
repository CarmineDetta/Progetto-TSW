package control;

import java.io.IOException;

import java.util.logging.Logger;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ProdottoBean;
import model.ProdottoDAO;
import model.ProductModelDS;

public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final Logger LOGGER = Logger.getLogger(AdminControl.class.getName());

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
			LOGGER.log(null, "contesto", e);	//fatto perchè lo chiede sonarcloud dicendo che devo controllare se il questo codice è disattivato quando consegno del condice da eseguire
		}

		String sort = request.getParameter("sort");
		
		try {
			request.setAttribute("products", model.doRetrieveAll(sort));
		} catch (SQLException e) {
			LOGGER.log(null, "contesto", e);	//fatto perchè lo chiede sonarcloud dicendo che devo controllare se il questo codice è disattivato quando consegno del condice da eseguire
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Catalogo_Admin.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
