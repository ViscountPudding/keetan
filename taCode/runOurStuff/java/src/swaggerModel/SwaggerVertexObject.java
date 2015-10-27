package swaggerModel;

import org.junit.Assert;

import shared.model.gamemap.VertexValue;
import shared.model.locations.VertexLocation;

public class SwaggerVertexObject {
	private int owner;
	private SwaggerVertexLocation location;
	
	public SwaggerVertexObject(VertexValue vertex) {
		if (vertex.getSettlement() == null) {
			
		}
		else if (vertex.getCity() == null) {
			owner = vertex.getSettlement().getPlayerIndex();
		}
		else {
			Assert.assertTrue(false);
			owner = -1;
		}
		location = new SwaggerVertexLocation(vertex.getLocation());
	}
	
	public int getOwner() {
		return owner;
	}
	
	public VertexLocation getLocation() {
		return location.getVertexLocation();
	}
}
