package mediatheque;

import java.util.List;

/**
 cette classe repr�sente la mediatheque du point de vue du domaine
 cette classe est un singleton
 elle a un attribut qui fait le lien avec les donn�es persistentes
 
 LES SERVLETS DOIVENT S'ADRESSER A CETTE CLASSE EXCLUSIVEMENT
 POUR INTERROGER LES DONNEES

 beaucoup des m�thodes de Mediatheque sont d�l�gu�es � l'attribut de persistance
 qui devra r�percuter ces op�rations sur les donn�es persistantes

*/
public class Mediatheque {
// Jean-Fran�ois Brette 01/01/2018

// singleton standard ======================== 
	static {
		instance = new Mediatheque();
	}
	private static Mediatheque instance;
	public static Mediatheque getInstance() {
		return instance;
	}
	private Mediatheque () {}
// fin - singleton standard ==================

// lien avec les donn�es persistantes +++++++++++++++
	private PersistentMediatheque data;

	public void setData(PersistentMediatheque data) {
		if (this.data == null) this.data = data;
	}
// fin - lien avec les donn�es persistantes +++++++++

// ********** action sur le document ***********************

	// enregistre l'emprunt par l'abonn� a du document d)

	public int emprunt(Document d, Utilisateur a) throws EmpruntException {
		
		d.emprunter(a);
		return data.empruntDocument(d, a);
	}

	//enregistre le retour du document d)

	public void retour (Document d) throws EmpruntException {
		d.retour();
		data.retourDocument(d);
	}

// *********************** d�l�gation **********************

	// renvoie la liste de tous les documents de la biblioth�que

	public List<Document> tousLesDocuments() {
		return data.tousLesDocuments();
	}

	// renvoie le user de login et passwd 
	// si pas trouv�, renvoie null
	
	public Utilisateur getUser (String login, String password) {
		System.out.println("We're here buddy");
		return data.getUser(login, password);
	}

	// renvoie le document de num�ro numDocument
	// si pas trouv�, renvoie null

	public Document getDocument(int numDocument) {
		return data.getDocument(numDocument);
	}
	
	// ajoute un nouveau document

	public void nouveauDocument(String nom, String Auteur, String type) {
		data.nouveauDocument(nom, Auteur, type);
	};

}
