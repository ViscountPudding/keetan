package bothEnds.model.gamemap;

import bothEnds.model.Resource;
/**
 * Contains data about a given hex on the game map.
 */
public class Hex {
	private HexLocation location;
	private Resource resource;
	private int diceNumber;
	
	/**
	 * @pre diceNumber must be in the range [2,12]
	 * @param location The HexLocation of this hex
	 * @param resource The resource produced by this hex
	 * @param diceNumber The number that, when rolled by the dice, cause this hex to produce resources
	 * @post The objects internal values are set to the given params
	 */
	public Hex(HexLocation location, Resource resource, int diceNumber) {
		this.location = location;
		this.resource = resource;
		this.diceNumber = diceNumber;
	}
	
	public HexLocation getLocation() {
		return location;
	}
	public Resource getResource() {
		return resource;
	}
	public int getDiceNumber() {
		return diceNumber;
	}
}
