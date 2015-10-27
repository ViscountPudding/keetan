package swaggerModel;

import java.util.ArrayList;
import java.util.List;

import shared.model.Model;
import shared.model.Resource;
import shared.model.gamemap.EdgeValue;
import shared.model.gamemap.GameMap;
import shared.model.gamemap.Hex;
import shared.model.gamemap.Port;
import shared.model.gamemap.VertexValue;

public class Map {
	private List<Hex> hexes;
	private List<Port> ports;
	private List<Road> roads;
	private List<VertexObject> settlements;
	private List<VertexObject> cities;

	public Map(Model model) {
		hexes = new ArrayList<Hex>();
		ports = new ArrayList<Port>();
		settlements = new ArrayList<VertexObject>();
		cities = new ArrayList<VertexObject>();		
		
		GameMap map = model.getMap();
		
		for (Hex hex : map.getHexes().values()) {
			if (hex.getResource() == Resource.DESERT) {
				hexes.add(new DesertHex(hex));
			}
			else {
				hexes.add(new NormalHex(hex));
			}
		}
		
		for (Port port : map.getPorts()) {
			ports.add(new Port(port));
		}
		
		for (EdgeValue edge : map.getEdges().values()) {
			if (edge.getRoad() != null) {
				roads.add(new Road(edge));
			}
		}
		
		for (VertexValue vertex : map.getVertices().values()) {
			if (vertex.getSettlement() != null) {
				settlements.add(new VertexObject(vertex));
			}
			else if (vertex.getCity() != null) {
				cities.add(new VertexObject(vertex));
			}
		}
	}

	public GameMap toOurMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
