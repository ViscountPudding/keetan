package model.gamemap;

public class EdgeValue {
	
	private int index;
	private EdgeLocation location;
	
	public EdgeValue(int index, EdgeLocation location) {
		this.index = index;
		this.location = location;
	}

	public int getIndex() {
		return index;
	}

	public EdgeLocation getLocation() {
		return location;
	}
	
	
	
}
