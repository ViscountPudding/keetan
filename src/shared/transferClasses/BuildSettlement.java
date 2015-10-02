package shared.transferClasses;

import shared.model.gamemap.VertexValue;

public class BuildSettlement {

	public BuildSettlement(int playerIndex, VertexValue vertexLocation, Boolean free) {
		this.playerIndex = playerIndex;
		this.vertexLocation = vertexLocation;
		this.free = free;
	}

	private String type = "buildSettlement"
	private int playerIndex;
	private VertexValue vertexLocation;
	private Boolean free;

	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public VertexValue getSpotOne() {
		return vertexLocation;
	}
	public void setSpotOne(VertexValue vertexLocation) {
		this.vertexLocation = vertexLocation;
	}
	public Boolean getFree() {
		return free;
	}
	public void setFree(Boolean free) {
		this.free = free;
	}

}
