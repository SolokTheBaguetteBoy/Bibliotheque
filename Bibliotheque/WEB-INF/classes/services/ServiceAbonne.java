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

public class ServiceAbonne extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

			out.println("<form method=\"POST\" action=\"http://localhost:8080/Bibliotheque/AbonneEmprunt\"><input type=\"submit\" name=\"Emprunt\" value=\"Emprunt\"></form>");
			out.println("<form method=\"POST\" action=\"http://localhost:8080/Bibliotheque/AbonneRetour\"><input type=\"submit\" name=\"Retour\" value=\"Retour\"></form>");
			
	}
	
	@Override
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		doGet(arg0, arg1);
	}
}
