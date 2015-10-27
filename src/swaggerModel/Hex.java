package swaggerModel;

import shared.model.Resource;
import shared.model.locations.HexLocation;

/**
 * This interface is used so that the Map's list of hexes can include the DesertHex which
 * has no chit-number or resource String
 * @author djoshuac
 */
public interface Hex {
	public HexLocation getLocation();
	public Resource getResource();
	public int getChitNumber();
}
