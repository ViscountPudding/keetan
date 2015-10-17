package shared.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import clientSide.exceptions.IllegalActionException;
import shared.model.gamemap.EdgeValue;
import shared.model.gamemap.Hex;
import shared.model.gamemap.Port;
import shared.model.gamemap.VertexValue;
import shared.model.locations.HexLocation;
import shared.model.message.MessageLine;
import shared.model.pieces.City;
import shared.model.pieces.Road;
import shared.model.pieces.Settlement;
import shared.transferClasses.*;

public class ModelFacade {

	private Model model;
	
	//SINGLETON!
	private static ModelFacade instance = null;
	
	private ModelFacade(Model model) {
		this.model = model;
	}
	
	/** The singleton generator for the ModelFacade
	 * @pre none
	 * @post If the model is already set, this function returns the already created instance
	 * @return the singleton of the ModelFacade
	 */
	public static ModelFacade createInstance(boolean randomHexes, boolean randomChits, boolean randomPorts, boolean loadGame, ArrayList<String> names) {
		if(instance == null) {
			instance = new ModelFacade(new Model(randomHexes, randomChits, randomPorts, loadGame, names));
		}
		
		return instance;
	}
	
	/**
	 * @pre none
	 * @post returns null if the Model is null
	 * @return
	 */
	public static ModelFacade getInstance() {
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
			if(vertex.getCity() == null && vertex.getSettlement() == null) {}
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
		return model.getBank().hasResource(resource_type, resource_amount);
	}
	/**
	* @pre Whenever
	* @param two player indexes
	* @return true if it is one of the player's turns AND both players have enough resources, false if otherwise
	* @post players may trade if possible
	*/
	public boolean canDomesticTrade(TradeOffer offer) {
		int currentPlayer = model.getTurnTracker().getCurrentPlayer();
		if(offer.getSender() != currentPlayer && offer.getReceiver() != currentPlayer) {
			return false;
		}
		
		//Check if sender has enough resources
		ResourceList sendOffer = offer.getReceiverReceives();
		ResourceList sendResources = model.getPlayers()[offer.getSender()].getResources();
		if(sendOffer.getBrick() > sendResources.getBrick()
				|| sendOffer.getOre() > sendResources.getOre()
				|| sendOffer.getSheep() > sendResources.getSheep()
				|| sendOffer.getWheat() > sendResources.getWheat()
				|| sendOffer.getWood() > sendResources.getWood()) {
			return false;
		}
		
		//Check if receiver has enough resources
		ResourceList receiveOffer = offer.getSenderReceives();
		ResourceList receiveResources = model.getPlayers()[offer.getReceiver()].getResources();
		if(receiveOffer.getBrick() > receiveResources.getBrick()
				|| receiveOffer.getOre() > receiveResources.getOre()
				|| receiveOffer.getSheep() > receiveResources.getSheep()
				|| receiveOffer.getWheat() > receiveResources.getWheat()
				|| receiveOffer.getWood() > receiveResources.getWood()) {
			return false;
		}
		
		//Passed all checks
		return true;
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
				tradeRatio = 2;
				foundPort = true;
			}
		}
		
		return player.getResources().hasResource(tradeResource, tradeRatio) && model.getBank().hasResource(desiredResource, 1);
	}
	
	/**
	* @pre Whenever
	* @param Player
	* @return true if the player has an available road and the location is a valid place to build, false if otherwise
	* @post Player may build the road if possible
	*/
	public boolean canBuildRoad(int playerIndex, EdgeValue edgeValue) {
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
			if(edgeValue.getRoad() != null) {
				return false;
			}
			for(EdgeValue edge : edgeValue.getAdjacentEdges()) {
				//Avoid null pointer exceptions
				if(edge.getRoad() == null) {}
				else if(edge.getRoad().getPlayerIndex() == playerIndex) {
					return true;
				}
			}
			//No adjacent roads are owned by the player.
			return false;
		}
		else {
			//It's the setup phase
			for(VertexValue vertex : edgeValue.getAdjacentVertices()) {
				if(vertex.getSettlement() == null) {}
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
	public boolean canBuildSettlement(int playerIndex, VertexValue vertexValue) {
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
		List<VertexValue> adjacents = model.getMap().getAdjacentVertices(vertexValue.getLocation());
		System.out.println(vertexValue);
		System.out.println(adjacents.get(0));
		System.out.println(adjacents.get(1));
		System.out.println(adjacents.get(2));
		for(VertexValue vertex : model.getMap().getAdjacentVertices(vertexValue.getLocation())) {
			if(vertex.getSettlement() != null || vertex.getCity() != null) {
				return false;
			}
		}
		//Is there an adjacent road?
		for(EdgeValue edge : vertexValue.getAdjacentEdges()) {
			if(edge.getRoad() == null) {}
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
		Hex robberHex = model.getMap().getHexes().get(robber);
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
	
	/**
	 * @pre Resources must be produceable
	 * @param location
	 * @post Players receive resources from the bank
	 */
	public void ProduceResources(HexLocation location) {
		if(canProduceResource(location)) {
			//Find total number of resources required
			int totalResources = 0;
			Hex hex = model.getMap().getHexes().get(location);
			for(VertexValue vert : hex.getAdjacentVertices()) {
				if(vert.getSettlement() != null){
					totalResources++;
				}
				else if(vert.getCity() != null) {
					totalResources += 2;
				}
			}
			if(canReceiveResource(totalResources,hex.getResource())) {
				removeFromBank(hex.getResource(), totalResources);
				for(VertexValue vert : hex.getAdjacentVertices()) {
					Settlement settle = vert.getSettlement();
					City city = vert.getCity();
					if(settle != null) {
						Player player = model.getPlayers()[settle.getPlayerIndex()];
						player.addResource(hex.getResource(), 1);
					}
					else if(city != null) {
						Player player = model.getPlayers()[city.getPlayerIndex()];
						player.addResource(hex.getResource(), 2);
					}
				}
			}
		}
	}
	
	/**
	 * @pre Players must be able to trade
	 * @param offer
	 * @post The trade takes place
	 */
	public void DomesticTrade(TradeOffer offer) {
		Player sender = model.getPlayers()[offer.getSender()];
		Player receiver = model.getPlayers()[offer.getReceiver()];
		if(canDomesticTrade(offer)) {
			//Find difference in the offers
			int brick = offer.getSenderReceives().getBrick() - offer.getReceiverReceives().getBrick();
			int ore = offer.getSenderReceives().getOre() - offer.getReceiverReceives().getOre();
			int sheep = offer.getSenderReceives().getSheep() - offer.getReceiverReceives().getSheep();
			int wheat = offer.getSenderReceives().getWheat() - offer.getReceiverReceives().getWheat();
			int wood = offer.getSenderReceives().getWood() - offer.getReceiverReceives().getWood();
			
			sender.getResources().setBrick(sender.getResources().getBrick() + brick);
			sender.getResources().setOre(sender.getResources().getOre() + ore);
			sender.getResources().setSheep(sender.getResources().getSheep() + sheep);
			sender.getResources().setWheat(sender.getResources().getWheat() + wheat);
			sender.getResources().setWood(sender.getResources().getWood() + wood);
			
			receiver.getResources().setBrick(receiver.getResources().getBrick() - brick);
			receiver.getResources().setOre(receiver.getResources().getOre() - ore);
			receiver.getResources().setSheep(receiver.getResources().getSheep() - sheep);
			receiver.getResources().setWheat(receiver.getResources().getWheat() - wheat);
			receiver.getResources().setWood(receiver.getResources().getWood() - wood);
		}
	}
	
	/**
	 * @pre Player must have enough of one resource (determined by the ports player owns) to trade with the bank
	 * @param player The player who's trading
	 * @param toSend What the player wants to send to the bank
	 * @param toReceive What the player wants to receive
	 * @post The trade is completed
	 */
	public void MaritimeTrade(Player player, Resource toSend, Resource toReceive) {
		if(canMaritimeTrade(player.getPlayerIndex(),toSend,toReceive)) {
			//Set tradeRatio. If the player has no applicable ports, this never gets overwritten.
			int tradeRatio = 4;
			boolean foundPort = false;
			for(Port port : player.getPorts()) {
				if(port.getResource() == null && foundPort == false) {
					tradeRatio = 3;
				}
				else if(port.getResource() == toSend && foundPort == false) {
					tradeRatio = 2;
					foundPort = true;
				}
			}
			
			player.removeResource(toSend, tradeRatio);
			player.addResource(toReceive, 1);
			removeFromBank(toReceive,1);
			addToBank(toSend, tradeRatio);
		}
	}
	
	/**
	 * @pre All conditions to build a road must be met
	 * @param player The player building a road
	 * @param location The road location
	 * @post The road is placed
	 */
	public void BuildRoad(Player player, EdgeValue location) {
		if(canBuildRoad(player.getPlayerIndex(),location)) {
			//Remove resources
			player.getResources().setBrick(player.getResources().getBrick()-1);
			player.getResources().setWood(player.getResources().getWood()-1);
			
			//Modify relevant road counts
			player.setUnplacedRoads(player.getUnplacedRoads()-1);
			Road road = new Road(player.getPlayerIndex());
			location.setRoad(road);
			player.addRoad(road);
		}
	}

	/**
	 * @pre All conditions to build a settlement must be met.
	 * @param player The player building a settlement
	 * @param vertex The vertex where the settlement will be built
	 * @post The settlement is placed
	 */
	public void BuildSettlement(Player player, VertexValue vertex) {
		if(canBuildSettlement(player.getPlayerIndex(),vertex)) {
			//Remove resources
			player.getResources().setBrick(player.getResources().getBrick()-1);
			player.getResources().setWood(player.getResources().getWood()-1);
			player.getResources().setWheat(player.getResources().getWheat()-1);
			player.getResources().setSheep(player.getResources().getSheep()-1);
			
			//Modify relevant settlement counts
			player.setUnplacedSettlements(player.getUnplacedSettlements()-1);
			Settlement settlement = new Settlement(player.getPlayerIndex());
			vertex.setSettlement(settlement);
			player.addSettlement(settlement);
		}
	}
	
	/**
	 * @pre All conditions to build a city must be met
	 * @param player The player building a city
	 * @param vertex The vertex where the city will be placed
	 * @post The city is placed
	 */
	public void BuildCity(Player player, VertexValue vertex) {
		if(canBuildCity(player.getPlayerIndex(),vertex)) {
			//Remove resources
			player.getResources().setWheat(player.getResources().getWheat()-2);
			player.getResources().setOre(player.getResources().getOre()-3);
			
			//Modify relevant settlement and city counts
			player.setUnplacedSettlements(player.getUnplacedSettlements()+1);
			player.setUnplacedCities(player.getUnplacedCities()-1);
			City city = new City(player.getPlayerIndex());
			player.removeSettlement(vertex.getSettlement());
			vertex.setSettlement(null);
			vertex.setCity(city);
			player.addCity(city);
		}
	}
	
	/**
	 * @pre The player must have enough resources to buy a card, and the bank must still have cards
	 * @param player
	 * @post The player buys a card from the bank
	 */
	public void BuyDevelopmentCard(Player player) {
		if(canBuyDevelopmentCard(player.getPlayerIndex())) {
			//Remove resources
			player.getResources().setOre(player.getResources().getOre()-1);
			player.getResources().setWheat(player.getResources().getWheat()-1);
			player.getResources().setSheep(player.getResources().getSheep()-1);
			
			//Update card lists
			boolean finished = false;
			Random generator = new Random();
			DevCardList undrawnCards = model.getUndrawnDevCards();
			while(!finished) {
				int generatedNumber = generator.nextInt(5);
				switch(generatedNumber) {
				case 0:
					if(undrawnCards.getMonopoly() > 0) {
						finished = true;
						undrawnCards.setMonopoly(undrawnCards.getMonopoly() - 1);
						player.getNewDevCards().setMonopoly(player.getNewDevCards().getMonopoly() + 1);
					}
					break;
				case 1:
					if(undrawnCards.getMonument() > 0) {
						finished = true;
						undrawnCards.setMonument(undrawnCards.getMonument() - 1);
						player.getNewDevCards().setMonument(player.getNewDevCards().getMonument() + 1);
					}
					break;
				case 2:
					if(undrawnCards.getRoadBuilding() > 0) {
						finished = true;
						undrawnCards.setRoadBuilding(undrawnCards.getRoadBuilding() - 1);
						player.getNewDevCards().setRoadBuilding(player.getNewDevCards().getRoadBuilding() + 1);
					}
					break;
				case 3:
					if(undrawnCards.getSoldier() > 0) {
						finished = true;
						undrawnCards.setSoldier(undrawnCards.getSoldier() - 1);
						player.getNewDevCards().setSoldier(player.getNewDevCards().getSoldier() + 1);
					}
					break;
				case 4:
					if(undrawnCards.getYearOfPlenty() > 0) {
						finished = true;
						undrawnCards.setYearOfPlenty(undrawnCards.getYearOfPlenty() - 1);
						player.getNewDevCards().setYearOfPlenty(player.getNewDevCards().getYearOfPlenty() + 1);
					}
					break;
				}
			}
		}
	}
	
	/**
	 * @pre The stealer player must have moved the robber via dice roll or knight card 
	 * @param stealer The player doing the stealing
	 * @param stealee The victim
	 * @post The stealer gains a card from the stealee
	 */
	public void LoseCardsFromPlayerRobber(Player stealer, Player stealee) {
		if(canLoseCardsFromDieRoll(stealee.getPlayerIndex())) {
			Random generator = new Random();
			boolean finished = false;
			while(!finished) {
				int generatedNumber = generator.nextInt(5);
				switch(generatedNumber) {
				case 0:
					if(stealee.getResources().getBrick() > 0) {
						finished = true;
						stealee.getResources().setBrick(stealee.getResources().getBrick() - 1);
						stealer.getResources().setBrick(stealer.getResources().getBrick() + 1);
					}
					break;
				case 1:
					if(stealee.getResources().getOre() > 0) {
						finished = true;
						stealee.getResources().setOre(stealee.getResources().getOre() - 1);
						stealer.getResources().setOre(stealer.getResources().getOre() + 1);
					}
					break;
				case 2:
					if(stealee.getResources().getSheep() > 0) {
						finished = true;
						stealee.getResources().setSheep(stealee.getResources().getSheep() - 1);
						stealer.getResources().setSheep(stealer.getResources().getSheep() + 1);
					}
					break;
				case 3:
					if(stealee.getResources().getWheat() > 0) {
						finished = true;
						stealee.getResources().setWheat(stealee.getResources().getWheat() - 1);
						stealer.getResources().setWheat(stealer.getResources().getWheat() + 1);
					}
					break;
				case 4:
					if(stealee.getResources().getWood() > 0) {
						finished = true;
						stealee.getResources().setWood(stealee.getResources().getWood() - 1);
						stealer.getResources().setWood(stealer.getResources().getWood() + 1);
					}
					break;
				}
			}
		}
	}
	
	/**
	 * @pre The dice have rolled a 7, and the player has 8 or more cards
	 * @param player The player discarding cards
	 * @param toDiscard The chosen cards to discard
	 * @throws IllegalActionException toDiscard has more cards of at least one type than does the player
	 * @post The player loses the discarded cards
	 */
	public void LoseCardsFromDieRoll(Player player, ResourceList toDiscard) throws IllegalActionException {
		if(canLoseCardsFromRobber(player.getPlayerIndex())) {
			ResourceList currResource = player.getResources();
			if(currResource.getBrick() >= toDiscard.getBrick()
					&& currResource.getOre() >= toDiscard.getOre()
					&& currResource.getSheep() >= toDiscard.getSheep()
					&& currResource.getWheat() >= toDiscard.getWheat()
					&& currResource.getWood() >= toDiscard.getWood()) {
				player.removeResource(Resource.BRICK, toDiscard.getBrick());
				player.removeResource(Resource.ORE, toDiscard.getOre());
				player.removeResource(Resource.SHEEP, toDiscard.getSheep());
				player.removeResource(Resource.WHEAT, toDiscard.getWheat());
				player.removeResource(Resource.WOOD, toDiscard.getWood());
				
				addToBank(Resource.BRICK,toDiscard.getBrick());
				addToBank(Resource.ORE,toDiscard.getOre());
				addToBank(Resource.SHEEP,toDiscard.getSheep());
				addToBank(Resource.WHEAT,toDiscard.getWheat());
				addToBank(Resource.WOOD,toDiscard.getWood());
			}
			else {
				throw new IllegalActionException();
			}
		}
	}
	
	/**
	 * @pre player must have a monopoly card
	 * @param player The player playing the card
	 * @param resource The chosen resource to monopolize
	 * @post player gains all other player's cards of type resource
	 */
	public void PlayMonopoly(Player player, Resource resource) {
		if(player.getOldDevCards().getMonopoly() > 0) {
			for(Player otherPlayer : model.getPlayers()) {
				if(player == otherPlayer) {
					continue; //Skip the rest of the loop
				}
				
				int ownedCards = 0;
				switch(resource) {
				case BRICK:
					ownedCards = otherPlayer.getResources().getBrick();
					break;
				case DESERT:
					break;
				case ORE:
					ownedCards = otherPlayer.getResources().getOre();
					break;
				case SHEEP:
					ownedCards = otherPlayer.getResources().getSheep();
					break;
				case WHEAT:
					ownedCards = otherPlayer.getResources().getWheat();
					break;
				case WOOD:
					ownedCards = otherPlayer.getResources().getWood();
					break;
				default:
					break;
				}
				
				otherPlayer.removeResource(resource, ownedCards);
				player.addResource(resource, ownedCards);
			}
			player.getOldDevCards().setMonopoly(player.getOldDevCards().getMonopoly() - 1);
		}
	}
	
	/**
	 * @pre player must have a roadbuilding card
	 * @param player The player playing the card
	 * @param location1 Location of the first road to place
	 * @param location2 Location of the second road to place
	 * @post New roads are placed
	 */
	public void PlayRoadBuilding(Player player, EdgeValue location1, EdgeValue location2) {
		if(player.getOldDevCards().getRoadBuilding() > 0) {
			if(canBuildRoad(player.getPlayerIndex(),location1)) {
				BuildRoad(player, location1);
			}
			if(canBuildRoad(player.getPlayerIndex(), location2)) {
				BuildRoad(player, location2);
			}
			
			player.getOldDevCards().setRoadBuilding(player.getOldDevCards().getRoadBuilding() - 1);
		}
	}
	
	/**
	 * @pre player must have a soldier card, and toStealFrom must have a building bordering newRobberLocation
	 * @param player The player playing the card
	 * @param newRobberLocation The new spot for the robber piece
	 * @param toStealFrom The player to steal a card from
	 * @post The robber is moved, and player receives a random card from toStealFrom 
	 */
	public void PlaySoldier(Player player, HexLocation newRobberLocation, Player toStealFrom) {
		if(player.getOldDevCards().getSoldier() > 0) {
			model.getMap().setRobber(newRobberLocation);
			if(canLoseCardsFromRobber(toStealFrom.getPlayerIndex())) {
				LoseCardsFromPlayerRobber(player,toStealFrom);
			}
			player.getOldDevCards().setSoldier(player.getOldDevCards().getSoldier() - 1);
		}
	}
	
	/**
	 * @pre player must have a YearOfPlenty card, and choose resources that the bank has available
	 * @param player The player playing the card
	 * @param resource1 The first resource to take from the bank
	 * @param resource2 The second resource to take from the bank
	 * @throws IllegalActionException 
	 * @post The player receives the two resources from the bank
	 */
	public void PlayYearOfPlenty(Player player, Resource resource1, Resource resource2) throws IllegalActionException {
		if(player.getOldDevCards().getYearOfPlenty() > 0) {
			if(resource1 == resource2 && model.getBank().hasResource(resource1, 2)) {
				removeFromBank(resource1,2);
				player.addResource(resource1, 2);
			}
			else if(model.getBank().hasResource(resource1, 1) && model.getBank().hasResource(resource2, 1)) {
				removeFromBank(resource1,1);
				removeFromBank(resource2,1);
				player.addResource(resource1, 1);
				player.addResource(resource2, 1);
			}
			else {
				throw new IllegalActionException();
			}
			
			player.getOldDevCards().setYearOfPlenty(player.getOldDevCards().getYearOfPlenty() - 1);
		}
	}
	
	/**
	 * @pre whenever
	 * @param model The new client model
	 * @post replaces the old model with the new one
	 */
	public void updateModel(Model model) {
		if (model.getVersion() >= this.model.getVersion()) {
			this.model = model;
		}
	}
	
	public int getModelVersion() {
		return this.model.getVersion();
	}
	
	/**
	 * @pre whenever
	 * @param transferObject
	 * @throws IllegalActionException
	 * @post Performs actions based on which transferObject was given.
	 */
	public void updateModel(Object transferObject) throws IllegalActionException {
		if(transferObject == null) {}
		else if(transferObject instanceof AcceptTrade) {
			AcceptTrade aTrade = (AcceptTrade)transferObject;
			if(aTrade.getWillAccept()) {
				DomesticTrade(model.getCurrentTradeOffer());
			}
			model.setCurrentTradeOffer(null);
		}
		else if(transferObject instanceof AddAIRequest) {
			
		}
		else if(transferObject instanceof BuildCity) {
			BuildCity bCity = (BuildCity)transferObject;
			BuildCity(model.getPlayers()[bCity.getPlayerIndex()], bCity.getSpotOne());
		}
		else if(transferObject instanceof BuildRoad) {
			BuildRoad bRoad = (BuildRoad)transferObject;
			EdgeValue eValue = model.getMap().getEdges().get(bRoad.getRoadLocation());
			BuildRoad(model.getPlayers()[bRoad.getPlayerIndex()],eValue);
		}
		else if(transferObject instanceof BuildSettlement) {
			BuildSettlement bSettlement = (BuildSettlement)transferObject;
			BuildSettlement(model.getPlayers()[bSettlement.getPlayerIndex()],bSettlement.getSpotOne());
		}
		else if(transferObject instanceof BuyDevCard) {
			BuyDevCard bdCard = (BuyDevCard)transferObject;
			BuyDevelopmentCard(model.getPlayers()[bdCard.getPlayerIndex()]);
		}
		else if(transferObject instanceof ChangeLogLevelRequest) {
			
		}
		else if(transferObject instanceof DiscardCards) {
			DiscardCards dCards = (DiscardCards)transferObject;
			LoseCardsFromDieRoll(model.getPlayers()[dCards.getPlayerIndex()], dCards.getDiscardedCards());
		}
		else if(transferObject instanceof FinishTurn) {
			model.getTurnTracker().endPlayerTurn();
		}
		else if(transferObject instanceof ListOfCommands) {
			ListOfCommands list = (ListOfCommands)transferObject;
//Not implemented
		}
		else if(transferObject instanceof MaritimeTrade) {
			MaritimeTrade mTrade = (MaritimeTrade)transferObject;
			MaritimeTrade(model.getPlayers()[mTrade.getPlayerIndex()],mTrade.getOutputResource(),mTrade.getInputResource());
		}
		else if(transferObject instanceof Monopoly) {
			Monopoly monopoly = (Monopoly)transferObject;
			PlayMonopoly(model.getPlayers()[monopoly.getPlayerIndex()],monopoly.getResource());
		}
		else if(transferObject instanceof Monument) {
			Monument monument = (Monument)transferObject;
			Player player = model.getPlayers()[monument.getPlayerIndex()];
			player.setVictoryPoints(player.getVictoryPoints() + 1);
		}
		else if(transferObject instanceof OfferTrade) {
			OfferTrade oTrade = (OfferTrade)transferObject;
			ResourceList senderReceives = new ResourceList(0,0,0,0,0);
			ResourceList receiverReceives = new ResourceList(0,0,0,0,0);
			int brick = oTrade.getOffer().getBrick();
			if(brick > 0) {
				senderReceives.setBrick(brick);
			}
			else {
				receiverReceives.setBrick(brick);
			}
			
			int ore = oTrade.getOffer().getOre();
			if(ore > 0) {
				senderReceives.setOre(ore);
			}
			else {
				receiverReceives.setOre(ore);
			}
			
			int sheep = oTrade.getOffer().getSheep();
			if(sheep > 0) {
				senderReceives.setSheep(sheep);
			}
			else {
				receiverReceives.setSheep(sheep);
			}
			
			int wheat = oTrade.getOffer().getWheat();
			if(wheat > 0) {
				senderReceives.setWheat(wheat);
			}
			else {
				receiverReceives.setWheat(wheat);
			}
			
			int wood = oTrade.getOffer().getWood();
			if(wood > 0) {
				senderReceives.setWood(wood);
			}
			else {
				receiverReceives.setWood(wood);
			}
			
			model.setCurrentTradeOffer(new TradeOffer(oTrade.getPlayerIndex(), oTrade.getReciever(), senderReceives, receiverReceives));
		}
		else if(transferObject instanceof RoadBuilding) {
			RoadBuilding rBuild = (RoadBuilding)transferObject;
			EdgeValue value1 = model.getMap().getEdges().get(rBuild.getSpotOne());
			EdgeValue value2 = model.getMap().getEdges().get(rBuild.getSpotTwo());
			PlayRoadBuilding(model.getPlayers()[rBuild.getPlayerIndex()], value1, value2);
		}
		else if(transferObject instanceof RobPlayer) {
			RobPlayer rPlayer = (RobPlayer)transferObject;
			model.getMap().setRobber(rPlayer.getLocation());
			LoseCardsFromPlayerRobber(model.getPlayers()[rPlayer.getPlayerIndex()], model.getPlayers()[rPlayer.getVictimIndex()]);
		}
		else if(transferObject instanceof RollNumber) {
			RollNumber rNumber = (RollNumber)transferObject;
			if(rNumber.getNumber() != 7) {
				for(Map.Entry<HexLocation, Hex> entry : model.getMap().getHexes().entrySet()) {
					if(entry.getValue().getDiceNumber() == rNumber.getNumber()) {
						ProduceResources(entry.getKey());
					}
				}
			}
		}
		else if(transferObject instanceof SendChat) {
			SendChat sChat = (SendChat)transferObject;
			model.getChat().addLine(new MessageLine(sChat.getContent(), model.getPlayers()[sChat.getPlayerIndex()].getName()));
		}
		else if(transferObject instanceof Soldier) {
			Soldier soldier = (Soldier)transferObject;
			PlaySoldier(model.getPlayers()[soldier.getPlayerIndex()], soldier.getLocation(), model.getPlayers()[soldier.getVictimIndex()]);
		}
		else if(transferObject instanceof UserCredentials) {
			UserCredentials uCred = (UserCredentials)transferObject;
//Not implemented
		}
		else if(transferObject instanceof YearOfPlenty) {
			YearOfPlenty yOPlenty = (YearOfPlenty)transferObject;
			PlayYearOfPlenty(model.getPlayers()[yOPlenty.getPlayerIndex()], yOPlenty.getResourceOne(), yOPlenty.getResourceTwo());
		}
	}
	
	private void addToBank(Resource resource, int amount) {
		ResourceList bank = model.getBank();
		switch(resource) {
		case BRICK:
			bank.setBrick(bank.getBrick()+amount);
			break;
		case DESERT:
			break;
		case ORE:
			bank.setOre(bank.getOre()+amount);
			break;
		case SHEEP:
			bank.setSheep(bank.getSheep()+amount);
			break;
		case WHEAT:
			bank.setWheat(bank.getWheat()+amount);
			break;
		case WOOD:
			bank.setWood(bank.getWood()+amount);
			break;
		default:
			break;
		}
	}
	
	private void removeFromBank(Resource resource, int amount) {
		ResourceList bank = model.getBank();
		switch(resource) {
		case BRICK:
			bank.setBrick(bank.getBrick()-amount);
			break;
		case DESERT:
			break;
		case ORE:
			bank.setOre(bank.getOre()-amount);
			break;
		case SHEEP:
			bank.setSheep(bank.getSheep()-amount);
			break;
		case WHEAT:
			bank.setWheat(bank.getWheat()-amount);
			break;
		case WOOD:
			bank.setWood(bank.getWood()-amount);
			break;
		default:
			break;
		}
	}
}
