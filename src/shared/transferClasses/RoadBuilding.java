package shared.transferClasses;


/**
 * This plays the road building card, which actually builds two roads.
 * @param playerIndex the player building the two roads
 * @param spotOne location for the first road
 * @param spotTwo the location for the second road
 * @author willvdb
 */

public class RoadBuilding {

	public RoadBuilding(int playerIndex, EdgeLocationSwag spotOne, EdgeLocationSwag spotTwo) {
		this.playerIndex = playerIndex;
		this.spotOne = spotOne;
		this.spotTwo = spotTwo;
	}

	private String type = "Road_Building";
	private int playerIndex;
	private EdgeLocationSwag spotOne;
	private EdgeLocationSwag spotTwo;
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
	public EdgeLocationSwag getSpotOne() {
		return spotOne;
	}
	public void setSpotOne(EdgeLocationSwag spotOne) {
		this.spotOne = spotOne;
	}
	public EdgeLocationSwag getSpotTwo() {
		return spotTwo;
	}
	public void setSpotTwo(EdgeLocationSwag spotTwo) {
		this.spotTwo = spotTwo;
	}
}
