package clientSide.guiThings.domestic.states;

import clientSide.exceptions.ServerException;
import clientSide.server.ClientServerFacade;
import shared.definitions.ResourceType;
import shared.model.ModelFacade;
import shared.model.ResourceList;
import shared.model.TradeOffer;
import shared.transferClasses.OfferTrade;

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
		TradeOffer command = new TradeOffer(ModelFacade.getInstance().whoseTurnIsItAnyway(), receiver, offer, request);
		
		try {
			ClientServerFacade.getInstance().offerTrade(command);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
