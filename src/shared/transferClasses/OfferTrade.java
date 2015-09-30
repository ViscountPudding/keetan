package shared.transferClasses;

import shared.model.ResourceList;

public class OfferTrade {

	public OfferTrade(int playerIndex, ResourceList offer, int reciever) {
		this.playerIndex = playerIndex;
		this.offer = offer;
		this.reciever = reciever;
	}

	protected int playerIndex;
	protected ResourceList offer;
	protected int reciever;
	
	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public ResourceList getOffer() {
		return offer;
	}
	public void setOffer(ResourceList offer) {
		this.offer = offer;
	}
	public int getReciever() {
		return reciever;
	}
	public void setReciever(int reciever) {
		this.reciever = reciever;
	}


}
