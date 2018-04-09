package persistantdata;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public class ADocument implements Document {

	private int IdDoc;
	private String NomDoc;
	private String AuteurDoc;
	private boolean DocEmprunt;
	private String DocType;
	
	public ADocument(int id, String name, String author, String Type){
		IdDoc = id;
		NomDoc = name;
		AuteurDoc = author;
		DocEmprunt = false;
		DocType = Type;
	}
	
	public ADocument(int id, String name, String author, int Emprunt, String Type){
		IdDoc = id;
		NomDoc = name;
		AuteurDoc = author;
		if(Emprunt == 1){
			DocEmprunt = true;
		}
		else {
			DocEmprunt = false;
		}
		DocType = Type;
	}
	
	@Override
	public void emprunter(Utilisateur a) throws EmpruntException {
		this.DocEmprunt =  true;
		
	}

	@Override
	public void retour() {
		this.DocEmprunt =  false;
		
	}

	@Override
	public Object[] affiche() {
		//Non utilisée
		return null;
	}
	
	@Override
	public String getName() {
		return NomDoc;
	}

	@Override
	public String getType() {
		return DocType;
	}

	@Override
	public int getId() {
		return IdDoc;
	}

	@Override
	public boolean getEmprunt(){
		return DocEmprunt;
	}
}
