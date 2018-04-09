package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mediatheque.*;

// classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-François Brette 01/01/2018
	private static String user = "GRP205US9";
	private static String password = "GRP205US9";
	private static String DTBSname = "jdbc:oracle:thin:@vs-oracle2:1521:ORCL";
	private static Connection con;
	private static String queryCommit = "COMMIT";
	
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
	
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(DTBSname, user, password);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private MediathequeData() {
	}

	// renvoie la liste de tous les documents de la bibliothèque
	@Override
	public List<Document> tousLesDocuments() {
		synchronized(this){
			
			String query = "SELECT * FROM Document";
			PreparedStatement stmt = null;
			ArrayList<Document> Docs = new ArrayList<Document>();
			
			try {
				
				stmt = con.prepareStatement(query);
				
				ResultSet rs = stmt.executeQuery();
			
			/*if(!rsLivre.next()){
					System.out.println("Pas de livres");
			}
			else { Docs.add(new Livre()) }*/
				while(rs.next()){
					Docs.add(new ADocument(rs.getInt("IdDoc"), rs.getString("NomDoc"), rs.getString("AuteurDoc"), rs.getInt("DocEmprunt"), rs.getString("DocType")));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Docs;
		}
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		synchronized(this){
			String query = "SELECT * " +
						"FROM Utilisateur " +
						"WHERE Utilisateur.NomUser = ? AND Utilisateur.Password = ?";
			PreparedStatement stmt = null;
			
			try {
				
				stmt = con.prepareStatement(query);
				stmt.setString(1, login);
				stmt.setString(2, password);
				ResultSet rs = stmt.executeQuery();
				System.out.println(rs.getRow());
				if(!rs.next()){
					System.out.println("NANI");
					return null;
				}
				else { return new Utilisateur(rs.getInt("IdUSER"), rs.getString("NomUser"), rs.getString("Password"), rs.getString("UserType")); }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		return tousLesDocuments().get(numDocument - 1);
	}

	@Override
	public void nouveauDocument(String nom, String Auteur, String type) {
		synchronized(this){
			String query = "INSERT INTO Document (IdDoc, NomDoc, AuteurDoc, DocEmprunt, DocType) VALUES (seq_doc.nextval, ?, ?, 0, ?)";
			PreparedStatement stmt = null;
			try {
				stmt = con.prepareStatement(query);
				stmt.setString(1, nom);
				stmt.setString(2, Auteur);
				stmt.setString(3, type);
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public int empruntDocument(Document doc, Utilisateur user) throws EmpruntException {
		synchronized(this){
			String query = "UPDATE Document " +
						"SET DocEmprunt = 1 " + 
						"WHERE IdDoc = ?";
			PreparedStatement stmt = null;
			try {
				stmt = con.prepareStatement(query);
				stmt.setInt(1, doc.getId());
				int result = stmt.executeUpdate();
				PreparedStatement stmtC = con.prepareStatement(queryCommit);
				stmtC.executeQuery();
				return result;
			}
			catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	@Override
	public int retourDocument(Document doc) throws EmpruntException {
		synchronized(this){
			String query = "UPDATE Document " +
						"SET DocEmprunt = 0 " + 
						"WHERE IdDoc = ?";
			PreparedStatement stmt = null;
			try {
				stmt = con.prepareStatement(query);
				stmt.setInt(1, doc.getId());
				int result = stmt.executeUpdate();
				PreparedStatement stmtC = con.prepareStatement(queryCommit);
				stmtC.executeQuery();
				return result;
			}
			catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	

}
