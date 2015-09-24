package shared.model.pieces;

/**
 * Representative of a settlement on the board in game
 * @author Steen
 *
 */

public class Settlement {

	int playerIndex;

	/**
	 * @pre playerIndex must be in the range [0, 3]
	 * @param playerIndex
	 * @post a new settlement is made, owned by the player that shares its playerIndex
	 */
	
	public Settlement(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}
	
}
