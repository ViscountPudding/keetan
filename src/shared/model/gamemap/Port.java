package shared.model.gamemap;

import shared.model.Resource;

/**
 * Contains data for special ports that improve trade ratios
 */
public class Port {

	private Resource resource;
	private HexLocation location;
	private String direction;
	private int ratio;
	
	/**
	 * @pre 
	 * @param resource Should be null if it's a general port
	 * @param location
	 * @param direction
	 * @post The object's values are set to the given params.
	 */
	public Port(Resource resource, HexLocation location, String direction) {
		this.resource = resource;
		this.location = location;
		this.direction = direction;
		if(resource == null) {
			this.ratio = 3;
		}
		else {
			this.ratio = 2;
		}
	}

	public Resource getResource() {
		return resource;
	}

	public HexLocation getLocation() {
		return location;
	}

	public String getDirection() {
		return direction;
	}

	public int getRatio() {
		return ratio;
	}
	
	
	
}
