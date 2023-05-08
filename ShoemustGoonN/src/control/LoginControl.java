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
			
					utente = u_ds.doRetrieveByEmail(email);
					
					if(utente.getEmail().equalsIgnoreCase("") || utente.getEmail() == null) {
						response.sendRedirect("Login.jsp");
					}else {
					
						if(utente != null) {
						
							if(utente.getEmail().equalsIgnoreCase(email)) {
								if(utente.getPassword().equalsIgnoreCase(password)) {
									
									if(utente.getTipo().equalsIgnoreCase("utente")) {
										request.getSession().setAttribute("Utente loggato" , utente );       //Per motivi di sicurezza 
										response.sendRedirect("./Catalogo_Utente.jsp");
									}
									
									if(utente.getTipo().equalsIgnoreCase("admin")) {
										request.getSession().setAttribute("Utente loggato" , utente );       //Per motivi di sicurezza
										response.sendRedirect("./Catalogo_Admin.jsp");
									}
								}else{
									response.getWriter().append("Password non corretta");
								}
							}
							else {
								response.getWriter().append("Utente non registrato");
							}
							
						}
					}
				}else if(action.equalsIgnoreCase("register")) {
					
					String Nome = request.getParameter("nome");
					String Cognome = request.getParameter("cognome");
					String DataNascita = request.getParameter("nascita");
					String CF = request.getParameter("cf");
					String Email = request.getParameter("email");
					String Password = request.getParameter("psw");
					String Tipo = request.getParameter("tipo");
					
					
					UtenteBean u = new UtenteBean();

					u.setNome(Nome);
					u.setCognome(Cognome);
					u.setDataNascita(DataNascita);
					u.setCF(CF);
					u.setEmail(Email);
					u.setPassword(Password);
					u.setTipo(Tipo);
					
					
					System.out.println("Nome:"+u.getNome()+"\n");
					System.out.println("Cognome:"+u.getCognome()+"\n");
					System.out.println("DataNascita:"+u.getDataNascita()+"\n");
					System.out.println("CF:"+u.getCF()+"\n");
					System.out.println("Email:"+u.getEmail()+"\n");
					System.out.println("Password:"+u.getPassword()+"\n");
					System.out.println("Tipo:"+u.getTipo()+"\n");
					
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
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
	
