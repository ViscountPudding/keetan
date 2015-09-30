package shared.model.gamemap;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Random;
import java.util.Collections;

import shared.model.Resource;
import shared.model.pieces.City;
import shared.model.pieces.Road;
import shared.model.pieces.Settlement;

import shared.model.locations.HexLocation;
import shared.model.locations.EdgeLocation;
import shared.model.locations.EdgeDirection;
import shared.model.locations.VertexLocation;
import shared.model.locations.VertexDirection;

/**
 * Class is a singleton, and contains the data about the game map
 */
public class GameMap {

	//private Hex[] hexes;
	
	private TreeMap<HexLocation, Hex> hexes;
	
	private TreeMap<EdgeLocation, EdgeValue> edges;
	
	private TreeMap<VertexLocation, VertexValue> vertices;
	
	private Port[] ports;
	private ArrayList<Road> roads;
	private ArrayList<Settlement> settlements;
	private ArrayList<City> cities;
	private int radius;
	private HexLocation robber;
	
	/**
	 * @pre The game must be starting or the map must be changing
	 * @param randomHexes determines whether or not the hexes are randomized
	 * @param randomChits determines whether or not the chits are randomized
	 * @param randomPorts determines whether or not the ports are randomized
	 * @param loadGame determines whether or not you are loading a game or starting a new one
	 * @post The objects internal values are set to the given params.
	 */
	public GameMap(boolean randomHexes, boolean randomChits, boolean randomPorts, boolean loadGame) {
		
		radius = 2;
		
		if(loadGame == false) {	
			ArrayList<Hex> theHexes = initializeHexList(randomHexes);
			
			ArrayList<Integer> theChits = initializeChitList(randomChits);
			
			//Need something with ports as well
			
			setUpMap(theHexes, theChits);
			
		}
		
		
	}
	
	private ArrayList<Hex> initializeHexList(boolean randomHexes) {
		
		ArrayList<Hex> gameHexes = new ArrayList<Hex>();
		
		//First Row of Beginner Grid
		gameHexes.add(new Hex(Resource.WOOD));
		gameHexes.add(new Hex(Resource.SHEEP));
		gameHexes.add(new Hex(Resource.WHEAT));
		
		//Second Row
		gameHexes.add(new Hex(Resource.BRICK));
		gameHexes.add(new Hex(Resource.ORE));
		gameHexes.add(new Hex(Resource.BRICK));
		gameHexes.add(new Hex(Resource.SHEEP));
		
		//Third Row
		gameHexes.add(new Hex(Resource.DESERT));
		gameHexes.add(new Hex(Resource.WOOD));
		gameHexes.add(new Hex(Resource.WHEAT));
		gameHexes.add(new Hex(Resource.WOOD));
		gameHexes.add(new Hex(Resource.WHEAT));
		
		//Fourth Row
		gameHexes.add(new Hex(Resource.BRICK));
		gameHexes.add(new Hex(Resource.SHEEP));
		gameHexes.add(new Hex(Resource.SHEEP));
		gameHexes.add(new Hex(Resource.ORE));
		
		//Fifth Row
		gameHexes.add(new Hex(Resource.ORE));
		gameHexes.add(new Hex(Resource.WHEAT));
		gameHexes.add(new Hex(Resource.WOOD));
		
		if(randomHexes == true) {
			long seed = System.nanoTime();
			Collections.shuffle(gameHexes, new Random(seed));
		}
		
		return gameHexes;
	}
	
	private ArrayList<Integer> initializeChitList(boolean randomChits) {
		
		ArrayList<Integer> chits = new ArrayList<Integer>();
		
		//First Row for beginner board
		chits.add(new Integer(11));
		chits.add(new Integer(12));
		chits.add(new Integer(9));
		
		//Second Row
		chits.add(new Integer(4));
		chits.add(new Integer(6));
		chits.add(new Integer(5));
		chits.add(new Integer(10));
		
		//Third Row (Except Desert, which has no chits)
		chits.add(new Integer(3));
		chits.add(new Integer(11));
		chits.add(new Integer(4));
		chits.add(new Integer(8));
		
		//Fourth Row
		chits.add(new Integer(8));
		chits.add(new Integer(10));
		chits.add(new Integer(9));
		chits.add(new Integer(3));
		
		//Fifth Row
		chits.add(new Integer(5));
		chits.add(new Integer(2));
		chits.add(new Integer(6));
		
		if(randomChits == true) {
			long seed = System.nanoTime();
			Collections.shuffle(chits, new Random(seed));
		}
		
		return chits;
	}
	
	
	//This doesn't have ports or vertices yet
	private void setUpMap(ArrayList<Hex> theHexes, ArrayList<Integer> theChits) {
		hexes = new TreeMap<HexLocation, Hex>();
		edges = new TreeMap<EdgeLocation, EdgeValue>();
		vertices = new TreeMap<VertexLocation, VertexValue>();
		
		for(int i = -2; i <= 2; i++) {
			for(int j = -2; j <= 2; j++) {
				if(((i > 0) && !(j > 0)) || (!(i > 0) && (j > 0))) { //If either i or j (but not both) is greater than 0...
					if((Math.abs(i) + Math.abs(j)) <= radius) { //If the sum of the absolute values is less than or equal to the radius...
						Hex newHex = addToMap(i, j, theHexes, theChits);
						TreeMap<EdgeDirection, EdgeValue> newEdges = addEdges(i, j);
						TreeMap<VertexDirection, VertexValue> newVertices = addVertices(i, j);
						newHex.establishEdges(newEdges);
						newHex.verifyVertices(newVertices);
					}
				}
				else
				{
					Hex newHex = addToMap(i, j, theHexes, theChits);
					TreeMap<EdgeDirection, EdgeValue> newEdges = addEdges(i, j);
					TreeMap<VertexDirection, VertexValue> newVertices = addVertices(i, j);
					newHex.establishEdges(newEdges);
					newHex.verifyVertices(newVertices);
				}
			}
		}
	}
	
	//RUDIMENTARY!!
	private Hex addToMap(int x, int y, ArrayList<Hex> theHexes, ArrayList<Integer> theChits) {
		
		HexLocation coordinates = new HexLocation(x,y);
		
		theHexes.get(0).setLocation(coordinates); //Set the hex's location to the coordinates
		
		if (theHexes.get(0).getResource() == Resource.DESERT) {
			robber = coordinates;
		}
		else {
			theHexes.get(0).setDiceNumber(theChits.get(0).intValue());
			theChits.remove(0); //Pop off top chit as it is already used
		}
		
		hexes.put(coordinates, theHexes.get(0));
		
		return theHexes.remove(0);
	}
	
	//RUDIMENTARY!!
	private TreeMap<EdgeDirection, EdgeValue> addEdges(int x, int y) {
		
		TreeMap<EdgeDirection, EdgeValue> newEdges = new TreeMap<EdgeDirection, EdgeValue>();
		
		HexLocation coordinates = new HexLocation(x,y);
		
		//Create new edges in the six directions
		
		EdgeLocation northwestLocation = new EdgeLocation(coordinates, EdgeDirection.NorthWest);
		EdgeValue northwestEdge = new EdgeValue(northwestLocation);
		
		EdgeLocation northLocation = new EdgeLocation(coordinates, EdgeDirection.North);
		EdgeValue northEdge = new EdgeValue(northLocation);
		
		EdgeLocation northeastLocation = new EdgeLocation(coordinates, EdgeDirection.NorthEast);
		EdgeValue northeastEdge = new EdgeValue(northeastLocation);
		
		EdgeLocation southeastLocation = new EdgeLocation(coordinates, EdgeDirection.SouthEast);
		EdgeValue southeastEdge = new EdgeValue(southeastLocation);
		
		EdgeLocation southLocation = new EdgeLocation(coordinates, EdgeDirection.South);
		EdgeValue southEdge = new EdgeValue(southLocation);
		
		EdgeLocation southwestLocation = new EdgeLocation(coordinates, EdgeDirection.SouthWest);
		EdgeValue southwestEdge = new EdgeValue(southwestLocation);
		
		//Check edges map, add new edges if they don't already exist
		
		if(edges.get(northwestLocation.getNormalizedLocation()) == null) {
			edges.put(northwestLocation.getNormalizedLocation(), northwestEdge);
		}
		
		if(edges.get(northLocation.getNormalizedLocation()) == null) {
			edges.put(northLocation.getNormalizedLocation(), northEdge);
		}
		
		if(edges.get(northeastLocation.getNormalizedLocation()) == null) {
			edges.put(northeastLocation.getNormalizedLocation(), northeastEdge);
		}
		
		if(edges.get(southeastLocation.getNormalizedLocation()) == null) {
			edges.put(southeastLocation.getNormalizedLocation(), southeastEdge);
		}
		
		if(edges.get(southLocation.getNormalizedLocation()) == null) {
			edges.put(southLocation.getNormalizedLocation(), southEdge);
		}
		
		if(edges.get(southwestLocation.getNormalizedLocation()) == null) {
			edges.put(southwestLocation.getNormalizedLocation(), southwestEdge);
		}
		
		//Fill in newEdges with related edges on edges map
		
		newEdges.put(EdgeDirection.NorthWest, edges.get(northwestLocation.getNormalizedLocation()));
		newEdges.put(EdgeDirection.North, edges.get(northLocation.getNormalizedLocation()));
		newEdges.put(EdgeDirection.NorthEast, edges.get(northeastLocation.getNormalizedLocation()));
		newEdges.put(EdgeDirection.SouthEast, edges.get(southeastLocation.getNormalizedLocation()));
		newEdges.put(EdgeDirection.South, edges.get(southLocation.getNormalizedLocation()));
		newEdges.put(EdgeDirection.SouthWest, edges.get(southwestLocation.getNormalizedLocation()));
		
		return newEdges;
	}
	
	//RUDIMENTARY!!
	private TreeMap<VertexDirection, VertexValue> addVertices(int x, int y) {
		
		TreeMap<VertexDirection, VertexValue> newVertices = new TreeMap<VertexDirection, VertexValue>();
		
		HexLocation coordinates = new HexLocation(x,y);
		
		//Create new vertices in six directions
		
		VertexLocation westLocation = new VertexLocation(coordinates, VertexDirection.West);
		VertexValue westVertex = new VertexValue(westLocation);
		
		VertexLocation northwestLocation = new VertexLocation(coordinates, VertexDirection.NorthWest);
		VertexValue northwestVertex = new VertexValue(northwestLocation);
		
		VertexLocation northeastLocation = new VertexLocation(coordinates, VertexDirection.NorthEast);
		VertexValue northeastVertex = new VertexValue(northeastLocation);
		
		VertexLocation eastLocation = new VertexLocation(coordinates, VertexDirection.East);
		VertexValue eastVertex = new VertexValue(eastLocation);
		
		VertexLocation southeastLocation = new VertexLocation(coordinates, VertexDirection.SouthEast);
		VertexValue southeastVertex = new VertexValue(southeastLocation);
		
		VertexLocation southwestLocation = new VertexLocation(coordinates, VertexDirection.West);
		VertexValue southwestVertex = new VertexValue(southwestLocation);
		
		//check vertices map, adding new vertices if they don't already exist
		
		if(vertices.get(westLocation.getNormalizedLocation()) == null) {
			vertices.put(westLocation.getNormalizedLocation(), westVertex);
		}
		
		if(vertices.get(northwestLocation.getNormalizedLocation()) == null) {
			vertices.put(northwestLocation.getNormalizedLocation(), northwestVertex);
		}
		
		if(vertices.get(northeastLocation.getNormalizedLocation()) == null) {
			vertices.put(northeastLocation.getNormalizedLocation(), northeastVertex);
		}
		
		if(vertices.get(eastLocation.getNormalizedLocation()) == null) {
			vertices.put(eastLocation.getNormalizedLocation(), eastVertex);
		}
		
		if(vertices.get(southeastLocation.getNormalizedLocation()) == null) {
			vertices.put(southeastLocation.getNormalizedLocation(), southeastVertex);
		}
		
		if(vertices.get(southwestLocation.getNormalizedLocation()) == null) {
			vertices.put(southwestLocation.getNormalizedLocation(), southwestVertex);
		}
		
		//Fill in newVertices with related vertices on vertices map
		newVertices.put(VertexDirection.West, vertices.get(westLocation.getNormalizedLocation()));
		newVertices.put(VertexDirection.NorthWest, vertices.get(northwestLocation.getNormalizedLocation()));
		newVertices.put(VertexDirection.NorthEast, vertices.get(northeastLocation.getNormalizedLocation()));
		newVertices.put(VertexDirection.East, vertices.get(eastLocation.getNormalizedLocation()));
		newVertices.put(VertexDirection.SouthEast, vertices.get(southeastLocation.getNormalizedLocation()));
		newVertices.put(VertexDirection.SouthWest, vertices.get(southwestLocation.getNormalizedLocation()));
		
		return newVertices;
	}
	
	public HexLocation getRobber() {
		return robber;
	}

	public void setRobber(HexLocation robber) {
		this.robber = robber;
	}

	public TreeMap<HexLocation, Hex> getHexes() {
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
