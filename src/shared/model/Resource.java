package shared.model;

import shared.definitions.PortType;
import shared.definitions.ResourceType;

/**
 * An enumeration of possible resource types.
 */
public enum Resource {
	
	WOOD, BRICK, SHEEP, WHEAT, ORE, DESERT;

	public PortType getPortType() {
		switch (this) {
		case BRICK:
			return PortType.BRICK;
		case ORE:
			return PortType.ORE;
		case SHEEP:
			return PortType.SHEEP;
		case WHEAT:
			return PortType.WHEAT;
		case WOOD:
			return PortType.WOOD;
		default:
			return null;
		}
	}

	public static Resource fromResourceType(ResourceType resourceType) {
		switch (resourceType) {
		case BRICK:
			return Resource.BRICK;
		case ORE:
			return Resource.ORE;
		case SHEEP:
			return Resource.SHEEP;
		case WHEAT:
			return Resource.WHEAT;
		case WOOD:
			return Resource.WOOD;
		default:
			return null;
		}
	}
}