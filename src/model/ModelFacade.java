package model;

import model.gamemap.HexLocation;
import model.gamemap.VertexObject;
import model.gamemap.EdgeLocation;

public class ModelFacade {

	private ClientModel model = ClientModel.getInstance();
	
	//SINGLETON!
	private static ModelFacade instance = null;
	
	private ModelFacade() {
		
	}
	
	/** The singleton generator for the ModelFacade
	 * @pre the game must be starting (there must be a server model to interact with)
	 * @return the singleton of the ModelFacade
	 */
	public static ModelFacade GetInstance() {
		if(instance == null) {
			instance = new ModelFacade();
		}
		
		return instance;
	}
	
	/**
	* @pre Whenever
	* @param Location of hex
	* @return true if there is a settlement or city adjacent to that location, false if otherwise
	* @post players with adjacent settlements/cities may get resources if the bank has enough
	*/
	public boolean canProduceResource(HexLocation location) {
		return true;
	}
	
	/**
	* @pre Whenever
	* @param Number of resources to be received
	* @return true if the bank has enough for all entitled players, false if otherwise
	* @post players may receive their resources
	*/
	public boolean canReceiveResource(int resource_amount) {
		return true;
	}
	/**
	* @pre Whenever
	* @param two player indexes
	* @return true if it is one of the player's turns, false if otherwise
	* @post players may trade if possible
	*/
	public boolean canDomesticTrade(int playerIndexOne, int playerIndexTwo) {
		return true;
	}
	
	/**
	* @pre Whenever
	* @param Player, resource to trade, resource desired
	* @return true if it is the player's turn and they and the bank have enough resources, false if otherwise
	* @post Player may trade with boat if possible
	*/
	public boolean canMaritimeTrade(int playerIndex, String tradeResource, String desiredResource) {
		return true;
	}
	
	/**
	* @pre Whenever
	* @param Player
	* @return true if the player has an available road and the location is a valid place to build, false if otherwise
	* @post Player may build the road if possible
	*/
	public boolean canBuildRoad(int playerIndex, EdgeLocation location) {
		return true;
	}
	
	
	/**
	* @pre Whenever
	* @param Player
	* @return true if the player has an available settlement and the location is a valid place to build
	* @post Player may build the settlement if possible
	*/
	public boolean canBuildSettlement(int playerIndex, VertexObject location) {
		return true;
	}
	
	/**
	* @pre Whenever
	* @param Player
	* @return true if the player has an available city and the location has an existing settlement, false if otherwise
	* @post Player may build the city if possible
	*/
	public boolean canBuildCity(int playerIndex, VertexObject location) {
		return true;
	}	
	
	/**
	 * @pre Whenever
	 * @param playerIndex
	 * @return true if it is the player's turn and they have sufficient resources for the development card
	 * @post player may buy a development card if possible
	 */
	public boolean canBuyDevelopmentCard(int playerIndex) {
		return true;
	}
	
	/**
	 * @pre Whenever
	 * @param playerIndex
	 * @return true if the player has more than 7 cards, false if otherwise
	 * @post player is able to lose cards to die roll if they have more than 7 cards
	 */
	public boolean canLoseCardsFromDieRoll(int playerIndex) {
		return true;
	}
	
	/**
	 * @pre Whenever
	 * @param playerIndex
	 * @return true if player has more than zero cards, and has a settlement/city adjacent to robber hex, false if otherwise
	 * @post player can be robbed
	 */
	public boolean canLoseCardsFromRobber(int playerIndex) {
		return true;
	}

	/**
	 * @pre Whenever
	 * @param playerIndex
	 * @return true if player has 10 or more victory points and it is their turn, false if otherwise
	 * @post player can be the WINNER!
	 */
	public boolean canWin(int playerIndex) {
		return true;
	}
	
}
