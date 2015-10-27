package swaggerModel;

import shared.model.gamemap.EdgeValue;
import shared.transferClasses.EdgeLocation;

public class Road {
	private int owner;
	private EdgeLocation location;
	
	public Road(EdgeValue edge) {
		owner = edge.getRoad().getPlayerIndex();
		location = new EdgeLocation(edge.getLocation());
	}
	
	public int getOwner() {
		return owner;
	}
	public EdgeLocation getLocation() {
		return location;
	}
}
