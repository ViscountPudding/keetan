package shared.model.gamemap;

import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.model.locations.EdgeLocation;

/**
 * Contains data for special ports that improve trade ratios
 */
public class Port {
	
	private PortType type;
	private EdgeLocation edge;
	
	/**
	 * @pre 
	 * @param resource Should be null if it's a general port
	 * @param location
	 * @param direction
	 * @post The object's values are set to the given params.
	 */
	public Port(PortType type, EdgeLocation edge) {
		this.setType(type);
		this.setEdge(edge);
	}

	public PortType getType() {
		return type;
	}

	/**
	 * Returns a resource type based on the type of the port
	 * @pre Port.getType() cannot return null
	 * @post see return
	 * @return the ResourceType associated with the PortType
	 */
	public ResourceType getResourceType() {
		
		switch(type) {
		case WOOD:
			return ResourceType.WOOD;
		case BRICK:
			return ResourceType.BRICK;
		case SHEEP:
			return ResourceType.SHEEP;
		case WHEAT:
			return ResourceType.WHEAT;
		case ORE:
			return ResourceType.ORE;
		case THREE:
		default:
			return null;
		}
	}
	
	public void setType(PortType type) {
		this.type = type;
	}

	public EdgeLocation getEdge() {
		return edge;
	}

	public void setEdge(EdgeLocation edge) {
		this.edge = edge;
	}
}
