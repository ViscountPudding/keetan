package shared.model.gamemap;

import java.util.List;

import shared.model.pieces.Road;

/**
 * Contains data about the edges between hexes on the map.
 */
public class EdgeValue {
	
	private int playerIndex;
	private EdgeLocation locationNorth;
	private EdgeLocation locationSouth;
	private Road road;
	
	/**
	 * @pre playerIndex must be in the range [-1,3]
	 * @param playerIndex
	 * @param location
	 * @post This object's data is set to the given params. Road is null because there will be no road on the object when it is first constructed
	 */
	public EdgeValue(int playerIndex, EdgeLocation location) {
		this.playerIndex = playerIndex;
		JUXTAPOSE(location);
		this.road = null;
	}


	/**
	 * Sets the edge's North and South locations, referring to its relative position based on the two hexes that share it
	 * @param location the location passed into the constructor
	 */
	private void JUXTAPOSE(EdgeLocation location) {
		
		int oppositeX;
		int oppositeY;
		HexLocation opposite_hexLocation;
		Direction opposite_direction;
		
		switch (location.getDirection()) 
		{
			case Northwest:
				this.locationNorth = location;
				oppositeX = location.getHexLocation().getX() + 1;
				oppositeY = location.getHexLocation().getY() - 1;
				opposite_hexLocation = new HexLocation(oppositeX, oppositeY);
				opposite_direction = Direction.Southeast;
				this.locationSouth = new EdgeLocation(opposite_hexLocation, opposite_direction);
				break;
			case North:
				this.locationNorth = location;
				oppositeX = location.getHexLocation().getX();
				oppositeY = location.getHexLocation().getY() - 1;
				opposite_hexLocation = new HexLocation(oppositeX, oppositeY);
				opposite_direction = Direction.South;
				this.locationSouth = new EdgeLocation(opposite_hexLocation, opposite_direction);
				break;
			case Northeast:
				this.locationNorth = location;
				oppositeX = location.getHexLocation().getX() - 1;
				oppositeY = location.getHexLocation().getY() - 1;
				opposite_hexLocation = new HexLocation(oppositeX, oppositeY);
				opposite_direction = Direction.Southwest;
				this.locationSouth = new EdgeLocation(opposite_hexLocation, opposite_direction);
				break;
			case Southeast:
				this.locationSouth = location;
				oppositeX = location.getHexLocation().getX() - 1;
				oppositeY = location.getHexLocation().getY() + 1;
				opposite_hexLocation = new HexLocation(oppositeX, oppositeY);
				opposite_direction = Direction.Northwest;
				this.locationNorth = new EdgeLocation(opposite_hexLocation, opposite_direction);
				break;
			case South:
				this.locationSouth = location;
				oppositeX = location.getHexLocation().getX();
				oppositeY = location.getHexLocation().getY() + 1;
				opposite_hexLocation = new HexLocation(oppositeX, oppositeY);
				opposite_direction = Direction.North;
				this.locationNorth = new EdgeLocation(opposite_hexLocation, opposite_direction);
				break;
			case Southwest:
				this.locationSouth = location;
				oppositeX = location.getHexLocation().getX() + 1;
				oppositeY = location.getHexLocation().getY() + 1;
				opposite_hexLocation = new HexLocation(oppositeX, oppositeY);
				opposite_direction = Direction.Northeast;
				this.locationNorth = new EdgeLocation(opposite_hexLocation, opposite_direction);
				break;
			default: break;
		}
	}
	
	public int getPlayerIndex() {
		return playerIndex;
	}
	
	/**
	 * @pre playerIndex must be in the range [0-3]
	 * @param playerIndex The index of the player placing a road.
	 * @post Internally stored playerIndex is updated.
	 */
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public EdgeLocation getLocationNorth() {
		return locationNorth;
	}

	public EdgeLocation getLocationSouth() {
		return locationSouth;
	}
	
	public Road getRoad() {
		return road;
	}

	public void setRoad(Road road) {
		this.road = road;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {return true;}
		else if(obj == null) {return false;}
		else if(this.getClass() != obj.getClass()) {return false;}
		
		else {
			return compare_Edges(this, (EdgeValue) obj);
		}
	}
	
	private boolean compare_Edges(EdgeValue Edge1, EdgeValue Edge2) {
		if(Edge1.getLocationNorth() == Edge2.getLocationNorth()) {
			if(Edge1.getLocationSouth() == Edge2.getLocationSouth()) {
				if(Edge1.getRoad() == Edge2.getRoad()) {
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
		else {
			return false;
		}
	}


	public List<EdgeValue> getAdjacentEdges() {
		// TODO Auto-generated method stub
		return null;
	}


	public List<VertexObject> getAdjacentVertices() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
