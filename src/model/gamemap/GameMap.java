package model.gamemap;

public class GameMap {

	private Hex[] hexes;
	private Port[] ports;
	private EdgeValue[] roads;
	private VertexObject[] settlements;
	private VertexObject[] cities;
	private int radius;
	private HexLocation robber;
	
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

	public VertexObject[] getSettlements() {
		return settlements;
	}

	public VertexObject[] getCities() {
		return cities;
	}

	public int getRadius() {
		return radius;
	}
	
	
}
