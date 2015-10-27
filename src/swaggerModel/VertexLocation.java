package swaggerModel;

import shared.model.locations.HexLocation;
import shared.model.locations.VertexDirection;
import shared.model.locations.VertexLocation;

public class VertexLocation {
	private int x;
	private int y;
	private String direction;
	
	public VertexLocation (VertexLocation location) {
		location = location.getNormalizedLocation();
		x = location.getHexLoc().getX();
		y = location.getHexLoc().getY();
		direction = Model.vertexDirectionToString(location.getDir());
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public VertexDirection getVertexDirection() {
		return Model.stringToVertexDirection(direction);
	}
	
	public VertexLocation getVertexLocation() {
		return new VertexLocation(new HexLocation(x, y), getVertexDirection());
	}
}
