package client.map.states;

import shared.definitions.PieceType;
import shared.model.ModelFacade;
import shared.model.locations.EdgeLocation;
import shared.model.locations.HexLocation;
import shared.model.locations.VertexLocation;
import shared.transferClasses.RoadBuilding;
import shared.transferClasses.Soldier;
import client.data.RobPlayerInfo;
import client.exceptions.ServerException;
import client.server.ClientServerFacade;

public class MapControllerRollingDiceState implements MapControllerState {

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
//		// TODO Auto-generated method stub
//		Soldier command = new Soldier(ModelFacade.getInstance().whoseTurnIsItAnyway(), victimIndex, location);
//		try {
//			ClientServerFacade.getInstance().soldier(command);
//		} catch (ServerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void playRoadBuildingCard() {
//		// TODO Auto-generated method stub
//		RoadBuilding command = new RoadBuilding(ModelFacade.getInstance().whoseTurnIsItAnyway(), roadOne, roadTwo);
//		try {
//			ClientServerFacade.getInstance().roadBuilding(command);
//		} catch (ServerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void robPlayer(RobPlayerInfo victim) {
		// TODO Auto-generated method stub

	}

}
