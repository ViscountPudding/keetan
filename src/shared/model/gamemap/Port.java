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
	 * @pre Ratio is in the range [2,3]
	 * @param resource
	 * @param location
	 * @param direction
	 * @param ratio
	 * @post The object's values are set to the given params.
	 */
	public Port(Resource resource, HexLocation location, String direction,
			int ratio) {
		this.resource = resource;
		this.location = location;
		this.direction = direction;
		this.ratio = ratio;
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
