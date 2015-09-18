package model.gamemap;

/**
 * Class is a singleton, and contains the data about the game map
 */
public class GameMap {

	private Hex[] hexes;
	private Port[] ports;
	private EdgeValue[] roads;
	private VertexObject[] settlements;
	private VertexObject[] cities;
	private int radius;
	private HexLocation robber;
	
	/**
	 * @pre 
	 * @param hexes
	 * @param ports
	 * @param roads
	 * @param settlements
	 * @param cities
	 * @param radius The map's radius
	 * @param robber The robber's HexLocation
	 * @post The objects internal values are set to the given params.
	 */
	public GameMap(Hex[] hexes, Port[] ports, EdgeValue[] roads,
			VertexObject[] settlements, VertexObject[] cities, int radius,
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

	public EdgeValue[] getRoads() {
		return roads;
	}
	
	/**
	 * @pre EdgeValue must be a valid location to place a road
	 * @param road New location to place road
	 * @post road is added to the map
	 */
	public void addRoad(EdgeValue road) {
		
	}

	public VertexObject[] getSettlements() {
		return settlements;
	}
	
	/**
	 * @pre settlement must be a valid location to place a settlement
	 * @param settlement The location for the new settlement
	 * @post settlement is added to the map
	 */
	public void addSettlement(VertexObject settlement) {
		
	}

	public VertexObject[] getCities() {
		return cities;
	}
	
	/**
	 * @pre city must be a valid location to upgrade a settlement
	 * @param city
	 * @post The settlement at the vertex is removed from the map, and a city put in its place
	 */
	public void setCities(VertexObject city) {
		
	}

	public int getRadius() {
		return radius;
	}
	
	
}
