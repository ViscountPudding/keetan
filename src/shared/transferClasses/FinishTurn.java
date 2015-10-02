package shared.transferClasses;

public class FinishTurn {

	public FinishMove(int playerIndex){
		this.playerIndex = playerIndex;
	}

	private String type = "finishTurn"
	private int playerIndex;

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

}
