package model.gamemap;

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
}
