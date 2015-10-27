package shared.model.pieces;

/**
 * Representative of a city on the board in game
 * @author Steen
 *
 */

public class City {

	int playerIndex;

	/**
	 * @pre playerIndex must be in the range [0, 3]
	 * @param playerIndex
	 * @post the settlement at the vertex is removed, and replaced by a city owned by the same player
	 */
	
	public City(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}
	
	
}
