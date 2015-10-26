package clientSide.guiThings.map.states;

import clientSide.guiThings.data.RobPlayerInfo;
import shared.definitions.PieceType;
import shared.model.locations.EdgeLocation;
import shared.model.locations.HexLocation;
import shared.model.locations.VertexLocation;

public interface MapControllerState {

	boolean canPlaceRoad(EdgeLocation edgeLoc);
	
	boolean canPlaceSettlement(VertexLocation vertLoc);
	
	boolean canPlaceCity(VertexLocation vertLoc);
	
	boolean canPlaceRobber(HexLocation hexLoc);
	
	void placeRoad(EdgeLocation edgeLoc);
	
	void placeSettlement(VertexLocation vertLoc);
	
	void placeCity(VertexLocation vertLoc);
	
	void placeRobber(HexLocation hexLoc);
	
	void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected);
	
	void cancelMove();
	
	void playSoldierCard(int victimIndex, HexLocation location);

	void playRoadBuildingCard(EdgeLocation roadOne, EdgeLocation roadTwo);
	
	void robPlayer(RobPlayerInfo victim);
}
