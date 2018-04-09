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

public class ServiceRetour extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//out.println("<form method=\"POST\" action=\"http://localhost:8080/Bibliotheque/AbonneEmpruntDone\"><input type=\"text\" name=\"Emprunt\" value=\"Emprunt\"><input type=\"submit\" name=\"Retour\" value=\"Retour\"></form>");
		List<Document> Docs = Mediatheque.getInstance().tousLesDocuments();
		for(Document doc : Docs){
			if(doc.getEmprunt()){
				out.println("<br>" + doc.getType() + " " + doc.getName() + " " + doc.getId() + " <form method=\"POST\" action=\"http://localhost:8080/Bibliotheque/AbonneRetourDone?idDoc=" + doc.getId() + "\"><input type=\"submit\" name=\"Retour\" value=\"Retour\"></form>");
			}
		}
	}

}
