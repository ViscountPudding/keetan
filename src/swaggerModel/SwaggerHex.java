package swaggerModel;

import shared.model.gamemap.Hex;

public class SwaggerHex {
	private SwaggerHexLocation location;
	private String resource;
	private int number;
	
	public SwaggerHex(Hex hex) {
		resource = SwaggerModel.resourceToString(hex.getResource());
		
	}
	
	public SwaggerHexLocation getLocation() {
		return location;
	}
	public void setLocation(SwaggerHexLocation location) {
		this.location = location;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
}
