package shared.transferClasses;

import shared.definitions.ResourceType;
import shared.model.Resource;

/**
 * Plays a 'Year of Plenty' card from your hand to gain the two specified resources.
 * @param playerIndex the player playing the card
 * @param resourceOne the first resource affected
 * @parma resourceTwo the second resource affected
 * @author willvdb
 *
 */

public class YearOfPlenty {

	public YearOfPlenty(int playerIndex, Resource resourceOne, Resource resourceTwo) {
		this.playerIndex = playerIndex;
		this.resourceOne = resourceOne;
		this.resourceTwo = resourceTwo;
	}

	public YearOfPlenty(int playerIndex, ResourceType resourceOne, ResourceType resourceTwo) {
		this.playerIndex = playerIndex;
		this.resourceOne = Resource.fromResourceType(resourceOne);
		this.resourceTwo = Resource.fromResourceType(resourceTwo);
	}

	private String type = "Year_of_Plenty";
	private int playerIndex;
	private Resource resourceOne;
	private Resource resourceTwo;

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public Resource getResourceOne() {
		return resourceOne;
	}

	public void setResourceOne(Resource resourceOne) {
		this.resourceOne = resourceOne;
	}

	public Resource getResourceTwo() {
		return resourceTwo;
	}

	public void setResourceTwo(Resource resourceTwo) {
		this.resourceTwo = resourceTwo;
	}

}
