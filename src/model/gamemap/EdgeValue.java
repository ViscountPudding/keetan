package model.gamemap;

/**
 * Contains data about the edges between hexes on the map.
 */
public class EdgeValue {
	
	private int playerIndex;
	private EdgeLocation location;
	
	/**
	 * @pre playerIndex must be in the range [-1,3]
	 * @param playerIndex
	 * @param location
	 * @post This object's data is set to the given params.
	 */
	public EdgeValue(int playerIndex, EdgeLocation location) {
		this.playerIndex = playerIndex;
		this.location = location;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}
	
	/**
	 * @pre playerIndex must be in the range [0-3]
	 * @param playerIndex The index of the player placing a road.
	 * @post Internally stored playerIndex is updated.
	 */
	public void setPlayerIndex(int playerIndex) {
		
	}

	public EdgeLocation getLocation() {
		return location;
	}
	
	
	
}
