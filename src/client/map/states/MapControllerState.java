package client.map.states;

import model.EdgeLocation;
import model.HexLocation;
import model.VertexLocation;
import shared.definitions.PieceType;
import client.data.RobPlayerInfo;

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
	
	void playSoldierCard(); // this is supposed to trigger the placement of a robber

	void playRoadBuildingCard(); // this is supposed to trigger the placement of two roads
	
	void robPlayer(RobPlayerInfo victim);
}
