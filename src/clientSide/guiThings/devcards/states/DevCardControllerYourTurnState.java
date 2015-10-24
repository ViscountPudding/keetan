package clientSide.guiThings.devcards.states;

import clientSide.exceptions.ServerException;
import clientSide.server.ClientServerFacade;
import shared.definitions.ResourceType;
import shared.model.ModelFacade;
import shared.model.locations.EdgeLocation;
import shared.model.locations.HexLocation;
import shared.transferClasses.BuyDevCard;
import shared.transferClasses.Monopoly;
import shared.transferClasses.RoadBuilding;
import shared.transferClasses.Soldier;
import shared.transferClasses.YearOfPlenty;

public class DevCardControllerYourTurnState implements DevCardControllerState {

	/**
	 * This the YourTurnState. You can do all of this stuff at any point on your turn. Lucky you.
	 */
	
	@Override
	public void startBuyCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelBuyCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buyCard() {
		// TODO Auto-generated method stub
		BuyDevCard command = new BuyDevCard(ModelFacade.getInstance().whoseTurnIsItAnyway());
		try {
			ClientServerFacade.getInstance().buyDevCard(command);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void startPlayCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelPlayCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playMonopolyCard(ResourceType resource) {
		// TODO Auto-generated method stub
		Monopoly command = new Monopoly(ModelFacade.getInstance().whoseTurnIsItAnyway(), resource);
		try {
			ClientServerFacade.getInstance().monopoly(command);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void playRoadBuildCard(EdgeLocation roadOne, EdgeLocation roadTwo) {
		// TODO Auto-generated method stub
		RoadBuilding command = new RoadBuilding(ModelFacade.getInstance().whoseTurnIsItAnyway(), roadOne, roadTwo);
		try {
			ClientServerFacade.getInstance().roadBuilding(command);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void playSoldierCard(int victimIndex, HexLocation location) {
		// TODO Auto-generated method stub
		Soldier command = new Soldier(ModelFacade.getInstance().whoseTurnIsItAnyway(), victimIndex, location);
		try {
			ClientServerFacade.getInstance().soldier(command);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resourceOne, ResourceType resourceTwo) {
		// TODO Auto-generated method stub
		YearOfPlenty command = new YearOfPlenty(ModelFacade.getInstance().whoseTurnIsItAnyway(), resourceOne, resourceTwo);
		try {
			ClientServerFacade.getInstance().yearOfPlenty(command);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
