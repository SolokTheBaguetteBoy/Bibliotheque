package mediatheque;

import java.util.List;

public interface PersistentMediatheque {
// Jean-François Brette 01/01/2018
	List<Document> tousLesDocuments();

	Document getDocument(int numDocument);

	Utilisateur getUser(String login, String password);

	
	int empruntDocument(Document doc, Utilisateur user) throws EmpruntException;

	int retourDocument(Document doc) throws EmpruntException;

	void nouveauDocument(String nom, String Auteur, String type);

}
