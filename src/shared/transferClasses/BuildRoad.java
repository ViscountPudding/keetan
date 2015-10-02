package shared.transferClasses;

import shared.model.locations.EdgeLocation;

public class BuildRoad {

	public BuildCity(int playerIndex, EdgeLocation roadLocation, Boolean free) {
		this.playerIndex = playerIndex;
		this.roadLocation = roadLocation;
		this.free = free;
	}

	private String type = "buildRoad";
	private int playerIndex;
	private EdgeLocation roadLocation;
	private Boolean free;

	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public EdgeLocation getRoadLocation() {
		return roadLocation;
	}
	public void setRoadLocation(EdgeLocation roadLocation) {
		this.roadLocation = roadLocation;
	}
	public Boolean getFree() {
		return free;
	}
	public void setFree(Boolean free) {
		this.free = free;
	}

}
