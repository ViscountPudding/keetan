package client.model;

import java.util.HashMap;

import client.data.PlayerInfo;

public class ClientModel {

	PlayerInfo thisPlayer;
	
	private HashMap<HexLocation, Hex> hexes;
	private HashMap<EdgeLocation, Road> roads;
	private HashMap<VertexLocation, VertexObject> settlements;
	private HashMap<VertexLocation, VertexObject> cities;
	
	private TransferModel dataLump; //GG TAs

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
			roads.put(road.getLocation(), road);
		}
		
		for(VertexObject settlement : newLump.getMap().getSettlements()) {
			settlements.put(settlement.getLocation(), settlement);
		}
		
		for(VertexObject city : newLump.getMap().getCities()) {
			cities.put(city.getLocation(), city);
		}
	}
	
}
