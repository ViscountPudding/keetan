package shared.model;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

import shared.definitions.DevCardType;
import shared.model.gamemap.EdgeValue;
import shared.model.gamemap.Hex;
import shared.model.gamemap.Port;
import shared.model.gamemap.VertexValue;
import shared.model.locations.HexLocation;
import shared.model.pieces.City;
import shared.model.pieces.Road;
import shared.model.pieces.Settlement;

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
		boolean toReturn = false;
		if(offer.getSender() != currentPlayer && offer.getReceiver() != currentPlayer) {
			return false;
		}
		
		//Check if sender has enough resources
		ResourceList sOffer = offer.getReceiverReceives();
		ResourceList sResources = model.getPlayers()[offer.getSender()].getResources();
		if(sOffer.getBrick() <= sResources.getBrick()
				&& sOffer.getOre() <= sResources.getOre()
				&& sOffer.getSheep() <= sResources.getSheep()
				&& sOffer.getWheat() <= sResources.getWheat()
				&& sOffer.getWood() <= sResources.getWood()) {}
		else {
			return false;
		}
		
		//Check if receiver has enough resources
		ResourceList rOffer = offer.getSenderReceives();
		ResourceList rResources = model.getPlayers()[offer.getReceiver()].getResources();
		if(rOffer.getBrick() <= rResources.getBrick()
				&& rOffer.getOre() <= rResources.getOre()
				&& rOffer.getSheep() <= rResources.getSheep()
				&& rOffer.getWheat() <= rResources.getWheat()
				&& rOffer.getWood() <= rResources.getWood()) {}
		else {
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
			for(VertexValue vertex : location.getAdjacentVertices()) {
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
						Player player = model.getPlayers()[settle.getPlayerIndex()];
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
	
	public void LoseCardsFromDieRoll(Player player) {
		if(canLoseCardsFromRobber(player.getPlayerIndex())) {
			int endHandSize = player.getResources().getTotalCards()/2;
			Random generator = new Random();
			
			while(player.getResources().getTotalCards() > endHandSize) {
				int generatedNumber = generator.nextInt(5);
				switch(generatedNumber) {
				case 0:
					if(player.getResources().getBrick() > 0) {
						player.getResources().setBrick(player.getResources().getBrick() - 1);
						model.getBank().setBrick(model.getBank().getBrick() + 1);
					}
					break;
				case 1:
					if(player.getResources().getOre() > 0) {
						player.getResources().setOre(player.getResources().getOre() - 1);
						model.getBank().setOre(model.getBank().getOre() + 1);
					}
					break;
				case 2:
					if(player.getResources().getSheep() > 0) {
						player.getResources().setSheep(player.getResources().getSheep() - 1);
						model.getBank().setSheep(model.getBank().getSheep() + 1);
					}
					break;
				case 3:
					if(player.getResources().getWheat() > 0) {
						player.getResources().setWheat(player.getResources().getWheat() - 1);
						model.getBank().setWheat(model.getBank().getWheat() + 1);
					}
					break;
				case 4:
					if(player.getResources().getWood() > 0) {
						player.getResources().setWood(player.getResources().getWood() - 1);
						model.getBank().setWood(model.getBank().getWood() + 1);
					}
					break;
				}
			}
		}
	}
	
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
	
	public void PlaySoldier(Player player, HexLocation newRobberLocation, Player toStealFrom) {
		if(player.getOldDevCards().getSoldier() > 0) {
			model.getMap().setRobber(newRobberLocation);
			if(canLoseCardsFromRobber(toStealFrom.getPlayerIndex())) {
				LoseCardsFromPlayerRobber(player,toStealFrom);
			}
			player.getOldDevCards().setSoldier(player.getOldDevCards().getSoldier() - 1);
		}
	}
	
	public void PlayYearOfPlenty(Player player, Resource resource1, Resource resource2) {
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
			
			player.getOldDevCards().setYearOfPlenty(player.getOldDevCards().getYearOfPlenty() - 1);
		}
	}
	
	UpdateModel
	
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
