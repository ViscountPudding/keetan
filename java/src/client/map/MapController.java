package client.map;

import shared.definitions.CatanColor;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import client.base.Controller;
import client.data.RobPlayerInfo;
import client.map.states.MapControllerBuildTradeState;
import client.map.states.MapControllerDoublePlaceState;
import client.map.states.MapControllerDoubleWaitState;
import client.map.states.MapControllerNotTurnState;
import client.map.states.MapControllerRollingDiceState;
import client.map.states.MapControllerSetupState;
import client.map.states.MapControllerState;
import client.map.states.MapControllerThieveryState;
import client.model.EdgeLocation;
import client.model.Hex;
import client.model.HexLocation;
import client.model.ModelFacade;
import client.model.Port;
import client.model.Status;
import client.model.VertexLocation;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController {
	
	private IRobView robView;
	
	private MapControllerState state;
	
	public MapController(IMapView view, IRobView robView) {
		
		super(view);
		
		setRobView(robView);
		
		//This might be wrong
		state = new MapControllerSetupState();
		
		ModelFacade.addObserver(this);
	}
	
	public void set_state(MapControllerState newState) {
		state = newState;
	}
	
	public IMapView getView() {
		
		return (IMapView)super.getView();
	}
	
	private IRobView getRobView() {
		return robView;
	}
	private void setRobView(IRobView robView) {
		this.robView = robView;
	}
	
	protected void initFromModel() {
		for(Hex hex : ModelFacade.getHexes()) {
			getView().addHex(hex.getLocation(), hex.getType());
			if(hex.getType() != HexType.DESERT && hex.getType() != HexType.WATER) {
				getView().addNumber(hex.getLocation(), hex.getChitNumber());
			}
		}
		for(Port port : ModelFacade.getPorts()) {
			getView().addPort(new EdgeLocation(port.getLocation().getX(), port.getLocation().getY(), port.getDirection()), port.getResource().getPortType());
		}
		System.out.println("GG TAs");
	}

	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		return state.canPlaceRoad(edgeLoc);
		//return true;
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		return state.canPlaceSettlement(vertLoc);
		//return true;
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {
		return state.canPlaceCity(vertLoc);
		//return true;
	}

	public boolean canPlaceRobber(HexLocation hexLoc) {
		return state.canPlaceRobber(hexLoc);
		//return true;
	}
	
	public void placeRoad(EdgeLocation edgeLoc) {
		
		//getView().placeRoad(edgeLoc, CatanColor.ORANGE);
		state.placeRoad(edgeLoc);
	}

	public void placeSettlement(VertexLocation vertLoc) {
		
		//getView().placeSettlement(vertLoc, CatanColor.ORANGE);
		state.placeSettlement(vertLoc);
	}

	public void placeCity(VertexLocation vertLoc) {
		
		//getView().placeCity(vertLoc, CatanColor.ORANGE);
		state.placeCity(vertLoc);
	}

	public void placeRobber(HexLocation hexLoc) {
		
		getView().placeRobber(hexLoc);
		
		getRobView().showModal();
		
		state.placeRobber(hexLoc);
	}
	
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	
		//This is probably wrong but I've no idea how to fix it
		getView().startDrop(pieceType, CatanColor.ORANGE, true);
		
		state.startMove(pieceType, false, false);
	}
	
	public void cancelMove() {
		state.cancelMove();
	}
	
	public void playSoldierCard() {	
		state.playSoldierCard();
	}
	
	public void playRoadBuildingCard() {	
		state.playRoadBuildingCard();
	}
	
	public void robPlayer(RobPlayerInfo victim) {	
		state.robPlayer(victim);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (ModelFacade.getModelVersion() != -1) {
			initFromModel();
			
			if(ModelFacade.whoseTurnIsItAnyway() != ModelFacade.getPlayerInfo().getIndex()) {
				if(ModelFacade.whatStateMightItBe() == Status.FirstRound || ModelFacade.whatStateMightItBe() == Status.SecondRound) {
					state = new MapControllerDoubleWaitState();			
				}
				else {
					state = new MapControllerNotTurnState();
				}
			}
			else {
				switch(ModelFacade.whatStateMightItBe()) {
				case Rolling:
					state = new MapControllerRollingDiceState();
					break;
				case Discarding:
					break;
				case FirstRound:
					state = new MapControllerDoublePlaceState();
					break;
				case Playing:
					state = new MapControllerBuildTradeState();
					break;
				case Robbing:
					state = new MapControllerThieveryState();
					break;
				case SecondRound:
					state = new MapControllerDoublePlaceState();
					break;
				default:
					state = new MapControllerNotTurnState();
					break;
			}
			
			}
		}
	}
	
}

