package client.map.states;

import shared.definitions.PieceType;
import shared.model.locations.EdgeLocation;
import shared.model.locations.HexLocation;
import shared.model.locations.VertexLocation;
import client.data.RobPlayerInfo;

public class MapControllerSetupState implements MapControllerState {

	/**
	 * This is the MapController in the Setup State. You can do literally nothing. The canDos return false and the functions do nothing
	 */
	
	@Override
	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaceCity(VertexLocation vertLoc) {
		// TODO Auto-generated method stub
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
		
	}

	@Override
	public void placeSettlement(VertexLocation vertLoc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void placeCity(VertexLocation vertLoc) {
		// TODO Auto-generated method stub
		
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
