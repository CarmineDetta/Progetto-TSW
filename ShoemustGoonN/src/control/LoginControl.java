package control;

import java.io.IOException;
import model.UtenteBean;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginControl() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UtenteBean utente = new UtenteBean();
		UtenteModelDS u_ds = new UtenteModelDS();
		 
		String action = request.getParameter("action");
	
		try {
			
			if(action != null) {
				
				if(action.equalsIgnoreCase("login")){
			
					utente = u_ds.doRetriveByEmail(email);
					
					if(utente.getEmail().equalsIgnoreCase("") || utente.getEmail() == null) {
						response.sendRedirect("Login.jsp");
					}else {
					
						if(utente != null) {
						
							if(utente.getEmail().equalsIgnoreCase(email)) {
								if(utente.getPassword().equalsIgnoreCase(password)) {
									
									if(utente.getTipo().equalsIgnoreCase("utente")) {
										request.getSession().setAttribute("UtenteLoggato" , utente);       //Per motivi di sicurezza 
										response.sendRedirect("./Catalogo_Utente.jsp");
										
									}
									
									if(utente.getTipo().equalsIgnoreCase("admin")) {
										request.getSession().setAttribute("AdminLoggato", utente );       //Per motivi di sicurezza
										response.sendRedirect("./Catalogo_Admin.jsp");
									}
								}else{
									response.sendRedirect("./Login.jsp");
								}
							}
							else {
								response.sendRedirect("./Login.jsp");
							}
							
						}
					}
				}else if(action.equalsIgnoreCase("register")) {
					
					String nome = request.getParameter("nome");
					String cognome = request.getParameter("cognome");
					String datanascita = request.getParameter("nascita");
					String cf = request.getParameter("cf");
					String mail = request.getParameter("email");	/*mail perhcèé sopra esistegià email*/
					String pass = request.getParameter("psw");
					String tipo = request.getParameter("tipo");
					
					
					UtenteBean u = new UtenteBean();

					u.setNome(nome);
					u.setCognome(cognome);
					u.setDataNascita(datanascita);
					u.setCF(cf);
					u.setEmail(mail);
					u.setPassword(pass);
					u.setTipo(tipo);
					
					u_ds.doSave(u);
					
					response.sendRedirect("Login.jsp");
				}
			}			
		}catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("Login.jsp");
		}
	}
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
	
