package shared.transferClasses;

import shared.model.gamemap.VertexValue;

public class BuildCity {

	public BuildCity(int playerIndex, VertexValue vertexLocation, Boolean free) {
		this.playerIndex = playerIndex;
		this.vertexLocation = vertexLocation;
		this.free = free;
	}
	
	protected int playerIndex;
	protected VertexValue vertexLocation;
	protected Boolean free;
	
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
