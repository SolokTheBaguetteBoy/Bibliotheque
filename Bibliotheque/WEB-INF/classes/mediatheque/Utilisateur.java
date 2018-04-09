package mediatheque;

public class Utilisateur {
	
	private int IdUser;
	private String NomUser;
	private String password;
	private String type;
	
	public Utilisateur(int id, String name, String password, String type){
		this.IdUser = id;
		this.NomUser = name;
		this.password = password;
		this.type = type;
	}
	
	public String getName(){
		return this.NomUser;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public String getType(){
		return this.type;
	}
}
