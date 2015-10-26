package clientSide.guiThings.maritime;

import shared.definitions.*;
import shared.model.ModelFacade;
import shared.model.Resource;
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
		
		Resource desiredResource;
		
		switch(get)
		{
		case WOOD:
			desiredResource = Resource.WOOD;
			break;
		case BRICK:
			desiredResource = Resource.BRICK;
			break;
		case SHEEP:
			desiredResource = Resource.SHEEP;
			break;
		case WHEAT:
			desiredResource = Resource.WHEAT;
			break;
		case ORE:
			desiredResource = Resource.ORE;
			break;
		default:
			desiredResource = Resource.DESERT; //Will literally never happen
			break;
		}
			
		
		int ratio = ModelFacade.getInstance().getTradeRatio(ModelFacade.getInstance().whoseTurnIsItAnyway(), desiredResource);
		
		getTradeOverlay().closeModal();
		state.makeTrade(give, get, ratio);  //WILL BE A DIFFERENT NUMBER!
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

