package model.gamemap;

/**
 * 
 */
public class VertexObject {

	private int owner;
	private EdgeLocation location;
	
	/**
	 * @pre owner must be in the range [-1,3], and location must be a valid EdgeLocation
	 * @param owner The owner of this vertex; should be -1 if nobody owns it. 
	 * @param location The map location of this vertex
	 * @post The object's stored owner and location are set to the given params.
	 */
	public VertexObject(int owner, EdgeLocation location) {
		this.owner = owner;
		this.location = location;
	}

	public int getOwner() {
		return owner;
	}

	public EdgeLocation getLocation() {
		return location;
	}
	
	
}
