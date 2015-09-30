package shared.transferClasses;

import shared.model.locations.HexLocation;

public class RobPlayer {
	
	public RobPlayer(int playerIndex, int victimIndex, HexLocation location){
		this.playerIndex = playerIndex;
		this.victimIndex = victimIndex;
		this.location = location;
	}
	
	protected int playerIndex;
	protected int victimIndex;
	protected HexLocation location;
	
	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public int getVictimIndex() {
		return victimIndex;
	}
	public void setVictimIndex(int victimIndex) {
		this.victimIndex = victimIndex;
	}
	public HexLocation getLocation() {
		return location;
	}
	public void setLocation(HexLocation location) {
		this.location = location;
	}
}
