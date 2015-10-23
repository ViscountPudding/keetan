package swaggerModel;

import java.util.ArrayList;
import java.util.List;

import shared.model.Model;
import shared.model.gamemap.GameMap;
import shared.model.gamemap.Hex;

public class SwaggerMap {
	private List<SwaggerHex> hexes;
	private List<SwaggerPort> ports;
	private List<SwaggerRoad> roads;
	private List<SwaggerVertexObject> settlements;
	private List<SwaggerVertexObject> cities;

	public SwaggerMap(Model clientModel) {
		hexes = new ArrayList<SwaggerHex>();
		ports = new ArrayList<SwaggerPort>();
		settlements = new ArrayList<SwaggerVertexObject>();
		cities = new ArrayList<SwaggerVertexObject>();		
		
		GameMap map = clientModel.getMap();
		for (Hex hex : map.getHexes().values()) {
			hexes.add(new SwaggerHex(hex));
		}
	}
}
