package shared.transferClasses;

import shared.model.locations.EdgeDirection;
import shared.model.locations.EdgeLocation;
import swaggerModel.Model;

/**
 * This class is used for the  server
 * @author djoshuac
 */
public class EdgeLocation {
	private int x;
	private int y;
	private String direction;
	
	public EdgeLocation(EdgeLocation edge) {
		edge = edge.getNormalizedLocation();
		this.x = edge.getHexLoc().getX();
		this.y = edge.getHexLoc().getY();
		this.direction = Model.edgeDirectionToString(edge.getDir());
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public EdgeDirection getEdgeDirection() {
		return Model.stringToEdgeDirection(direction);
	}
}
