package shared.model;

import shared.definitions.PortType;

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
}