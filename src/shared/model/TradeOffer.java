package shared.model;

/**
 * Contains info about a trade offer
 */
public class TradeOffer {

	private int sender;
	private int receiver;
	private ResourceList senderReceives;
	private ResourceList receiverReceives;
	
	/**
	* @pre sender and receiver must represent two players, so must be between 0 and 3, and not the same
	* @post The trade offer is stored in memory
	*/
	public TradeOffer(int sender, int receiver, ResourceList senderReceives, ResourceList receiverReceives) {
		this.sender = sender;
		this.receiver = receiver;
		this.senderReceives = senderReceives;
		this.receiverReceives = receiverReceives;
	}

	public int getSender() {
		return sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public ResourceList getSenderReceives() {
		return senderReceives;
	}

	public ResourceList getReceiverReceives() {
		return receiverReceives;
	}
}
