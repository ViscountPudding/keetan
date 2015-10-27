package shared.model.gamemap;

import shared.definitions.ResourceType;
import shared.model.locations.HexLocation;

/**
 * Contains data about a given hex on the game map.
 */
public class Hex {
	private HexLocation location;
	private ResourceType resource;
	private int number;
	
	public HexLocation getLocation() {
		return location;
	}
	
	public void setLocation(HexLocation location) {
		this.location = location;
	}
	
	public ResourceType getResource() {
		return resource;
	}
	
	public void setResource(ResourceType resource) {
		this.resource = resource;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
}
