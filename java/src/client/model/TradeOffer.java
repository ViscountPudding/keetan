package client.model;


public class TradeOffer {
	private int sender; // The index of the person offering the trade
	private int reciever; // The index of the person the trade is offered to
	private ResourceList offer; // Positive=offered, Negative=requested

	public TradeOffer(int sender, int reciever, ResourceList offer) {
		this.sender = sender;
		this.reciever = reciever;
		this.offer = offer;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getReciever() {
		return reciever;
	}

	public void setReciever(int reciever) {
		this.reciever = reciever;
	}

	public ResourceList getOffer() {
		return offer;
	}

	public void setOffer(ResourceList offer) {
		this.offer = offer;
	}
}
