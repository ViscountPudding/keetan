package bothEnds.model;

/**
 * Contains info about a trade offer
 */
public class TradeOffer {

	private int sender;
	private int receiver;
	private ResourceList offer;
	
	/**
	* @pre sender and receiver must represent two players, so must be between 0 and 3, and not the same
	* @post The trade offer is stored in memory
	*/
	public TradeOffer(int sender, int receiver, ResourceList offer) {
		this.sender = sender;
		this.receiver = receiver;
		this.offer = offer;
	}

	public int getSender() {
		return sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public ResourceList getOffer() {
		return offer;
	}
}
