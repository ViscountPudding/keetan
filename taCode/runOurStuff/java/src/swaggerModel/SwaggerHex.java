package swaggerModel;

import shared.model.Resource;
import shared.model.locations.HexLocation;

/**
 * This interface is used so that the SwaggerMap's list of hexes can include the SwaggerDesertHex which
 * has no chit-number or resource String
 * @author djoshuac
 */
public interface SwaggerHex {
	public HexLocation getLocation();
	public Resource getResource();
	public int getChitNumber();
}
