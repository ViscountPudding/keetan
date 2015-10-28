package client.model;

import shared.definitions.Direction;
import shared.definitions.ResourceType;

public class Port {
	private ResourceType resource;
	private HexLocation location;
	private Direction direction;
	private int ratio;

	public Port(ResourceType resource, HexLocation location, Direction direction, int ratio) {
		setResource(resource);
		setLocation(location);
		setDirection(direction);
		setRatio(ratio);
	}

	public ResourceType getResource() {
		return resource;
	}

	public void setResource(ResourceType resource) {
		this.resource = resource;
	}

	public HexLocation getLocation() {
		return location;
	}

	public void setLocation(HexLocation location) {
		this.location = location;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
}
