package swaggerModel;

import shared.model.DevCardList;
import shared.model.Player;
import shared.model.ResourceList;

public class SwaggerPlayer {
	private int cities; // How many cities this player has left to play
	private String color; // The color of this player
	private boolean discarded; // If the player has discarded in the current discard phase
	private int monuments; // How many monuments this player has played.
	private String name; // The name of the player
	private DevCardList newDevCards; //The development cards the player bought this turn
	private DevCardList oldDevCards; // The development cards the player had when the turn started
	private int playerIndex; // What place in the array is this player? 0-3
	private boolean playedDevCard; // Whether the player has played a dev card this turn
	private int playerID; // The unique playerID.
	private ResourceList resources; // The resource cards this player has
	private int roads; // How many roads this player has left to play
	private int settlements; // How many settlements this player has left to play
	private int soldiers; // How many solders this player has
	private int victoryPoints; // How many victory points this player has
	
	public SwaggerPlayer(Player player) {
		cities = player.getUnplacedCities();
		color = player.getColor();
		discarded = player.hasDiscarded();
		monuments = player.getMonuments();
		name = player.getName();
		newDevCards = player.getNewDevCards();
		oldDevCards = player.getOldDevCards();
		playerIndex = player.getPlayerIndex();
		playerID = player.getPlayerID();
		resources = player.getResources();
		roads = player.getUnplacedRoads();
		settlements = player.getUnplacedSettlements();
		soldiers = player.getSoldiers();
		victoryPoints = player.getVictoryPoints();
	}
	
	public int getCities() {
		return cities;
	}

	public String getColor() {
		return color;
	}

	public boolean isDiscarded() {
		return discarded;
	}

	public int getMonuments() {
		return monuments;
	}

	public String getName() {
		return name;
	}

	public DevCardList getNewDevCards() {
		return newDevCards;
	}

	public DevCardList getOldDevCards() {
		return oldDevCards;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public boolean isPlayedDevCard() {
		return playedDevCard;
	}

	public int getPlayerID() {
		return playerID;
	}

	public ResourceList getResources() {
		return resources;
	}

	public int getRoads() {
		return roads;
	}

	public int getSettlements() {
		return settlements;
	}

	public int getSoldiers() {
		return soldiers;
	}

	public int getVictoryPoints() {
		return victoryPoints;
	}
}
