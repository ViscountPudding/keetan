package shared.model.gamemap;

import java.util.ArrayList;

import shared.model.pieces.City;
import shared.model.pieces.Road;
import shared.model.pieces.Settlement;

/**
 * Class is a singleton, and contains the data about the game map
 */
public class GameMap {

	private Hex[] hexes;
	private Port[] ports;
	private ArrayList<Road> roads;
	private ArrayList<Settlement> settlements;
	private ArrayList<City> cities;
	private int radius;
	private HexLocation robber;
	
	/**
	 * @pre The game must be starting or the map must be changing
	 * @param hexes The array of hexes in the game map
	 * @param ports The array of port locations in the game map
	 * @param roads The array of roads in the game map
	 * @param settlements The array of settlements in the game map 
	 * @param cities The array of cities in the game map
	 * @param radius The map's radius
	 * @param robber The robber's HexLocation
	 * @post The objects internal values are set to the given params.
	 */
	public GameMap(Hex[] hexes, Port[] ports, ArrayList<Road> roads,
			ArrayList<Settlement> settlements, ArrayList<City> cities, int radius,
			HexLocation robber) {
		this.hexes = hexes;
		this.ports = ports;
		this.roads = roads;
		this.settlements = settlements;
		this.cities = cities;
		this.radius = radius;
		this.robber = robber;
	}

	public HexLocation getRobber() {
		return robber;
	}

	public void setRobber(HexLocation robber) {
		this.robber = robber;
	}

	public Hex[] getHexes() {
		return hexes;
	}

	public Port[] getPorts() {
		return ports;
	}

	public ArrayList<Road> getRoads() {
		return roads;
	}
	
	/**
	 * @pre EdgeValue must be a valid location to place a road
	 * @param road New location to place road
	 * @post road is added to the map
	 */
	public void addRoad(Road road) {
		roads.add(road);
	}

	public ArrayList<Settlement> getSettlements() {
		return settlements;
	}
	
	/**
	 * @pre settlement must be a valid location to place a settlement
	 * @param settlement The location for the new settlement
	 * @post settlement is added to the map
	 */
	public void addSettlement(Settlement settlement) {
		settlements.add(settlement);
	}

	public ArrayList<City> getCities() {
		return cities;
	}
	
	/**
	 * @pre city must be a valid location to upgrade a settlement
	 * @param city
	 * @post The settlement at the vertex is removed from the map, and a city put in its place
	 */
	public void addCity(City city) {
		cities.add(city);
	}

	public int getRadius() {
		return radius;
	}
	
	
}
