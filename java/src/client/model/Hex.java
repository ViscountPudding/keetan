package client.model;

import shared.definitions.ResourceType;

/**
 * This interface is used so that the Map's list of hexes can include the DesertHex which
 * has no chit-number or resource String
 * @author djoshuac
 */
public class Hex {
	private HexLocation location;
	private ResourceType resource;
	private int number;
	
	public void setHexLocation(HexLocation location) {
		this.location = location;
	}
	
	public void setResource(ResourceType resource) {
		this.resource = resource;
	}
	
	public void setChitNumber(int chitNumber) {
		this.number = chitNumber;
	}

	public HexLocation getLocation() {
		return this.location;
	}
	
	public ResourceType getResource() {
		return this.resource;
	}
	
	public int getChitNumber() {
		return this.number;
	}
}
