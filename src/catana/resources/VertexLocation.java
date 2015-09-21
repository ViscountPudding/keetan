package catana.resources;

/**
 * Class representing a vertex
 * @author willvdb
 *
 */

public class VertexLocation {
	
	private GridLocation hex;
	private int vertexNum;
	
	public GridLocation getHex() {
		return hex;
	}
	public void setHex(GridLocation hex) {
		this.hex = hex;
	}
	public int getRelativeLocation() {
		return vertexNum;
	}
	public void setRelativeLocation(int vertexNum) {
		this.vertexNum = vertexNum;
	}
	
	

}
