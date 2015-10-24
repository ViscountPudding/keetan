package swaggerModel;

import shared.model.Resource;
import shared.model.gamemap.Hex;
import shared.model.locations.HexLocation;

public class SwaggerNormalHex implements SwaggerHex {
	private HexLocation location;
	private String resource;
	private int number;
	
	public SwaggerNormalHex(Hex hex) {
		location = hex.getLocation();
		resource = SwaggerModel.resourceToString(hex.getResource());
	}

	@Override
	public HexLocation getLocation() {
		return location;
	}

	@Override
	public Resource getResource() {
		return SwaggerModel.resource;
	}

	@Override
	public int getChitNumber() {
		return number;
	}

}
