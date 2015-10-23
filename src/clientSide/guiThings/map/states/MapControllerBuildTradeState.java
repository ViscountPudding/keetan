package clientSide.guiThings.map.states;

import shared.definitions.PieceType;
import shared.model.locations.EdgeLocation;
import shared.model.locations.HexLocation;
import shared.model.locations.VertexLocation;
import clientSide.guiThings.data.RobPlayerInfo;

public class MapControllerBuildTradeState implements MapControllerState {

	@Override
	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		// TODO Auto-generated method stub
		
		//Call canBuildRoad in ModelFacade
		
		return true;
	}

	@Override
	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		// TODO Auto-generated method stub
		
		//Call canPlaceSettlement in ModelFacade
		
		return true;
	}

	@Override
	public boolean canPlaceCity(VertexLocation vertLoc) {
		// TODO Auto-generated method stub
		
		//Can canPlaceCity in ModelFacade
		
		return false;
	}

	@Override
	public boolean canPlaceRobber(HexLocation hexLoc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void placeRoad(EdgeLocation edgeLoc) {
		// TODO Auto-generated method stub
		
		//BuildRoad command = new BuildRoad(ModelFacade.getCurrentPlayer, edgeLoc, false)
		//ClientServerFacade.sendTheThing(Not a real function) (command);
		
	}

	@Override
	public void placeSettlement(VertexLocation vertLoc) {
		// TODO Auto-generated method stub
		
		//BuildSettlement command = new BuildSettlement(currentPlayer, vertLoc, false);
		//ClientServerFacade.sendTheThing(command);

	}

	@Override
	public void placeCity(VertexLocation vertLoc) {
		// TODO Auto-generated method stub

		//BuildCity command = new BuildCity(currentlyPlayer, vertLoc, false);
		//ClientServerFacade.sendTheThing(command);
		
	}

	@Override
	public void placeRobber(HexLocation hexLoc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startMove(PieceType pieceType, boolean isFree,
			boolean allowDisconnected) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelMove() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playSoldierCard() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playRoadBuildingCard() {
		// TODO Auto-generated method stub

	}

	@Override
	public void robPlayer(RobPlayerInfo victim) {
		// TODO Auto-generated method stub

	}

}
