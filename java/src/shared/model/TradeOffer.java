//package shared.model;
//
///**
// * Contains info about a trade offer
// */
//public class TradeOffer {
//
//	private int sender;
//	private int receiver;
//	private ResourceList senderTrades;
//	private ResourceList receiverTrades;
//	
//	/**
//	* @pre sender and receiver must represent two players, so must be between 0 and 3, and not the same
//	* @post The trade offer is stored in memory
//	*/
//	public TradeOffer(int sender, int receiver, ResourceList senderTrades, ResourceList receiverTrades) {
//		this.sender = sender;
//		this.receiver = receiver;
//		this.senderTrades = senderTrades;
//		this.receiverTrades = receiverTrades;
//	}
//
//	public int getSenderIndex() {
//		return sender;
//	}
//	
//	public int getReceiverIndex() {
//		return receiver;
//	}
//
//	public ResourceList getSenderReceives() {
//		return receiverTrades;
//	}
//
//	public ResourceList getReceiverReceives() {
//		return senderTrades;
//	}
//
//	
//}
