package swaggerModel;

import org.junit.Assert;

import shared.model.gamemap.VertexValue;
import shared.model.locations.VertexLocation;

public class VertexObject {
	private int owner;
	private VertexLocation location;
	
	public VertexObject(VertexValue vertex) {
		if (vertex.getSettlement() == null) {
			
		}
		else if (vertex.getCity() == null) {
			owner = vertex.getSettlement().getPlayerIndex();
		}
		else {
			Assert.assertTrue(false);
			owner = -1;
		}
		location = new VertexLocation(vertex.getLocation());
	}
	
	public int getOwner() {
		return owner;
	}
	
	public VertexLocation getLocation() {
		return location.getVertexLocation();
	}
}
