package shared.transferClasses;

import shared.model.ResourceList;

public class DiscardCards {

	public DiscardCards(int playerIndex, ResourceList discardedCards) {
		this.playerIndex = playerIndex;
		this.discardedCards = discardedCards;
	}

	private String type = "discardCards";
	private int playerIndex;
	private ResourceList discardedCards;

	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public ResourceList getDiscardedCards() {
		return discardedCards;
	}
	public void setDiscardedCards(ResourceList discardedCards) {
		this.discardedCards = discardedCards;
	}

}
