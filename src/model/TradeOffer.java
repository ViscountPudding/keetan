package model;


public class TradeOffer {
	private int sender; // The index of the person offering the trade
	private int reciever; // The index of the person the trade is offered to
	private ResourceList offer; // Positive=offered, Negative=requested
	private ResourceList request;

	public TradeOffer(int sender, int receiver, ResourceList offer, ResourceList request) {
		this.sender = sender;
		this.reciever = reciever;
		this.offer = offer;
		this.reciever = reciever;
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
