package shared.transferClasses;

import shared.model.locations.EdgeLocation;


/**
 * This plays the road building card, which actually builds two roads.
 * @param playerIndex the player building the two roads
 * @param spotOne location for the first road
 * @param spotTwo the location for the second road
 * @author willvdb
 */

public class RoadBuilding {

	public RoadBuilding(int playerIndex, EdgeLocation spotOne, EdgeLocation spotTwo) {
		this.playerIndex = playerIndex;
		this.spotOne = new SwaggerEdgeLocation(spotOne);
		this.spotTwo = new SwaggerEdgeLocation(spotTwo);
	}

	private String type = "Road_Building";
	private int playerIndex;
	private SwaggerEdgeLocation spotOne;
	private SwaggerEdgeLocation spotTwo;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public SwaggerEdgeLocation getSpotOne() {
		return spotOne;
	}
	public void setSpotOne(SwaggerEdgeLocation spotOne) {
		this.spotOne = spotOne;
	}
	public SwaggerEdgeLocation getSpotTwo() {
		return spotTwo;
	}
	public void setSpotTwo(SwaggerEdgeLocation spotTwo) {
		this.spotTwo = spotTwo;
	}
}
