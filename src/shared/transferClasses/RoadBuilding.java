package shared.transferClasses;

import shared.model.locations.EdgeLocation;

/**
 * This plays the road building card, which actually builds two roads.
 */

public class RoadBuilding {

	public RoadBuilding(int playerIndex, EdgeLocation spotOne, EdgeLocation spotTwo) {
		this.playerIndex = playerIndex;
		this.spotOne = spotOne;
		this.spotTwo = spotTwo;
	}

	private String type = "Road_Building";
	private int playerIndex;
	private EdgeLocation spotOne;
	private EdgeLocation spotTwo;

	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public EdgeLocation getSpotOne() {
		return spotOne;
	}
	public void setSpotOne(EdgeLocation spotOne) {
		this.spotOne = spotOne;
	}
	public EdgeLocation getSpotTwo() {
		return spotTwo;
	}
	public void setSpotTwo(EdgeLocation spotTwo) {
		this.spotTwo = spotTwo;
	}

}
