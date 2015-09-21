package catana.resources;

/**
 * Class representing an edge of a hex
 * @author willvdb
 *
 */

public class EdgeLocation {
	
	private GridLocation hex;
	private String relativeLocation;
	
	public GridLocation getHex() {
		return hex;
	}
	public void setHex(GridLocation hex) {
		this.hex = hex;
	}
	public String getRelativeLocation() {
		return relativeLocation;
	}
	public void setRelativeLocation(String relativeLocation) {
		this.relativeLocation = relativeLocation;
	}
	
	

}
