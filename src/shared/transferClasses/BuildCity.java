package shared.transferClasses;

import shared.model.gamemap.VertexObject;
import shared.model.gamemap.VertexObject;

public class BuildCity {

	public BuildCity(int playerIndex, VertexObject vertexLocation, Boolean free) {
		this.playerIndex = playerIndex;
		this.vertexLocation = vertexLocation;
		this.free = free;
	}
	
	protected int playerIndex;
	protected VertexObject vertexLocation;
	protected Boolean free;
	
	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public VertexObject getSpotOne() {
		return vertexLocation;
	}
	public void setSpotOne(VertexObject vertexLocation) {
		this.vertexLocation = vertexLocation;
	}
	public Boolean getFree() {
		return free;
	}
	public void setFree(Boolean free) {
		this.free = free;
	}

}
