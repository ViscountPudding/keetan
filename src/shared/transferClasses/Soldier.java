package shared.transferClasses;

import shared.model.locations.EdgeLocation;
import shared.model.locations.HexLocation;

public class Soldier {

	public Soldier(int playerIndex, int victimIndex, HexLocation location){
		this.playerIndex = playerIndex;
		this.victimIndex = victimIndex;
		this.location = location;
	}

	private String type = "Soldier";
	private int playerIndex;
	private int victimIndex;
	private HexLocation location;

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
