package shared.model.gamemap;

import java.util.List;

import shared.model.pieces.Settlement;
import shared.model.pieces.City;
import shared.model.locations.VertexLocation;

/**
 *  An object representing the vertex of a Hex, containing information about what is located at that hex
 */
public class VertexValue {

	private VertexLocation location;
	private Settlement settlement;
	private City city;

	/**
	 * @pre owner must be in the range [-1,3], and location must be a valid EdgeLocation
	 * @param location The map location of this vertex
	 * @post The object's stored owner and location are set to the given params.
	 */
	public VertexValue(VertexLocation location) {
		
		this.location = location;
		
		this.settlement = null;
		this.city = null;
	}

	public VertexLocation getLocation() {
		return location;
	}

	public Settlement getSettlement() {
		return settlement;
	}

	public void setSettlement(Settlement settlement) {
		this.settlement = settlement;
	}

	public City getCity() {
		return city;
	}

	/**
	 * @pre A settlement must already exist in this location to build a city
	 * @param city The new city to place
	 * @post The old settlement is removed and the new city is placed
	 */
	public void setCity(City city) {
		this.settlement = null;
		this.city = city;
	}

	public List<VertexValue> getAdjacentVertices() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EdgeValue> getAdjacentEdges() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		String string = "";
		if (this.settlement != null) {
			string += "Has settlement, ";
		}
	    if (this.city != null) {
			string += "Has city, ";
		}
		if (this.location != null) {
			string += "Location: " + location.toString();
		}
		return string;
	}
	
	
}
