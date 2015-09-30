package shared.transferClasses;

public class FinishMove {
	
	public FinishMove(int playerIndex){
		this.playerIndex = playerIndex;
	}
	
	protected int playerIndex;
	
	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

}
