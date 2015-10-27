package model;

import shared.definitions.Direction;
import shared.definitions.VertexDirection;

/**
 * Represents the location of a vertex on a hex map
 */
public class VertexLocation {
	private int x;
	private int y;
	private Direction direction;
	
	public VertexLocation(int x, int y, Direction direction) {
		setX(x);
		setY(y);
		setDirection(direction);
	}
	public VertexLocation() {
		
	}
	
	public VertexLocation(int x2, int y2, VertexDirection vertexDirection) {
		setX(x);
		setY(y);
		setDirection(Direction.fromVertexDirection(vertexDirection));
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	/**
	 * Returns a canonical (i.e., unique) value for this vertex location. Since
	 * each vertex has three different locations on a map, this method converts
	 * a vertex location to a single canonical form. This is useful for using
	 * vertex locations as map keys.
	 * 
	 * @return Normalized vertex location
	 */
	public VertexLocation getNormalizedLocation() {
		HexLocation hex;
		switch (direction) {
			case NW:
			case NE:
				return this;
			case W:
				hex = (new HexLocation(x, y)).getNeighborLocation(Direction.SW);
				return new VertexLocation(hex.getX(), hex.getY(), Direction.NE);
			case SW:
				hex = (new HexLocation(x, y)).getNeighborLocation(Direction.S);
				return new VertexLocation(hex.getX(), hex.getY(), Direction.NW);
			case SE:
				hex = (new HexLocation(x, y)).getNeighborLocation(Direction.S);
				return new VertexLocation(hex.getX(), hex.getY(), Direction.NE);
			case E:
				hex = (new HexLocation(x, y)).getNeighborLocation(Direction.SE);
				return new VertexLocation(hex.getX(), hex.getY(), Direction.NW);
			default:
				assert false;
				return null;
		}
	}
	
	/**
	 * Validates a VertexLocation based on a radius criteria
	 * @pre none
	 * @param radius - the radius within which a VertexLocation is valid, note the normalized directions can equal radius on the negative side
	 * @return returns true if valid location given radius, false if otherwise
	 * @post see return
	 */
	public boolean validate(int radius) {
		if (direction == null) return false;
		VertexLocation vertex = getNormalizedLocation();
		if (vertex.x > radius || vertex.y >= radius) return false;
		if (vertex.x < -radius || vertex.y < -radius) return false;
		if (vertex.x == radius || vertex.x == -radius || vertex.y == -radius) {
			switch (vertex.direction) {
			case NW:
				if (x == radius && y < 0) return false;
				break;
			case NE:
				if (x < 0 || y > radius - 1) return false;
				break;
			case W:
			case SW:
			case SE:
			case E:
			default:
				return false;
			}
		}
		return true;
	}
	public HexLocation getHexLoc() {
		return new HexLocation(x, y);
	}
	public VertexDirection getVertexDirection() {
		return direction.getVertexDirection();
	}
}

