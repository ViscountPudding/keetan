package shared.transferClasses;

import shared.model.PlayerColor;

/**
 * This transfer object is used for the join game request to the server
 * @author djoshuac
 *
 */
public class JoinGameRequest {
	private int id;
	private PlayerColor color;
	
	/**
	 * @param id - the game id
	 * @param color - the color of the player
	 */
	public JoinGameRequest(int id, PlayerColor color) {
		setId(id);
		setColor(color);
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public PlayerColor getColor() {
		return this.color;
	}
	public void setColor(PlayerColor color) {
		this.color = color;
	}
}
