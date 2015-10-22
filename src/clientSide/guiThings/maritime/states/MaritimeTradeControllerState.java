package clientSide.guiThings.maritime.states;

import shared.definitions.ResourceType;

public interface MaritimeTradeControllerState {

	void startTrade();

	void makeTrade();

	void cancelTrade();
	
	void setGetResource(ResourceType resource);

	void setGiveResource(ResourceType resource);

	void unsetGetValue();

	void unsetGiveValue();	
}
