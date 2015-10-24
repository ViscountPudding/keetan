package shared.transferClasses;

import shared.model.locations.EdgeLocation;

/**
 * This class builds a road at the specified location
 * @param playerIndex the player building the road
 * @param roadLocation the location to build
 * @author willvdb
 *
 */

public class BuildRoad {
	private String type = "buildRoad";
	private int playerIndex;
	private SwaggerEdgeLocation roadLocation;
	private Boolean free;


	public BuildRoad(int playerIndex, EdgeLocation roadLocation, Boolean free) {
		this.playerIndex = playerIndex;
		this.roadLocation = new SwaggerEdgeLocation(roadLocation);
		this.free = free;
	}
	
	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public SwaggerEdgeLocation getRoadLocation() {
		return roadLocation;
	}
	public void setRoadLocation(SwaggerEdgeLocation roadLocation) {
		this.roadLocation = roadLocation;
	}
	public Boolean getFree() {
		return free;
	}
	public void setFree(Boolean free) {
		this.free = free;
	}

}
