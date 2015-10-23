package clientSide.guiThings.map.states;

import shared.definitions.PieceType;
import shared.model.ModelFacade;
import shared.model.locations.EdgeLocation;
import shared.model.locations.HexLocation;
import shared.model.locations.VertexLocation;
import shared.transferClasses.RobPlayer;
import clientSide.exceptions.ServerException;
import clientSide.guiThings.data.RobPlayerInfo;
import clientSide.server.ClientServerFacade;

public class MapControllerThieveryState implements MapControllerState {

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
		return true;
		//Put can place robber here
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
		ModelFacade.getInstance().placeRobber(hexLoc);
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
		RobPlayer command = new RobPlayer(ModelFacade.getInstance().whoseTurnIsItAnyway(), victim.getId(), ModelFacade.getInstance().findRobber());
		try {
			ClientServerFacade.getInstance().robPlayer(command);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("WHOOOPS!");
		}
		
	}

}
