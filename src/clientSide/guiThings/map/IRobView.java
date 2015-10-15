package clientSide.guiThings.map;

import clientSide.guiThings.base.*;
import clientSide.guiThings.data.*;

/**
 * Interface for the rob view, which lets the user select a player to rob
 */
public interface IRobView extends IOverlayView
{
	
	void setPlayers(RobPlayerInfo[] candidateVictims);
}

