package shared.transferClasses;

public class AcceptTrade {

	public AcceptTrade(int playerIndex, Boolean willAccept) {
		this.playerIndex = playerIndex;
		this.willAccept = willAccept;
	}
	
	protected int playerIndex;
	protected Boolean willAccept;
	
	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public Boolean getWillAccept() {
		return willAccept;
	}
	public void setWillAccept(Boolean willAccept) {
		this.willAccept = willAccept;
	}

}
