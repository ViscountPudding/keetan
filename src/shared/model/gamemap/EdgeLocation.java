package shared.model.gamemap;

/**
 * Contains information about where an edge is located
 */
public class EdgeLocation {
	private HexLocation hexLocation;
	private Direction direction;
	
	/**
	 * @param location Location of the hex the edge belongs to.
	 * @param direction The direction from the center of the hex the edge is on.
	 */
	public EdgeLocation(HexLocation hexLocation, Direction direction) {
		this.hexLocation = hexLocation;
		this.direction = direction;
	}

	public HexLocation getHexLocation() {
		return hexLocation;
	}

	public Direction getDirection() {
		return direction;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {return true;}
		else if(obj == null) {return false;}
		else if(this.getClass() != obj.getClass()) {return false;}
		
		else {
			return compareLocations(this, (EdgeLocation) obj);
		}
	}
	
	private boolean compareLocations(EdgeLocation Edge1, EdgeLocation Edge2){
		if(Edge1.getHexLocation() == Edge2.getHexLocation()) {
			if(Edge1.getDirection() == Edge2.getDirection()) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
}
