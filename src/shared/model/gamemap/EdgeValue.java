package shared.model.gamemap;

import shared.model.pieces.Road;

/**
 * Contains data about the edges between hexes on the map.
 */
public class EdgeValue {
	
	private int playerIndex;
	private EdgeLocation location;
	private Road road;
	
	/**
	 * @pre playerIndex must be in the range [-1,3]
	 * @param playerIndex
	 * @param location
	 * @post This object's data is set to the given params. Road is null because there will be no road on the object when it is first constructed
	 */
	public EdgeValue(int playerIndex, EdgeLocation location) {
		this.playerIndex = playerIndex;
		this.location = location;
		this.road = null;
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

	public Road getRoad() {
		return road;
	}

	public void setRoad(Road road) {
		this.road = road;
	}
	
	
	
}
