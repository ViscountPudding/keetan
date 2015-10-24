package swaggerModel;

import shared.model.locations.HexLocation;
import shared.model.locations.VertexDirection;
import shared.model.locations.VertexLocation;

public class SwaggerVertexLocation {
	private int x;
	private int y;
	private String direction;
	
	public SwaggerVertexLocation (VertexLocation location) {
		location = location.getNormalizedLocation();
		x = location.getHexLoc().getX();
		y = location.getHexLoc().getY();
		direction = SwaggerModel.vertexDirectionToString(location.getDir());
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public VertexDirection getVertexDirection() {
		return SwaggerModel.stringToVertexDirection(direction);
	}
	
	public VertexLocation getVertexLocation() {
		return new VertexLocation(new HexLocation(x, y), getVertexDirection());
	}
}
