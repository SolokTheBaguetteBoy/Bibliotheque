package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatheque.EmpruntException;
import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

public class ServiceRetourDone extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<form method=\"POST\" action=\"http://localhost:8080/Bibliotheque/AbonneSelect\"><input type=\"submit\" name=\"Retour menu Abonne\" value=\"Retour menu Abonne\"></form>");
		out.println("Document rendu : " + request.getParameter("idDoc"));
		//out.println(Integer.parseInt(request.getParameter("idDoc")));
		Utilisateur a = (Utilisateur) request.getSession().getAttribute("utilisateur");
		int in = Integer.parseInt(request.getParameter("idDoc"));
		out.println(" par " + a.getName());
		//out.println(Mediatheque.getInstance().getDocument(in).getId());
		try {
			Mediatheque.getInstance().retour(Mediatheque.getInstance().getDocument(in));
		
		} catch (NumberFormatException | EmpruntException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
