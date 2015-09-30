package shared.transferClasses;

import shared.model.gamemap.EdgeLocation;

public class BuildRoad {

	public BuildRoad(int playerIndex, EdgeLocation roadLocation, Boolean free) {
		this.playerIndex = playerIndex;
		this.roadLocation = roadLocation;
		this.free = free;
	}
	
	protected int playerIndex;
	protected EdgeLocation roadLocation;
	protected Boolean free;
	
	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public EdgeLocation getSpotOne() {
		return roadLocation;
	}
	public void setSpotOne(EdgeLocation roadLocation) {
		this.roadLocation = roadLocation;
	}
	public Boolean getFree() {
		return free;
	}
	public void setFree(Boolean free) {
		this.free = free;
	}

}
