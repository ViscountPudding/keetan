package shared.model;

import java.util.Iterator;
import java.util.Map.Entry;

import shared.model.gamemap.EdgeValue;
import shared.model.gamemap.Hex;
import shared.model.gamemap.Port;
import shared.model.gamemap.VertexValue;
import shared.model.locations.EdgeLocation;
import shared.model.locations.HexLocation;

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
		Hex thisHex = null;
		Iterator<Entry<HexLocation, Hex>> hexes = model.getMap().getHexes().entrySet().iterator();
		while (hexes.hasNext()) {
			Entry<HexLocation, Hex> hex = hexes.next();
			if(hex.getKey().equals(location)){
				thisHex = hex.getValue();
			}
		}
		for(VertexValue vertex : thisHex.getAdjacentVertices()) {
			if(vertex.getCity() == null && vertex.getSettlement() == null) {
				continue;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	/**
	* @pre Whenever
	* @param Number of resources to be received
	* @return true if the bank has enough for all entitled players, false if otherwise
	* @post players may receive their resources
	*/
	public boolean canReceiveResource(int resource_amount, Resource resource_type) {
		switch(resource_type) {
		case WOOD:
			return model.getBank().getWood() >= resource_amount;
		case BRICK:
			return model.getBank().getBrick() >= resource_amount;
		case SHEEP:
			return model.getBank().getSheep() >= resource_amount;
		case WHEAT:
			return model.getBank().getWheat() >= resource_amount;
		case ORE:
			return model.getBank().getOre() >= resource_amount;
		default:
			return false;
		}
	}
	/**
	* @pre Whenever
	* @param two player indexes
	* @return true if it is one of the player's turns, false if otherwise
	* @post players may trade if possible
	*/
	public boolean canDomesticTrade(int playerIndexOne, int playerIndexTwo) {
		int currentPlayer = model.getTurnTracker().getCurrentPlayer();
		return playerIndexOne == currentPlayer || playerIndexTwo == currentPlayer;
	}
	
	/**
	* @pre Whenever
	* @param Player, resource to trade, resource desired
	* @return true if it is the player's turn and they and the bank have enough resources, false if otherwise
	* @post Player may trade with boat if possible
	*/
	public boolean canMaritimeTrade(int playerIndex, Resource tradeResource, Resource desiredResource) {
		Player player = model.getPlayers()[playerIndex];
		
		//Set tradeRatio. If the player has no applicable ports, this never gets overwritten.
		int tradeRatio = 4;
		boolean foundPort = false;
		for(Port port : player.getPorts()) {
			if(port.getResource() == null && foundPort == false) {
				tradeRatio = 3;
			}
			else if(port.getResource() == tradeResource && foundPort == false) {
				//This is a best-case scenario, so we break to keep from overwriting it.
				tradeRatio = 2;
				foundPort = true;
			}
		}
		
		switch(tradeResource) {
		case SHEEP:
			if(player.getResources().getSheep() < tradeRatio) {
				return false;
			}
			return model.getBank().getSheep() > 0;
		case WOOD:
			if(player.getResources().getWood() < tradeRatio) {
				return false;
			}
			return model.getBank().getWood() > 0;
		case BRICK:
			if(player.getResources().getBrick() < tradeRatio) {
				return false;
			}
			return model.getBank().getBrick() > 0;
		case ORE:
			if(player.getResources().getOre() < tradeRatio) {
				return false;
			}
			return model.getBank().getOre() > 0;
		case WHEAT:
			if(player.getResources().getWheat() < tradeRatio) {
				return false;
			}
			return model.getBank().getWheat() > 0;
		default:
			return false;
		}
	}
	
	/**
	* @pre Whenever
	* @param Player
	* @return true if the player has an available road and the location is a valid place to build, false if otherwise
	* @post Player may build the road if possible
	*/
	public boolean canBuildRoad(int playerIndex, EdgeValue location) {
		if(model.getTurnTracker().getCurrentTurn() != 0) {
			Player player = model.getPlayers()[playerIndex];
			//Check for unplaced roads
			if(player.getUnplacedRoads() == 0) {
				return false;
			}
			//Check for resources
			if(player.getResources().getBrick() == 0 || player.getResources().getWood() == 0) {
				return false;
			}
			//Check for existing road
			if(location.getRoad() != null) {
				return false;
			}
			for(EdgeValue edge : location.getAdjacentEdges()) {
				//Avoid null pointer exceptions
				if(edge.getRoad() == null) {
					continue;
				}
				else if(edge.getRoad().getPlayerIndex() == playerIndex) {
					return true;
				}
			}
			//No adjacent roads are owned by the player.
			return false;
		}
		else {
			//It's the setup phase
			for(VertexValue vertex : location.getAdjacentVertices()) {
				if(vertex.getSettlement() == null) {
					continue;
				}
				else if(vertex.getSettlement().getPlayerIndex() == playerIndex) {
					return true;
				}
			}
			//There's not an adjacent settlement
			return false;
		}
	}
	
	
	/**
	* @pre Whenever
	* @param Player
	* @return true if the player has an available settlement and the location is a valid place to build
	* @post Player may build the settlement if possible
	*/
	public boolean canBuildSettlement(int playerIndex, VertexValue location) {
		Player player = model.getPlayers()[playerIndex];
		//Check for unplaced settlements
		if(player.getUnplacedSettlements() == 0) {
			return false;
		}
		//Does player have enough resources?
		ResourceList rList = player.getResources();
		if(rList.getBrick() == 0 || rList.getSheep() == 0 || rList.getWheat() == 0 || rList.getWood() == 0) {
			return false;
		}
		//Is the distance rule obeyed?
		for(VertexValue vertex : location.getAdjacentVertices()) {
			if(vertex.getSettlement() != null || vertex.getCity() != null) {
				return false;
			}
		}
		//Is there an adjacent road?
		for(EdgeValue edge : location.getAdjacentEdges()) {
			if(edge.getRoad() == null) {
				continue;
			}
			else if(edge.getRoad().getPlayerIndex() == playerIndex) {
				return true;
			}
		}
		return false;
	}
	
	/**
	* @pre Whenever
	* @param Player
	* @return true if the player has an available city and the location has an existing settlement, false if otherwise
	* @post Player may build the city if possible
	*/
	public boolean canBuildCity(int playerIndex, VertexValue location) {
		Player player = model.getPlayers()[playerIndex];
		if(player.getUnplacedCities() == 0) {
			return false;
		}
		else if(player.getResources().getOre() < 3 || player.getResources().getWheat() < 2) {
			return false;
		}
		else if(location.getSettlement() == null) {
			return false;
		}
		else if(location.getSettlement().getPlayerIndex() == playerIndex){
			return true;
		}
		else {
			return false;
		}
	}	
	
	/**
	 * @pre Whenever
	 * @param playerIndex
	 * @return true if it is the player's turn and they have sufficient resources for the development card
	 * @post player may buy a development card if possible
	 */
	public boolean canBuyDevelopmentCard(int playerIndex) {
		ResourceList rList = model.getPlayers()[playerIndex].getResources();
		if(model.getTurnTracker().getCurrentPlayer() != playerIndex) {
			return false;
		}
		else if(rList.getOre() == 0 || rList.getSheep() == 0 || rList.getWheat() == 0) {
			return false;
		}
		else if(model.getUndrawnDevCards().getTotalCards() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * @pre Whenever
	 * @param playerIndex
	 * @return true if the player has more than 7 cards, false if otherwise
	 * @post player is able to lose cards to die roll if they have more than 7 cards
	 */
	public boolean canLoseCardsFromDieRoll(int playerIndex) {
		return model.getPlayers()[playerIndex].getResources().getTotalCards() > 7;
	}
	
	/**
	 * @pre Whenever
	 * @param playerIndex
	 * @return true if player has more than zero cards, and has a settlement/city adjacent to robber hex, false if otherwise
	 * @post player can be robbed
	 */
	public boolean canLoseCardsFromRobber(int playerIndex) {
		if(model.getPlayers()[playerIndex].getResources().getTotalCards() == 0) {
			return false;
		}
		HexLocation robber = model.getMap().getRobber();
		Hex robberHex = null;
		Iterator<Entry<HexLocation, Hex>> hexes = model.getMap().getHexes().entrySet().iterator();
		while (hexes.hasNext()) {
			Entry<HexLocation, Hex> hex = hexes.next();
			if(hex.getKey().equals(robber)){
				robberHex = hex.getValue();
			}
		}
		for(VertexValue vertex : robberHex.getAdjacentVertices()) {
			if(vertex.getSettlement() != null && vertex.getSettlement().getPlayerIndex() == playerIndex) {
				return true;
			}
			else if(vertex.getCity() != null && vertex.getCity().getPlayerIndex() == playerIndex) {
				return true;
			}
		}
		
		return false;
	} 

	/**
	 * @pre Whenever
	 * @param playerIndex
	 * @return true if player has 10 or more victory points and it is their turn, false if otherwise
	 * @post player can be the WINNER!
	 */
	public boolean canWin(int playerIndex) {
		return model.getTurnTracker().getCurrentPlayer() == playerIndex && model.getPlayers()[playerIndex].getVictoryPoints() > 9;
	}
	
}
