package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatheque.Mediatheque;

public class ServiceAjoutDone extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//out.println(request.getParameter("NomDoc") + " " + request.getParameter("AuteurDoc") + " " + request.getParameter("Type"));
		Mediatheque.getInstance().nouveauDocument(request.getParameter("NomDoc"), request.getParameter("AuteurDoc"), request.getParameter("Type"));
		out.println("Document ajouté </br>");
		out.println("<form method=\"post\" action=\"http://localhost:8080/Bibliotheque/Bibliothecaire\">"+ "<input class=\"btn btn-primary\"type= \"submit\" value= \"Retour\" />" + "</form>");
	}
	
}
