package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.*;


@WebServlet("/SearchAjaxServlet")
public class SearchAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SearchAjax() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nome = request.getParameter("cerca");
		
		System.out.println(nome);
		ProductModelDS prod = new ProductModelDS();
		ArrayList<ProdottoBean> a = new ArrayList<ProdottoBean>();

		try {
			a = prod.RicercaNomeProdotto(nome);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(convertListToJson(a));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

    private String convertListToJson(ArrayList<ProdottoBean> list) {

        String s = new Gson().toJson(list);

        return s;

    	}
  
}
