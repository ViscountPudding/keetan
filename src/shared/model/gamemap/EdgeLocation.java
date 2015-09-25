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
	
	
}
