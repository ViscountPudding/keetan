package model.gamemap;

public class VertexObject {

	private int owner;
	private EdgeLocation location;
	
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
