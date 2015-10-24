package swaggerModel;

import org.junit.Assert;

import shared.definitions.PortType;
import shared.model.Resource;
import shared.model.gamemap.Port;
import shared.model.locations.EdgeLocation;
import shared.model.locations.HexLocation;

public class SwaggerPort {
	private String resource;
	private HexLocation location;
	private String direction;
	private int ratio;

	public SwaggerPort(Port port) {
		EdgeLocation edge = port.getEdge().getNormalizedLocation();
		HexLocation edgeHexLocation = edge.getHexLoc();
		
		switch (edge.getDir()) {
		case North:
			edgeHexLocation = new HexLocation(edgeHexLocation.getX(), edgeHexLocation.getY() + 1);
		case NorthWest:
			edgeHexLocation = new HexLocation(edgeHexLocation.getX() - 1, edgeHexLocation.getY());
		case SouthWest:
			edgeHexLocation = new HexLocation(edgeHexLocation.getX() - 1, edgeHexLocation.getY() - 1);
		case South:
			edgeHexLocation = new HexLocation(edgeHexLocation.getX(), edgeHexLocation.getY() - 1);
		case SouthEast:
			edgeHexLocation = new HexLocation(edgeHexLocation.getX() + 1, edgeHexLocation.getY());
		case NorthEast:
			edgeHexLocation = new HexLocation(edgeHexLocation.getX() + 1, edgeHexLocation.getY() + 1);
		default:
			Assert.assertTrue(false);
		}
		location = edgeHexLocation;
		direction = SwaggerModel.edgeDirectionToString(edge.getDir());
		if (port.getType() == PortType.THREE) {
			ratio = 3;
		}
		else {
			ratio = 2;
		}
	}

	public Resource getResource() {
		return SwaggerModel.stringToResource(resource);
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
