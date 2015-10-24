package swaggerModel;

import shared.model.gamemap.EdgeValue;
import shared.transferClasses.SwaggerEdgeLocation;

public class SwaggerRoad {
	private int owner;
	private SwaggerEdgeLocation location;
	
	public SwaggerRoad(EdgeValue edge) {
		owner = edge.getRoad().getPlayerIndex();
		location = new SwaggerEdgeLocation(edge.getLocation());
	}
	
	public int getOwner() {
		return owner;
	}
	public SwaggerEdgeLocation getLocation() {
		return location;
	}
}
