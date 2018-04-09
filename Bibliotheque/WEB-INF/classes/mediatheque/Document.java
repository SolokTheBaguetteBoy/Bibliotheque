// Jean-François Brette 01/01/2018

package mediatheque;

public interface Document {
// Jean-François Brette 01/01/2018
	
	void emprunter(Utilisateur a) throws EmpruntException;
	void retour();
	Object[] affiche();
	String getName();
	String getType();
	int getId();
	boolean getEmprunt();
	
}
