package model.gamemap;

import model.HexDirection;

/**
 * Contains information about where an edge is located
 */
public class EdgeLocation {
	private HexLocation hexLocation;
	private HexDirection direction;
	
	/**
	 * @param location Location of the hex the edge belongs to.
	 * @param direction The direction from the center of the hex the edge is on.
	 */
	public EdgeLocation(HexLocation hexLocation, HexDirection direction) {
		this.hexLocation = hexLocation;
		this.direction = direction;
	}

	public HexLocation getHexLocation() {
		return hexLocation;
	}

	public HexDirection getDirection() {
		return direction;
	}
	
	
}
