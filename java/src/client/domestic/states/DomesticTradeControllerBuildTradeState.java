package client.domestic.states;

import shared.definitions.ResourceType;
import client.model.ResourceList;

public class DomesticTradeControllerBuildTradeState implements
		DomesticTradeControllerState {

	/**
	 * YOU CAN DO EVERYTHING!
	 */
	
	@Override
	public void startTrade() {
		// TODO Auto-generated method stub

	}

	@Override
	public void decreaseResourceAmount(ResourceType resource) {
		// TODO Auto-generated method stub

	}

	@Override
	public void increaseResourceAmount(ResourceType resource) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendTradeOffer(ResourceList offer, int receiver, ResourceList request) {
		// TODO Auto-generated method stub
		//OfferTrade command = new OfferTrade(ModelFacade.getInstance().whoseTurnIsItAnyway(), offer, receiver);
		//TradeOffer command = new TradeOffer(ModelFacade.whoseTurnIsItAnyway(), receiver, offer, request);

	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setResourceToReceive(ResourceType resource) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unsetResource(ResourceType resource) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelTrade() {
		// TODO Auto-generated method stub

	}

	@Override
	public void acceptTrade(boolean willAccept) {
		// TODO Auto-generated method stub

	}

}
