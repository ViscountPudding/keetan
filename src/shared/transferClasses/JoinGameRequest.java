package shared.transferClasses;

/**
 * This transfer object is used for the join game request to the server
 * @author djoshuac
 *
 */
public class JoinGameRequest {
	private int id;
	private String color;
	
	/**
	 * @param id - the players id
	 * @param color - the color of the player
	 */
	public JoinGameRequest(int id, String color) {
		this.id = id;
		this.color = color;
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getColor() {
		return this.color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
