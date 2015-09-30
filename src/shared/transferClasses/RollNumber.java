package shared.transferClasses;

import java.io.Serializable;

public class RollNumber implements Serializable {
	
	public RollNumber(int playerIndex, int number) {
		this.number = number;
		this.playerIndex = playerIndex;
	}
	
	protected int playerIndex;

	protected int number;
	
	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
