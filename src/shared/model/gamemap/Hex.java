package shared.model.gamemap;

import java.util.List;

import shared.model.Resource;
/**
 * Contains data about a given hex on the game map.
 */
public class Hex {
	private HexLocation location;
	private Resource resource;
	private int diceNumber;
	private VertexObject[] vertices;
	private EdgeValue[] edges;
	
	/**
	 * @pre diceNumber must be in the range [2,12], vertices should be of size 6
	 * @param location The HexLocation of this hex
	 * @param resource The resource produced by this hex
	 * @param diceNumber The number that, when rolled by the dice, cause this hex to produce resources
	 * @param vertices The array of vertices associated with this Hex.
	 * @post The objects internal values are set to the given params
	 */
	public Hex(Resource resource) {
		//this.location = location;
		this.resource = resource;
		//this.diceNumber = diceNumber;
		//this.vertices = vertices;
	}
	
	public HexLocation getLocation() {
		return location;
	}
	public Resource getResource() {
		return resource;
	}
	public int getDiceNumber() {
		return diceNumber;
	}
	public VertexObject[] getVertices() {
		return vertices;
	}

	public EdgeValue[] getEdges() {
		return edges;
	}
	
	public void setLocation(HexLocation location) {
		this.location = location;
	}

	public void setDiceNumber(int diceNumber) {
		this.diceNumber = diceNumber;
	}

	public void setVertices(VertexObject[] vertices) {
		this.vertices = vertices;
	}

	public void setEdges(EdgeValue[] edges) {
		this.edges = edges;
	}
	
	public List<VertexObject> getAdjacentVertices() {
		// TODO Auto-generated method stub
		return null;
	}
}
