package clientSide.guiThings.maritime.states;

import clientSide.exceptions.ServerException;
import clientSide.server.ClientServerFacade;
import shared.definitions.ResourceType;
import shared.model.ModelFacade;
import shared.transferClasses.MaritimeTrade;

public class MaritimeTradeControllerBuildTradeState implements
		MaritimeTradeControllerState {

	@Override
	public void startTrade() {
		// TODO Auto-generated method stub

	}

	@Override
	public void makeTrade(ResourceType input, ResourceType output, int ratio) {
		// TODO Auto-generated method stub
		MaritimeTrade command = new MaritimeTrade(ModelFacade.getInstance().whoseTurnIsItAnyway(), ratio, input, output);
		try {
			ClientServerFacade.getInstance().maritimeTrade(command);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void cancelTrade() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setGetResource(ResourceType resource) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setGiveResource(ResourceType resource) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unsetGetValue() {
		// TODO Auto-generated method stub

	}

	@Override
	public void unsetGiveValue() {
		// TODO Auto-generated method stub

	}

}
