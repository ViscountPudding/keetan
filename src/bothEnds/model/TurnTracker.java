package bothEnds.model;

/**
 * Contains information about the current turn
 */
public class TurnTracker {
	private int currentTurn;
	private Status status;
	private int longestRoad;
	private int largestArmy;

	/**
	 * @param currentTurn The current turn (usually 0)
	 * @param status
	 * @param longestRoad The size of the current longest road
	 * @param largestArmy The size of the current largest army
	 * @post The objects internal values are set to the given params
	 */
	public TurnTracker(int currentTurn, Status status, int longestRoad, int largestArmy) {
		this.currentTurn = currentTurn;
		this.status = status;
		this.longestRoad = longestRoad;
		this.largestArmy = largestArmy;
	}
	
	public int getCurrentTurn() {
		return currentTurn;
	}
	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getLongestRoad() {
		return longestRoad;
	}
	public void setLongestRoad(int longestRoad) {
		this.longestRoad = longestRoad;
	}
	public int getLargestArmy() {
		return largestArmy;
	}
	public void setLargestArmy(int largestArmy) {
		this.largestArmy = largestArmy;
	}
}
