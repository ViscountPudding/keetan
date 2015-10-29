package client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shared.definitions.EdgeDirection;
import client.data.PlayerInfo;

public class ClientModel {

	/**
	 * TOOOOOO DOOOOOO
	 * add the getAdjacentVertices and getCousinVertex function (change them to return locations instead of values)
	 * add the getNearbyVertices function (change it to return locations instead of values)
	 * CHANGE getNearbyEdges to get the third edge (if I'm thinking right you don't need to do any rim crap so hooray for that)
	 * 
	 * Also in ModelFacade finish implementing the canDo methods
	 * 
	 */
	
	
	
	PlayerInfo thisPlayer;
	
	private HashMap<HexLocation, Hex> hexes;
	private HashMap<EdgeLocation, Road> roads;
	private HashMap<VertexLocation, VertexObject> settlements;
	private HashMap<VertexLocation, VertexObject> cities;
	
	private TransferModel dataLump; //GG TAs

	public ClientModel() {
		hexes = new HashMap<HexLocation, Hex>();
		roads = new HashMap<EdgeLocation, Road>();
		settlements = new HashMap<VertexLocation, VertexObject>();
		cities = new HashMap<VertexLocation, VertexObject>();
		
		dataLump = null;
	}
	
	public PlayerInfo getThisPlayer() {
		return thisPlayer;
	}

	public HashMap<HexLocation, Hex> getHexes() {
		return hexes;
	}

	public HashMap<EdgeLocation, Road> getRoads() {
		return roads;
	}

	public HashMap<VertexLocation, VertexObject> getSettlements() {
		return settlements;
	}

	public HashMap<VertexLocation, VertexObject> getCities() {
		return cities;
	}

	public TransferModel getDataLump() {
		return dataLump;
	}
	
	public void update(TransferModel newLump) {
		this.dataLump = newLump;
		
		for(Hex hex : newLump.getMap().getHexes()) {
			hexes.put(hex.getLocation(), hex);
		}
		
		for(Road road : newLump.getMap().getRoads()) {
			roads.put(road.getLocation().getNormalizedLocation(), road);
		}
		
		for(VertexObject settlement : newLump.getMap().getSettlements()) {
			settlements.put(settlement.getLocation().getNormalizedLocation(), settlement);
		}
		
		for(VertexObject city : newLump.getMap().getCities()) {
			cities.put(city.getLocation().getNormalizedLocation(), city);
		}
	}
	
	///THINGS!
	
	public List<EdgeLocation> getAdjacentEdges(EdgeLocation checkEdge) {
		
		List<EdgeLocation> adjacentEdges = new ArrayList<EdgeLocation>();
		
		int x = checkEdge.getX();
		int y = checkEdge.getY();
		
		EdgeDirection face = checkEdge.getDirection();
		
		EdgeLocation adjacentLocationOne;
		EdgeLocation adjacentLocationTwo;
		
		switch (face) {
		case NorthWest:
			adjacentLocationOne = new EdgeLocation(x, y, EdgeDirection.SouthWest);
			adjacentLocationTwo = new EdgeLocation(x, y, EdgeDirection.North);
			break;
		case North:
			adjacentLocationOne = new EdgeLocation(x, y, EdgeDirection.NorthWest);
			adjacentLocationTwo = new EdgeLocation(x, y, EdgeDirection.NorthEast);
			break;
		case NorthEast:
			adjacentLocationOne = new EdgeLocation(x, y, EdgeDirection.North);
			adjacentLocationTwo = new EdgeLocation(x, y, EdgeDirection.SouthEast);
			break;
		case SouthEast:
			adjacentLocationOne = new EdgeLocation(x, y, EdgeDirection.NorthEast);
			adjacentLocationTwo = new EdgeLocation(x, y, EdgeDirection.South);
			break;
		case South:
			adjacentLocationOne = new EdgeLocation(x, y, EdgeDirection.SouthEast);
			adjacentLocationTwo = new EdgeLocation(x, y, EdgeDirection.SouthWest);
			break;
		case SouthWest:
			adjacentLocationOne = new EdgeLocation(x, y, EdgeDirection.South);
			adjacentLocationTwo = new EdgeLocation(x, y, EdgeDirection.NorthWest);
			break;
		default:
			adjacentLocationOne = null;
			adjacentLocationTwo = null;
			break;
		}
		
		adjacentEdges.add(adjacentLocationOne);
		adjacentEdges.add(adjacentLocationTwo);
		
		List<EdgeLocation> cousinEdges = getCousinEdges(checkEdge);
		
		if(cousinEdges != null) {
			adjacentEdges.addAll(cousinEdges);
		}
		
		return adjacentEdges;
	}
	
	private List<EdgeLocation> getCousinEdges(EdgeLocation checkEdge) {
		
		int x = checkEdge.getX();
		int y = checkEdge.getY();
		EdgeDirection face = checkEdge.getDirection();
		
		boolean NorthRim; //Whether the hex is part of the northern edge
		boolean SouthRim; //Whether the hex is part of the southern edge
		
		boolean EastRim; //Whether the hex is part of the eastern edge
		boolean WestRim; //Whether the hex is part of the western edge
		
		//RUDIMENTARY MY DEAR WATSON!
		
		if((y == 2) ||
		  ((x == -2) && (y == 0)) ||
		  ((x == -1 &&  y == 1))) {
			NorthRim = true;
		}
		else {
			NorthRim = false;
		}
		
		if((y == -2) ||
		  ((x == 2) && (y == 0)) ||
		  ((x  == 1 && y == -1))) {
			SouthRim = true;
		}
		else {
			SouthRim = false;
		}
		
		if(
		  ((x == 2) && (y == 2))  ||
		  ((x == 1) && (y == 1))  ||
		  ((x == 2) && (y == 0))  ||
		  ((x == 1) && (y == -1)) ||
		  ((x == 0) && (y == -2))) {
			
			EastRim = true;
		}
		else {
			EastRim = false;
		}
		
		if(
		  ((x == -2) && (y == 0))  ||
	      ((x == -2) && (y == -1)) ||
		  ((x == -2) && (y == 0))  ||
		  ((x == -1) && (y == 1))  ||
		  ((x == 0) && (y == 2)))   {
					
		WestRim = true;
		}
		else {
			WestRim = false;
		}
		
		
		HexLocation location = null;
		EdgeDirection directionOne = null;
		EdgeDirection directionTwo = null;
		
		switch(face) {
		case NorthWest:
			if(WestRim == false) {
				location = new HexLocation(x-1, y);
				directionOne = EdgeDirection.NorthEast;
				directionTwo = EdgeDirection.South;
			}
			else {
				if(NorthRim == true) {
					location = new HexLocation(x-1, y-1);
					directionOne = EdgeDirection.North;
				}
				else {
					location = new HexLocation(x, y+1);
					directionOne = EdgeDirection.SouthWest;
				}	
			}
			break;
		case North:
			if(NorthRim == false) {
				location = new HexLocation(x, y+1);
				directionOne = EdgeDirection.SouthEast;
				directionTwo = EdgeDirection.SouthWest;
			}
			else {
				if(WestRim == true) {
					location = new HexLocation(x+1,y+1);
					directionOne = EdgeDirection.NorthWest;
				}
				else {
					location = new HexLocation(x-1, y);
					directionOne = EdgeDirection.NorthEast;
				}
			}
			break;
		case NorthEast:
			if(
			((NorthRim == false) && (EastRim == false)) ||
			((NorthRim == true) && (WestRim == true)) ||		
			((EastRim == true) && (SouthRim == true))
			 ) {
				location = new HexLocation(x+1,y+1);
				directionOne = EdgeDirection.NorthWest;
				directionTwo = EdgeDirection.South;
			}
			else if (NorthRim == true) {
				location = new HexLocation(x+1,y);
				directionOne = EdgeDirection.North;
			}
			else if (EastRim == true) {
				location = new HexLocation(x, y+1);
				directionOne = EdgeDirection.SouthEast;
			}
			break;
		case SouthEast:
			if(EastRim == false) {
				location = new HexLocation(x+1, y);
				directionOne = EdgeDirection.North;
				directionTwo = EdgeDirection.SouthWest;
			}
			else {
				if(SouthRim == true) {
					location = new HexLocation(x+1, y+1);
					directionOne = EdgeDirection.South;
				}
				else {
					location = new HexLocation(x, y-1);
					directionOne = EdgeDirection.NorthEast;
				}
			}
			break;
		case South:
			if(SouthRim == false) {
				location = new HexLocation(x, y-1);
				directionOne = EdgeDirection.NorthWest;
				directionTwo = EdgeDirection.SouthWest;
			}
			else {
				if(EastRim == true) {
					location = new HexLocation(x-1, y-1);
					directionOne = EdgeDirection.SouthEast;
				}
				else {
					location = new HexLocation(x+1, y);
					directionOne = EdgeDirection.SouthWest;
				}
			}
			break;
		case SouthWest:
			if(
			((SouthRim == false) && (WestRim == false)) ||
			((SouthRim == true) && (EastRim == true)) ||		
			((WestRim == true) && (NorthRim == true))
			 ) {
				location = new HexLocation(x-1,y-1);
				directionOne = EdgeDirection.North;
				directionTwo = EdgeDirection.SouthEast;
			}
			else if (SouthRim == true) {
				location = new HexLocation(x-1,y);
				directionOne = EdgeDirection.South;
			}
			else if (WestRim == true) {
				location = new HexLocation(x, y-1);
				directionOne = EdgeDirection.NorthEast;
			}
			break;
		default:
			break;
		}
		
		List<EdgeLocation> cousinEdges = new ArrayList<EdgeLocation>();
		
		int newX = location.getX();
		int newY = location.getY();
		
		cousinEdges.add(new EdgeLocation(newX, newY, directionOne));
		cousinEdges.add(new EdgeLocation(newX, newY, directionTwo));
		
		return cousinEdges;
	}
	
}
