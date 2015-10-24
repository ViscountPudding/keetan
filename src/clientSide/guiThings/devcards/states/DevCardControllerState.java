package clientSide.guiThings.devcards.states;

import shared.definitions.ResourceType;
import shared.model.locations.EdgeLocation;
import shared.model.locations.HexLocation;

public interface DevCardControllerState {

	void startBuyCard();
	
	void cancelBuyCard();
	
	void buyCard();
	
	void startPlayCard();
	
	void cancelPlayCard();
	
	void playMonopolyCard(ResourceType resource);
	
	void playRoadBuildCard(EdgeLocation roadOne, EdgeLocation roadTwo);
	
	void playSoldierCard(int victimIndex, HexLocation location);
	
	void playYearOfPlentyCard(ResourceType resourceOne, ResourceType resourceTwo);
	
}
