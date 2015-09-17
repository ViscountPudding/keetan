package model.gamemap;

public class Hex {
	
	public Hex(HexLocation location, String resource, int number) {
		this.location = location;
		this.resource = resource;
		this.number = number;
	}
	
	private HexLocation location;
	private String resource;
	private int number;
	
	public HexLocation getLocation() {
		return location;
	}
	public String getResource() {
		return resource;
	}
	public int getNumber() {
		return number;
	}
}
