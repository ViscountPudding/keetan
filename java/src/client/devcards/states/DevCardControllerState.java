package client.devcards.states;

import model.EdgeLocation;
import model.HexLocation;
import shared.definitions.ResourceType;

public interface DevCardControllerState {

	void startBuyCard();
	
	void cancelBuyCard();
	
	void buyCard();
	
	void startPlayCard();
	
	void cancelPlayCard();
	
	void playMonopolyCard(ResourceType resource);
	
	void playMonumentCard();
	
	void playRoadBuildCard(EdgeLocation roadOne, EdgeLocation roadTwo);
	
	void playSoldierCard(int victimIndex, HexLocation location);
	
	void playYearOfPlentyCard(ResourceType resourceOne, ResourceType resourceTwo);
	
}
