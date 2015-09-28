package shared.model.gamemap;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Random;
import java.util.Collections;

import shared.model.Resource;
import shared.model.pieces.City;
import shared.model.pieces.Road;
import shared.model.pieces.Settlement;

/**
 * Class is a singleton, and contains the data about the game map
 */
public class GameMap {

	//private Hex[] hexes;
	
	private TreeMap<HexLocation, Hex> hexes;
	
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
	
	
	//This doesn't have ports yet
	private void setUpMap(ArrayList<Hex> theHexes, ArrayList<Integer> theChits) {
		hexes = new TreeMap<HexLocation, Hex>();
		
		for(int i = -2; i <= 2; i++) {
			for(int j = -2; j <= 2; j++) {
				if(((i > 0) && !(j > 0)) || (!(i > 0) && (j > 0))) { //If either i or j (but not both) is greater than 0...
					if((Math.abs(i) + Math.abs(j)) <= radius) {
						addToMap(i, j, theHexes, theChits);
					}
				}
				else
				{
					addToMap(i, j, theHexes, theChits);
				}
			}
		}
	}
	
	//RUDIMENTARY!! Need to add edge and vertex overlap and ports
	private void addToMap(int x, int y, ArrayList<Hex> theHexes, ArrayList<Integer> theChits) {
		
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
		
		theHexes.remove(0);
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
