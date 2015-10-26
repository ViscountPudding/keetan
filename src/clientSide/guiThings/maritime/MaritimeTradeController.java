package clientSide.guiThings.maritime;

import shared.definitions.*;
import clientSide.guiThings.base.*;
import clientSide.guiThings.maritime.states.MaritimeTradeControllerState;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController {

	private IMaritimeTradeOverlay tradeOverlay;
	
	private MaritimeTradeControllerState state;
	
	private ResourceType give;
	private ResourceType get;
	
	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay) {
		
		super(tradeView);

		setTradeOverlay(tradeOverlay);
		
		give = null;
		get = null;
	}
	
	public IMaritimeTradeView getTradeView() {
		
		return (IMaritimeTradeView)super.getView();
	}
	
	public IMaritimeTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IMaritimeTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	public void set_state(MaritimeTradeControllerState newState) {
		state = newState;
	}
	
	@Override
	public void startTrade() {
		
		getTradeOverlay().showModal();
	}

	@Override
	public void makeTrade() {

		//Need a way to get the trade ratio from the ModelFacade
		
		getTradeOverlay().closeModal();
		state.makeTrade(give, get, 4);  //WILL BE A DIFFERENT NUMBER!
	}

	@Override
	public void cancelTrade() {

		getTradeOverlay().closeModal();
		
		unsetGetValue();
		unsetGiveValue();
	}

	@Override
	public void setGetResource(ResourceType resource) {
		get = resource;
	}

	@Override
	public void setGiveResource(ResourceType resource) {
		give = resource;
	}

	@Override
	public void unsetGetValue() {
		get = null;
	}

	@Override
	public void unsetGiveValue() {
		give = null;
	}

}

