package shared.transferClasses;

import shared.model.gamemap.Direction;

/**
 * This class is used for the Swagger server
 * @author djoshuac
 */
public class EdgeLocationSwag {
	private int x;
	private int y;
	private Direction direction;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public EdgeLocationSwag(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	
}
