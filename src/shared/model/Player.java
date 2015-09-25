package shared.model;

import java.util.ArrayList;

import shared.model.gamemap.Port;
import shared.model.pieces.*;

/**
 * Contains data about a given player
 */
public class Player {
	
	private String color = null;
	private boolean discarded;
	private int monuments;
	private String name = null;
	private DevCardList newDevCards = null;
	private DevCardList oldDevCards = null;
	private int playerIndex;
	private boolean playedDevCard;
	private int playerID;
	private ResourceList resources = new ResourceList(0,0,0,0,0);
	private ArrayList<City> cities = new ArrayList<City>();
	private ArrayList<Road> roads = new ArrayList<Road>();
	private ArrayList<Settlement> settlements = new ArrayList<Settlement>();
	private ArrayList<Port> ports = new ArrayList<Port>();
	private int soldiers;
	private int victoryPoints;
	
	/**
	 * @pre newPlayerIndex must be in the range [0,3], and newName must not be in use
	 * @param newName The player's name
	 * @param newPlayerIndex The player's playerIndex
	 * @param newPlayerID The player's ID
	 * @post The object's internal values are initialized
	 */
	public Player(String newName, int newPlayerIndex, int newPlayerID) {
		name = newName;
		playerIndex = newPlayerIndex;
		playerID = newPlayerID;
	}

	public ArrayList<City> getCities() {
		return cities;
	}

	public void addCity(City city) {
		cities.add(city);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isDiscarded() {
		return discarded;
	}

	public void setDiscarded(boolean discarded) {
		this.discarded = discarded;
	}

	public int getMonuments() {
		return monuments;
	}

	public void setMonuments(int monuments) {
		this.monuments = monuments;
	}

	public DevCardList getNewDevCards() {
		return newDevCards;
	}

	public void setNewDevCards(DevCardList newDevCards) {
		this.newDevCards = newDevCards;
	}

	public DevCardList getOldDevCards() {
		return oldDevCards;
	}

	public void setOldDevCards(DevCardList oldDevCards) {
		this.oldDevCards = oldDevCards;
	}

	public boolean isPlayedDevCard() {
		return playedDevCard;
	}

	public void setPlayedDevCard(boolean playedDevCard) {
		this.playedDevCard = playedDevCard;
	}

	public ResourceList getResources() {
		return resources;
	}

	public void setResources(ResourceList resources) {
		this.resources = resources;
	}

	public ArrayList<Road> getRoads() {
		return roads;
	}

	public void addRoad(Road road) {
		roads.add(road);
	}

	public ArrayList<Settlement> getSettlements() {
		return settlements;
	}

	public void addSettlement(Settlement settlement) {
		settlements.add(settlement);
	}
	
	public void removeSettlement(Settlement settlement) {
		settlements.remove(settlement);
	}
	
	public ArrayList<Port> getPorts() {
		return ports;
	}
	
	public void addPort(Port port) {
		ports.add(port);
	}

	public int getSoldiers() {
		return soldiers;
	}

	public void setSoldiers(int soldiers) {
		this.soldiers = soldiers;
	}

	public int getVictoryPoints() {
		return victoryPoints;
	}

	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}

	public String getName() {
		return name;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public int getPlayerID() {
		return playerID;
	}
}
