package client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shared.definitions.EdgeDirection;
import shared.definitions.VertexDirection;
import client.data.GameInfo;
import client.data.PlayerInfo;

public class ClientModel {	
	
	private HashMap<HexLocation, Hex> hexes;
	private HashMap<EdgeLocation, Road> roads;
	private HashMap<VertexLocation, VertexObject> settlements;
	private HashMap<VertexLocation, VertexObject> cities;
	
	/**
	 * This is where we store the model that the server sends to the client for updates
	 */
	private TransferModel dataLump; //GG TAs

	/**
	 * The username of the user
	 */
	private String username;
	/**
	 * This is where we store the info for user's player
	 */
	private PlayerInfo playerInfo;
	
	/**
	 * This is where we store the info for user's game the are currently playing or considering joining
	 */
	private GameInfo gameInfo;

	public GameInfo getGameInfo() {
		return gameInfo;
	}

	public void setGameInfo(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
	}

	public void setPlayerInfo(PlayerInfo playerInfo) {
		this.playerInfo = playerInfo;
	}

	public ClientModel() {
		hexes = new HashMap<HexLocation, Hex>();
		roads = new HashMap<EdgeLocation, Road>();
		settlements = new HashMap<VertexLocation, VertexObject>();
		cities = new HashMap<VertexLocation, VertexObject>();
		
		dataLump = null;
	}
	
	public PlayerInfo getPlayerInfo() {
		if (this.playerInfo == null) {
			this.playerInfo.getColor();
		}
		return this.playerInfo;
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
			if (settlements.containsKey(settlement.getLocation().getNormalizedLocation())){
				System.out.println("Hooray");
				System.out.println(settlements.get(settlement.getLocation().getNormalizedLocation()));
			}
		}
		
		for(VertexObject city : newLump.getMap().getCities()) {
			cities.put(city.getLocation().getNormalizedLocation(), city);
		}
		
		updateGameInfo();
	}
	private void updateGameInfo() {
		if (gameInfo == null) {
			gameInfo = new GameInfo();
		}
		else {
			gameInfo.update(dataLump);
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
	
	public List<VertexLocation> getAdjacentVertices(VertexLocation checkVertex) {
		
		List<VertexLocation> adjacentVertices = new ArrayList<VertexLocation>();
		
		HexLocation coordinates = checkVertex.getHexLocation();
		
		int x = coordinates.getX();
		int y = coordinates.getY();
		
		VertexDirection point = checkVertex.getDirection();
		
		VertexLocation adjacentLocationOne;
		VertexLocation adjacentLocationTwo;
		
		switch(point) {
			case NorthWest:
				adjacentLocationOne = new VertexLocation(x, y, VertexDirection.West);
				adjacentLocationTwo = new VertexLocation(x, y, VertexDirection.NorthEast);
				break;
			case NorthEast:
				adjacentLocationOne = new VertexLocation(x, y, VertexDirection.NorthWest);
				adjacentLocationTwo = new VertexLocation(x, y, VertexDirection.East);
				break;
			case East:
				adjacentLocationOne = new VertexLocation(x, y, VertexDirection.NorthEast);
				adjacentLocationTwo = new VertexLocation(x, y, VertexDirection.SouthEast);
				break;
			case SouthEast:
				adjacentLocationOne = new VertexLocation(x, y, VertexDirection.East);
				adjacentLocationTwo = new VertexLocation(x, y, VertexDirection.SouthWest);
				break;	
			case SouthWest:
				adjacentLocationOne = new VertexLocation(x, y, VertexDirection.SouthEast);
				adjacentLocationTwo = new VertexLocation(x, y, VertexDirection.West);
				break;
			case West:
				adjacentLocationOne = new VertexLocation(x, y, VertexDirection.SouthWest);
				adjacentLocationTwo = new VertexLocation(x, y, VertexDirection.NorthWest);
				break;
				
			default:
				adjacentLocationOne = null;
				adjacentLocationTwo = null;
				break;
		}
		
        VertexLocation distantCousin = getDistantCousin(checkVertex);
        
        if(distantCousin != null) {
            adjacentVertices.add(distantCousin);
        }
        
		adjacentVertices.add(adjacentLocationOne);
		adjacentVertices.add(adjacentLocationTwo);
		
		return adjacentVertices;
	}
	
	private VertexLocation getDistantCousin(VertexLocation checkVertex) {
		
		int x = checkVertex.getX();
		int y = checkVertex.getY();
		
		VertexDirection point = checkVertex.getDirection();
		
		boolean NorthEdge; //Whether the hex is not part of the northern edge
		boolean SouthEdge; //Whether the hex is not part of the southern edge
		
		//RUDIMENTARY MY DEAR WATSON!
		
		if((y == 2) ||
				  ((x == -2) && (y == 0)) ||
				  ((x == -1 &&  y == 1))) {
					NorthEdge = true;
				}
				else {
					NorthEdge = false;
				}
				
				if((y == -2) ||
				  ((x == 2) && (y == 0)) ||
				  ((x == 1 && y == -1))) {
					SouthEdge = true;
				}
				else {
					SouthEdge = false;
				}
		
		VertexDirection cousinDirection = null;
		
		int newX = 0;
		int newY = 0;
		
		switch(point) {
		
			case NorthWest:
				if(NorthEdge == false) {
					newX = x;
					newY = y + 1;
					cousinDirection = VertexDirection.West;
				}
				else {
					newX = x - 1;
					newY = y;
					cousinDirection = VertexDirection.NorthEast;					
				}
				break;
		
			case NorthEast:
				if(NorthEdge == false) {
					newX = x;
					newY = y + 1;
					cousinDirection = VertexDirection.East;
				}
				else {
					newX = x + 1;
					newY = y + 1;
					cousinDirection = VertexDirection.NorthWest;
				}
				break;
				
			case East:
				if(SouthEdge == false) {
					newX = x + 1;
					newY = y;
					cousinDirection = VertexDirection.NorthEast;
				}
				else {
					newX = x + 1;
					newY = y + 1;
					cousinDirection = VertexDirection.SouthEast;
				}
				break;

			case SouthEast:
				if(NorthEdge == false) {
					newX = x + 1;
					newY = y;
					cousinDirection = VertexDirection.SouthWest;
				}
				else {
					newX = x;
					newY = y - 1;
					cousinDirection = VertexDirection.East;
				}
				break;
				
			case SouthWest:
				if(SouthEdge == false) {
					newX = x;
					newY = y - 1;
					cousinDirection = VertexDirection.West;
				}
				else {
					newX = x - 1;
					newY = y - 1;
					cousinDirection = VertexDirection.SouthEast;
				}
				break;

			case West:
				if(NorthEdge == false) {
					newX = x - 1;
					newY = y;
					cousinDirection = VertexDirection.SouthWest;
				}
				else {
					newX = x - 1;
					newY = y - 1;
					cousinDirection = VertexDirection.NorthWest;
				}
				break;
		}
		

		
		VertexLocation cousinLocation = null;

		switch(point) {
		
			case NorthWest:
				if(((x == -2 && y == 0)) || ((x == 0) && (y == 2)) || ((x == -1) && (y == 1))) {
					cousinLocation = null;
				}
				else {
					cousinLocation = new VertexLocation(newX, newY, cousinDirection);
				}
				break;
				
			case NorthEast:
				if(((x == 0 && y == 2)) || ((x == 1) && (y == 2)) || ((x == 2) && (y == 2))) {
					cousinLocation = null;
				}
				else {
					cousinLocation = new VertexLocation(newX, newY, cousinDirection);
				}
				break;

			case East:
				if(((x == 2 && y == 2)) || ((x == 2) && (y == 1)) || ((x == 2) && (y == 0))) {
					cousinLocation = null;
				}
				else {
					cousinLocation = new VertexLocation(newX, newY, cousinDirection);
				}
				break;
				
			case SouthEast:
				if(((x == 2 && y == 0)) || ((x == 1) && (y == -1)) || ((x == 0) && (y == -2))) {
					cousinLocation = null;
				}
				else {
					cousinLocation = new VertexLocation(newX, newY, cousinDirection);
				}
				break;
				
			case SouthWest:
				if(((x == 0 && y == -2)) || ((x == -1) && (y == -2)) || ((x == -2) && (y == -2))) {
					cousinLocation = null;
				}
				else {
					cousinLocation = new VertexLocation(newX, newY, cousinDirection);
				}
				break;
				
			case West:
				if(((x == -2 && y == -2)) || ((x == -2) && (y == -1)) || ((x == -2) && (y == 0))) {
					cousinLocation = null;
				}
				else {
					cousinLocation = new VertexLocation(newX, newY, cousinDirection);
				}
				break;
		}

		
		return cousinLocation;
	}
	
	public List<VertexLocation> getNearbyVertices(EdgeLocation checkEdge) {
		
		int x = checkEdge.getX();
		int y = checkEdge.getY();
		
		EdgeDirection face = checkEdge.getDirection();
		
		VertexDirection pointOne = null;
		VertexDirection pointTwo = null;
		
		switch(face) {
		case NorthWest:
			pointOne = VertexDirection.West;
			pointTwo = VertexDirection.NorthWest;
			break;
		case North:
			pointOne = VertexDirection.NorthWest;
			pointTwo = VertexDirection.NorthEast;
			break;
		case NorthEast:
			pointOne = VertexDirection.NorthEast;
			pointTwo = VertexDirection.East;
			break;
		case SouthEast:
			pointOne = VertexDirection.East;
			pointTwo = VertexDirection.SouthEast;
			break;
		case South:
			pointOne = VertexDirection.SouthEast;
			pointTwo = VertexDirection.SouthWest;
			break;
		case SouthWest:
			pointOne = VertexDirection.SouthWest;
			pointTwo = VertexDirection.West;
			break;
		}
		
		List<VertexLocation> nearbyVertices = new ArrayList<VertexLocation>();
		
		VertexLocation locationOne = new VertexLocation(x, y, pointOne);
		VertexLocation locationTwo = new VertexLocation(x, y, pointTwo);
		
		nearbyVertices.add(locationOne);
		nearbyVertices.add(locationTwo);
		
		return nearbyVertices;
	}
	
	public List<EdgeLocation> getNearbyEdges(VertexLocation checkVertex) {
		int x = checkVertex.getX();
		int y = checkVertex.getY();
		
		VertexDirection point = checkVertex.getDirection();
		
		EdgeDirection faceOne = null;
		EdgeDirection faceTwo = null;
		
		int newX = 0;
		int newY = 0;
		
		EdgeDirection farFace = null;
		
		switch(point) {
		case NorthWest:
			faceOne = EdgeDirection.NorthWest;
			faceTwo = EdgeDirection.North;
			//FOR THE DISTANCE!
			newX = x - 1;
			newY = y;
			farFace = EdgeDirection.NorthEast;
			break;
		case NorthEast:
			faceOne = EdgeDirection.North;
			faceTwo = EdgeDirection.NorthEast;
			//FOR THE DISTANCE!
			newX = x;
			newY = y + 1;
			farFace = EdgeDirection.SouthEast;
			break;
		case East:
			faceOne = EdgeDirection.NorthEast;
			faceTwo = EdgeDirection.SouthEast;
			//FOR THE DISTANCE!
			newX = x + 1;
			newY = y;
			farFace = EdgeDirection.North;
			break;
		case SouthEast:
			faceOne = EdgeDirection.SouthEast;
			faceTwo = EdgeDirection.South;
			//FOR THE DISTANCE!
			newX = x + 1;
			newY = y;
			farFace = EdgeDirection.SouthWest;
			break;
		case SouthWest:
			faceOne = EdgeDirection.South;
			faceTwo = EdgeDirection.SouthWest;
			//FOR THE DISTANCE!
			newX = x;
			newY = y - 1;
			farFace = EdgeDirection.NorthWest;
			break;
		case West:
			faceOne = EdgeDirection.SouthWest;
			faceTwo = EdgeDirection.NorthWest;
			//FOR THE DISTANCE!
			newX = x - 1;
			newY = y;
			farFace = EdgeDirection.South;
			break;
		}
		
		List<EdgeLocation> nearbyEdges = new ArrayList<EdgeLocation>();
		
		EdgeLocation locationOne = new EdgeLocation(x, y, faceOne);
		EdgeLocation locationTwo = new EdgeLocation(x, y, faceTwo);
		
		EdgeLocation farLocation = new EdgeLocation(newX, newY, farFace);
		
		nearbyEdges.add(locationOne);
		nearbyEdges.add(locationTwo);
		nearbyEdges.add(farLocation);
		
		
		return nearbyEdges;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
}
