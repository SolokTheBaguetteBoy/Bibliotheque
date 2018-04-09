package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

public class ServiceConnexion extends HttpServlet {

	private String login;
	private String password;
	
    @Override
    public void init(ServletConfig SC) throws ServletException {
        super.init(SC);
        
        String nameForClassForName = SC.getInitParameter("ConnexionDB");
        try{
            Class.forName("persistantdata." + nameForClassForName);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
	
	@SuppressWarnings("unused")
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher;
		
		
		Mediatheque mediatheque = Mediatheque.getInstance();
		this.login = request.getParameter("login");
		this.password = request.getParameter("password");
		
		
		Utilisateur user = mediatheque.getUser(login, password);
		
		if(user == null){
			
			// login ou mdp incorrect
			dispatcher = request.getRequestDispatcher("index.html");
			String mesErr = "Login ou password incorrect";
		}else {
			// création de la session utilisateur
			HttpSession session = request.getSession(true);
			session.setAttribute("utilisateur", user);
			if(user.getType().equals("Abonne")){
				out.print("<p>AH</p>");
				response.sendRedirect("AbonneSelect");
			}
			if(user.getType().equals("Bibliothecaire")){
				out.print("<p>OH</p>");
				response.sendRedirect("http://localhost:8080/Bibliotheque/Bibliothecaire");
			}
		}
		
    }
	
}
