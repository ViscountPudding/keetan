package clientSide.guiThings.domestic.states;

import shared.definitions.ResourceType;

public interface DomesticTradeControllerState {

	void startTrade();
	
	void decreaseResourceAmount(ResourceType resource);

	void increaseResourceAmount(ResourceType resource);

	void sendTradeOffer();

	void setPlayerToTradeWith(int playerIndex);

	void setResourceToReceive(ResourceType resource);

	void setResourceToSend(ResourceType resource);

	void unsetResource(ResourceType resource);

	void cancelTrade();

	void acceptTrade(boolean willAccept);	
}
