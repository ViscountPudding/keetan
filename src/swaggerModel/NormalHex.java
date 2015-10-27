package swaggerModel;

import shared.model.Resource;
import shared.model.gamemap.Hex;
import shared.model.locations.HexLocation;

public class NormalHex implements Hex {
	private HexLocation location;
	private String resource;
	private int number;
	
	public NormalHex(Hex hex) {
		location = hex.getLocation();
		resource = Model.resourceToString(hex.getResource());
	}

	@Override
	public HexLocation getLocation() {
		return location;
	}

	@Override
	public Resource getResource() {
		return Model.stringToResource(resource);
	}

	@Override
	public int getChitNumber() {
		return number;
	}

}
