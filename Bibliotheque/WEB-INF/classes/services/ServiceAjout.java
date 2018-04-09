package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatheque.Document;
import mediatheque.IService;
import mediatheque.Mediatheque;

public class ServiceAjout extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<form method=\"get\" action=\"http://localhost:8080/Bibliotheque/BibliothecaireDone\"><p><label for=\"Nom\">Nom du Document :</label><input type=\"text\" name=\"NomDoc\"/></br>" +
       "<label for=\"Auteur\">Auteur Document :</label>" +
	   "<input type=\"text\" name=\"AuteurDoc\"/></br>" + 
	   "<label for=\"Type\">Auteur Document :</label>" +
	   "<select name=\"Type\">" +
           "<option value=\"Livre\">Livre</option>" +
           "<option value=\"DVD\">DVD</option>" +
           "<option value=\"CD\">CD</option>" +
       "</select>" +
   "</p>" + "<input class=\"btn btn-primary\"type= \"submit\" value= \"Ajouter le document\" />" + "</form>");
	}
	
	@Override
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		doGet(arg0, arg1);
	}

}
