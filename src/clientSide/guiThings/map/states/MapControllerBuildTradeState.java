package clientSide.guiThings.map.states;

import shared.definitions.PieceType;
import shared.model.ModelFacade;
import shared.model.locations.EdgeLocation;
import shared.model.locations.HexLocation;
import shared.model.locations.VertexLocation;
import shared.transferClasses.BuildCity;
import shared.transferClasses.BuildRoad;
import shared.transferClasses.BuildSettlement;
import clientSide.exceptions.ServerException;
import clientSide.guiThings.data.RobPlayerInfo;
import clientSide.server.ClientServerFacade;

public class MapControllerBuildTradeState implements MapControllerState {

	@Override
	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		// TODO Auto-generated method stub
		
		//Maybe change this to the client-bound player (i.e. player index of whoever is on this machine)
		
		return ModelFacade.getInstance().canBuildRoad(ModelFacade.getInstance().whoseTurnIsItAnyway(), edgeLoc);
		
	}

	@Override
	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		// TODO Auto-generated method stub
		
		return ModelFacade.getInstance().canBuildSettlement(ModelFacade.getInstance().whoseTurnIsItAnyway(), vertLoc);
	}

	@Override
	public boolean canPlaceCity(VertexLocation vertLoc) {
		// TODO Auto-generated method stub
		
		//Can canPlaceCity in ModelFacade
		
		return ModelFacade.getInstance().canBuildCity(ModelFacade.getInstance().whoseTurnIsItAnyway(), vertLoc);
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
		
		BuildRoad command = new BuildRoad(ModelFacade.getInstance().whoseTurnIsItAnyway(), edgeLoc, false);
		
		try {
			ClientServerFacade.getInstance().buildRoad(command);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("WHOOPS!");
		}
		
	}

	@Override
	public void placeSettlement(VertexLocation vertLoc) {
		// TODO Auto-generated method stub
		
		//BuildSettlement command = new BuildSettlement(currentPlayer, vertLoc, false);
		//ClientServerFacade.sendTheThing(command);

		BuildSettlement command = new BuildSettlement(ModelFacade.getInstance().whoseTurnIsItAnyway(), vertLoc, false);
		
		try {
			ClientServerFacade.getInstance().buildSettlement(command);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("WHOOPS!");
		}
		
	}

	@Override
	public void placeCity(VertexLocation vertLoc) {
		// TODO Auto-generated method stub

		//BuildCity command = new BuildCity(currentlyPlayer, vertLoc, false);
		//ClientServerFacade.sendTheThing(command);
		
		BuildCity command = new BuildCity(ModelFacade.getInstance().whoseTurnIsItAnyway(), vertLoc, false);
		
		try {
			ClientServerFacade.getInstance().buildCity(command);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("WHOOPS!");
		}
		
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