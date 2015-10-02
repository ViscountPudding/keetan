package shared.transferClasses;

public class Monument {

	public Monument(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	private String type = "Monument";
	private int playerIndex;

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
}
