package swaggerModel;

import shared.model.ResourceList;
import shared.model.TradeOffer;

public class SwaggerTradeOffer {
	private int sender; // The index of the person offering the trade
	private int reciever; // The index of the person the trade is offered to
	private ResourceList offer; // Positive=offered, Negative=requested
	
	public SwaggerTradeOffer (TradeOffer tradeOffer) {				
		ResourceList senderTrades = tradeOffer.getReceiverReceives();
		ResourceList recieverTrades = tradeOffer.getSenderReceives();

		sender = tradeOffer.getSenderIndex();
		reciever = tradeOffer.getReceiverIndex();
		offer = new ResourceList(senderTrades.getBrick() - recieverTrades.getBrick(),
				senderTrades.getOre() - recieverTrades.getOre(),
				senderTrades.getSheep() - recieverTrades.getSheep(),
				senderTrades.getWheat() - recieverTrades.getWheat(),
				senderTrades.getWood() - recieverTrades.getWood());
	}

	public TradeOffer toOurTradeOffer() {
		// TODO Auto-generated method stub
		return null;
	}
}
