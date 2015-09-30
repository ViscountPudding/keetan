package shared.model.gamemap;

import shared.definitions.PortType;
import shared.model.locations.EdgeLocation;

/**
 * Contains data for special ports that improve trade ratios
 */
public class Port {
	
	//These are the only params we need I think
	private PortType type;
	private EdgeLocation adjacentEdge1;
	private EdgeLocation adjacentEdge2;
	
	/**
	 * @pre 
	 * @param resource Should be null if it's a general port
	 * @param location
	 * @param direction
	 * @post The object's values are set to the given params.
	 */
	public Port(PortType type, EdgeLocation edge1, EdgeLocation edge2) {
		this.setType(type);
		this.setAdjacentEdge1(edge1);
		this.setAdjacentEdge2(edge2);
	}

	public PortType getType() {
		return type;
	}

	public void setType(PortType type) {
		this.type = type;
	}

	public EdgeLocation getAdjacentEdge1() {
		return adjacentEdge1;
	}

	public void setAdjacentEdge1(EdgeLocation adjacentEdge1) {
		this.adjacentEdge1 = adjacentEdge1;
	}

	public EdgeLocation getAdjacentEdge2() {
		return adjacentEdge2;
	}

	public void setAdjacentEdge2(EdgeLocation adjacentEdge2) {
		this.adjacentEdge2 = adjacentEdge2;
	}
	
	
	
}
