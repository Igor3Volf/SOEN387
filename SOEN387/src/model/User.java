package model;

public class User {
	
	private int id;
	private String userName;
	private String password;
	private int deckId;
	
	public int getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public int getDeckId() {
		return deckId;
	}	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setDeckId(int deckId) {
		this.deckId = deckId;
	}
	public User(int id, String u, String pass){
		this.id=id;
		this.userName=u;
		this.password=pass;
	}
	
}
