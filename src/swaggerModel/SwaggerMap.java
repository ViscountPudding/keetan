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

public class SwaggerMap {
	private List<SwaggerHex> hexes;
	private List<SwaggerPort> ports;
	private List<SwaggerRoad> roads;
	private List<SwaggerVertexObject> settlements;
	private List<SwaggerVertexObject> cities;

	public SwaggerMap(Model model) {
		hexes = new ArrayList<SwaggerHex>();
		ports = new ArrayList<SwaggerPort>();
		settlements = new ArrayList<SwaggerVertexObject>();
		cities = new ArrayList<SwaggerVertexObject>();		
		
		GameMap map = model.getMap();
		
		for (Hex hex : map.getHexes().values()) {
			if (hex.getResource() == Resource.DESERT) {
				hexes.add(new SwaggerDesertHex(hex));
			}
			else {
				hexes.add(new SwaggerNormalHex(hex));
			}
		}
		
		for (Port port : map.getPorts()) {
			ports.add(new SwaggerPort(port));
		}
		
		for (EdgeValue edge : map.getEdges().values()) {
			if (edge.getRoad() != null) {
				roads.add(new SwaggerRoad(edge));
			}
		}
		
		for (VertexValue vertex : map.getVertices().values()) {
			if (vertex.getSettlement() != null) {
				settlements.add(new SwaggerVertexObject(vertex));
			}
			else if (vertex.getCity() != null) {
				cities.add(new SwaggerVertexObject(vertex));
			}
		}
	}

	public GameMap toOurMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
