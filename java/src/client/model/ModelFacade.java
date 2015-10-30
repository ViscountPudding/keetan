package client.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import client.data.GameInfo;
import client.data.PlayerInfo;
import shared.definitions.ResourceType;
import shared.definitions.VertexDirection;


public class ModelFacade {
	private static final ClientModel model = new ClientModel();
	
	/**
	 * Updates the model if the given model's version is newer.
	 * @pre none
	 * @post If the ModelFacade's model is not yet initialized, or the given model has a higher version,
	 * the model is updated to the given model.
	 * @param model - the model to check for an update
	 */
	public static void updateModel(TransferModel lump) {
		if (getModelVersion() < lump.getVersion()) {
			model.update(lump);
		}
	}
	
	public static List<Hex> getHexes() {
		return model.getDataLump().getMap().getHexes();
	}
	
	
	/**
	* @pre ModelFacade must be initialized and must have a current valid model
	* @param playerIndex - the index of the player in question
	* @param location - location of hex
	* @return true if the given player owns a settlement or city adjacent to that location, false if otherwise
	* @post see return
	*/
	public static boolean canProduceResource(int playerIndex, HexLocation location) {
		Hex thisHex = null;
		Iterator<Entry<HexLocation, Hex>> hexes = model.getHexes().entrySet().iterator();
		while (hexes.hasNext()) {
			Entry<HexLocation, Hex> hex = hexes.next();
			if(hex.getKey().equals(location)){
				thisHex = hex.getValue();
			}
		}
		
		int x = thisHex.getLocation().getX();
		int y = thisHex.getLocation().getY();
	
		VertexLocation west = new VertexLocation(x, y, VertexDirection.West);
		VertexLocation northwest = new VertexLocation(x, y, VertexDirection.NorthWest);
		VertexLocation northeast = new VertexLocation(x, y, VertexDirection.NorthEast);
		VertexLocation east = new VertexLocation(x, y, VertexDirection.East);
		VertexLocation southeast = new VertexLocation(x, y, VertexDirection.SouthEast);
		VertexLocation southwest = new VertexLocation(x, y, VertexDirection.SouthWest);
		
		List<VertexObject> settlements = new ArrayList<VertexObject>();
		
		settlements.add(model.getSettlements().get(west.getNormalizedLocation()));
		settlements.add(model.getSettlements().get(northwest.getNormalizedLocation()));
		settlements.add(model.getSettlements().get(northeast.getNormalizedLocation()));
		settlements.add(model.getSettlements().get(east.getNormalizedLocation()));
		settlements.add(model.getSettlements().get(southeast.getNormalizedLocation()));
		settlements.add(model.getSettlements().get(southwest.getNormalizedLocation()));
		
		List<VertexObject> cities = new ArrayList<VertexObject>();
		
		cities.add(model.getCities().get(west.getNormalizedLocation()));
		cities.add(model.getCities().get(northwest.getNormalizedLocation()));
		cities.add(model.getCities().get(northeast.getNormalizedLocation()));
		cities.add(model.getCities().get(east.getNormalizedLocation()));
		cities.add(model.getCities().get(southeast.getNormalizedLocation()));
		cities.add(model.getCities().get(southwest.getNormalizedLocation()));
		
		for(VertexObject settlement : settlements) {
			if(settlement != null) {
				if(settlement.getOwner() == playerIndex) {
					return true; //HOORAY!
				}
			}
		}
		
		for(VertexObject city : cities) {
			if(city != null) {
				if(city.getOwner() == playerIndex) {
					return true; //HOORAY!
				}
			}
		}
		
		return false; //Awwww... found no settlements or cities for you... how sad....
	}
	
	public static boolean canReceiveResource(int resource_amount, ResourceType resource_type) {
		return model.getDataLump().getBank().hasResource(resource_type, resource_amount);
	}
	
	public static int getModelVersion() {
		return model.getDataLump().getVersion();
	}

	public static int whoseTurnIsItAnyway() {
		return model.getDataLump().getTurnTracker().getCurrentPlayer();
	}

	public static boolean canBuildSettlement(int playerIndex, VertexLocation vertLoc) {
		
		if(playerIndex != ModelFacade.whoseTurnIsItAnyway()) {
			return false;
		}
		else if(model.getSettlements().get(vertLoc.getNormalizedLocation()) != null) {
			return false;
		}
		
		else if(model.getCities().get(vertLoc.getNormalizedLocation()) != null) {
			return false;
		}
		
		List<VertexLocation> nearbyVertices = model.getAdjacentVertices(vertLoc);
		
		for(VertexLocation point : nearbyVertices) {
			if(model.getSettlements().get(point.getNormalizedLocation()) != null) {
				return false;
			}
			else if(model.getCities().get(point.getNormalizedLocation()) != null) {
				return false;
			}
		}
		
		ResourceList rList = model.getDataLump().getPlayers().get(playerIndex).getResources();
		
		if(rList.getBrick() == 0 || rList.getSheep() == 0 || rList.getWheat() == 0 || rList.getWood() == 0) {
			return false;
		}
		
		List<EdgeLocation> nearbyEdges = model.getNearbyEdges(vertLoc);
		
		for(EdgeLocation face : nearbyEdges) {
			if(model.getRoads().get(face.getNormalizedLocation()) != null) {
				if(model.getRoads().get(face.getNormalizedLocation()).getOwner() == playerIndex){
					return true;
				}
			}
		}
		
		return false;
	}

	public static boolean canBuildCity(int playerIndex, VertexLocation vertLoc) {
		// TODO Auto-generated method stub
		
		if(playerIndex != ModelFacade.whoseTurnIsItAnyway()) {
			return false;
		}
		
		ResourceList rList = model.getDataLump().getPlayers().get(playerIndex).getResources();
		
		if(rList.getOre() < 3 || rList.getWheat() < 2) {
			return false;
		}
		
		if(model.getSettlements().get(vertLoc) != null) {
			if(model.getSettlements().get(vertLoc.getNormalizedLocation()).getOwner() == playerIndex) {
				return true;
			}
			
			else {
				return false;
			}
		}

		return false;
	}

	public static boolean canBuildRoad(int playerIndex, EdgeLocation edgeLoc) {
		// TODO Auto-generated method stub
		
		if(playerIndex != ModelFacade.whoseTurnIsItAnyway()) {
			return false;
		}
		
		ResourceList rList = model.getDataLump().getPlayers().get(playerIndex).getResources();
		
		if(rList.getBrick() == 0 || rList.getWood() == 0) {
			return false;
		}
		
		List<VertexLocation> points = model.getNearbyVertices(edgeLoc);
		
		for(VertexLocation point : points) {
			if(model.getSettlements().get(point.getNormalizedLocation()) != null) {
				if(model.getSettlements().get(point.getNormalizedLocation()).getOwner() == playerIndex) {
					return true;
				}
			}
			else if(model.getCities().get(point.getNormalizedLocation()) != null) {
				if(model.getCities().get(point.getNormalizedLocation()).getOwner() == playerIndex) {
					return true;
				}
			}
		}
		
		List<EdgeLocation> nearbyEdges = model.getAdjacentEdges(edgeLoc);
		
		for(EdgeLocation nearbyEdge : nearbyEdges) {
			if(model.getRoads().get(nearbyEdge.getNormalizedLocation()) != null) {
				if(model.getRoads().get(nearbyEdge.getNormalizedLocation()).getOwner() == playerIndex) {
					return true;
				}
			}
		}
		
		return false;
	}

	public boolean canDomesticTrade(TradeOffer offer) {
		int currentPlayer = model.getDataLump().getTurnTracker().getCurrentPlayer();
		if(offer.getSender() != currentPlayer && offer.getReceiver() != currentPlayer) {
			return false;
		}
		
		//Check if sender has enough resources
		ResourceList sendOffer = offer.getOffer();
		ResourceList sendResources = model.getDataLump().getPlayers().get(offer.getSender()).getResources();
		if(sendOffer.getBrick() > sendResources.getBrick()
				|| sendOffer.getOre() > sendResources.getOre()
				|| sendOffer.getSheep() > sendResources.getSheep()
				|| sendOffer.getWheat() > sendResources.getWheat()
				|| sendOffer.getWood() > sendResources.getWood()) {
			return false;
		}
		
		//THIS DOESN'T ACTUALLY WORK. Need to split offer into the send (negatives) and receives (positives)
		
		//Check if receiver has enough resources
		ResourceList receiveOffer = offer.getOffer();
		ResourceList receiveResources = model.getDataLump().getPlayers().get(offer.getReceiver()).getResources();
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
	
	public boolean canMaritimeTrade(int playerIndex, ResourceType tradeResource, ResourceType desiredResource) {
		Player player = model.getDataLump().getPlayers().get(playerIndex);
		
		//int tradeRatio = getTradeRatio(playerIndex, tradeResource);
		
		int tradeRatio = 0; //Need to figure this out as well
		
		return player.getResources().hasResource(tradeResource, tradeRatio) && model.getDataLump().getBank().hasResource(desiredResource, 1);
	}
	
	public boolean canBuyDevelopmentCard(int playerIndex) {
		ResourceList rList = model.getDataLump().getPlayers().get(playerIndex).getResources();
		if(model.getDataLump().getTurnTracker().getCurrentPlayer() != playerIndex) {
			return false;
		}
		else if(rList.getOre() == 0 || rList.getSheep() == 0 || rList.getWheat() == 0) {
			return false;
		}
		else if(model.getDataLump().getDeck().getTotalCards() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean canLoseCardsFromDieRoll(int playerIndex) {
		return model.getDataLump().getPlayers().get(playerIndex).getResources().getTotalCards() > 7;
	}
	
	public boolean canWin(int playerIndex) {
		return model.getDataLump().getTurnTracker().getCurrentPlayer() == playerIndex && 
				model.getDataLump().getPlayers().get(playerIndex).getVictoryPoints() > 9;
	}
	
	/**
	 * Queries the hex location of the robber.
	 * @pre ModelFacade.updateModel() must have been called with a valid model
	 * @post see return
	 * @return the hex location of the robber
	 */
	public static HexLocation findRobber() {
		return model.getDataLump().getMap().getRobber();
	}

	/**
	 * This function sets the data for the game the user is interacting with, namely
	 * <ul>
	 * <li>A game the user is trying to join.
	 * <li>A game that the user is playing in.
	 * <ul>
	 * @pre model must not be null
	 * @post game info is set to given
	 * @param game - the game info to store
	 */
	public static void setGameInfo(GameInfo game) {
		model.setGameInfo(game);
	}
	public static GameInfo getGameInfo() {
		return model.getGameInfo();
	}
	
	/**
	 * This function clears the current game info the user is trying to interact with.
	 * @pre model must not be null
	 * @post game info is set to null
	 */
	public static void clearGameInfo() {
		model.setGameInfo(null);
	}
	
	/**
	 * This function sets the data for the player info of the user
	 * @pre model must not be null
	 * @post player info is set to given
	 * @param player - the player info to store
	 */
	public static void setPlayerInfo(PlayerInfo player) {
		model.setPlayerInfo(player);
	}
	public static PlayerInfo getPlayerInfo() {
		return model.getPlayerInfo();
	}
	
	/**
	 * This function clears the current player info of the user
	 * @pre model must not be null
	 * @post player info is set to null
	 */
	public static void clearPlayernfo() {
		model.setPlayerInfo(null);
	}
}
