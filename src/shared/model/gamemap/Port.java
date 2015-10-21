package shared.model.gamemap;

import shared.definitions.PortType;
import shared.model.locations.EdgeLocation;
import shared.model.Resource;

/**
 * Contains data for special ports that improve trade ratios
 */
public class Port {
	
	//These are the only params we need I think
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
	 * @return the resource type the port works with
	 */
	public Resource getResource() {
		
		Resource resourceType;
		
		switch(type) {
		case WOOD:
			resourceType = Resource.WOOD;
			break;
		case BRICK:
			resourceType = Resource.BRICK;
			break;
		case SHEEP:
			resourceType = Resource.SHEEP;
			break;
		case WHEAT:
			resourceType = Resource.WHEAT;
			break;
		case ORE:
			resourceType = Resource.ORE;
			break;
		case THREE:
			resourceType = null;
			break;
		default:
			resourceType = null;
			break;
		}
		
		return resourceType;
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
