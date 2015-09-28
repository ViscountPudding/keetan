package shared.model.gamemap;

/**
 * Contains the x and y coordinates of a hex on the game map
 */
public class HexLocation {
	
	private int x;
	private int y;
	
	/**
	 * @pre x and y must not go off the board; i.e., ||x|| + ||y|| <= board radius
	 * @param x
	 * @param y
	 * @post The objects values are set to the given params
	 */
	public HexLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		HexLocation other = (HexLocation) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		
		return true;
	}
}
