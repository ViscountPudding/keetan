package model;

import model.gamemap.HexLocation;

public class ModelFacade {

	private ClientModel model;
	
	public ModelFacade() {
		
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
	* @return true if the player has an available road and a place to build a road, false if otherwise
	* @post Player may build the road if possible
	*/
	public boolean canBuildRoad(int playerIndex) {
		return true;
	}
	
	
	/**
	* @pre Whenever
	* @param Player
	* @return true if the player has an available settlement and a place to build a settlement
	* @post Player may build the settlement if possible
	*/
	public boolean canBuildSettlement(int playerIndex) {
		return true;
	}
	
	/**
	* @pre Whenever
	* @param Player
	* @return true if the player has an available city and settlement to put the city on, false if otherwise
	* @post Player may build the city if possible
	*/
	public boolean canBuildCity(int playerIndex) {
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
