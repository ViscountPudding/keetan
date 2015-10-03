package shared.transferClasses;

import shared.model.Resource;

/**
 * Plays a 'Monopoly' card from your hand to monopolize the specified resource
 * @param playerIndex the player playing the card
 * @param resource the resource being monopolized
 * @author willvdb
 *
 */

public class Monopoly {

	public Monopoly(int playerIndex, Resource resource) {
		this.playerIndex = playerIndex;
		this.resource = resource;
	}

	private String type = "Monopoly";
	private int playerIndex;
	private Resource resource;

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResourceOne(Resource resource) {
		this.resource = resource;
	}

}
