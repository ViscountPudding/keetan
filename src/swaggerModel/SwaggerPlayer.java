package swaggerModel;

import shared.model.DevCardList;
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
	private int roads;
	private int settlements;
	private int soldiers;
	private int victoryPoints;
}
