package server;

/**
 * The Session stores the users info, aka the username password and ID
 * @author willvdb
 *
 */
public class Session {
	private String username;
	private String password;
	private int playerID;
	
	public Session(String username, String password, int playerID) {
		this.username = username;
		this.password = password;
		this.playerID = playerID;
	}
	
	/** 
	 * Creates a session from a cookie
	 * @param cookie
	 */
	public Session(String cookie) {
		
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the playerID
	 */
	public int getPlayerID() {
		return playerID;
	}
	
	/**
	 * @return The URL-encoded cookie representing the session
	 */
	public String getCookie() {
		return null;
	}
	

}
