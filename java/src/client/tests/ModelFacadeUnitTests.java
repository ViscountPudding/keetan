package client.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import shared.definitions.CatanColor;
import client.model.DevCardList;
import client.model.MessageList;
import client.model.Player;
import client.model.ResourceList;
import client.model.TransferMap;
import client.model.TransferModel;
import client.model.TurnTracker;

public class ModelFacadeUnitTests {

	@Test
	public void test() {
		//fail("Not yet implemented");
		
		TransferModel HeadBrick = new TransferModel();
	
		List<Player> brickPlayers = new ArrayList<Player>();
		
		brickPlayers.add(new Player("Fu", 0, CatanColor.BLUE, 123));
		brickPlayers.add(new Player("Rin", 1, CatanColor.GREEN, 456));
		brickPlayers.add(new Player("Ka", 2, CatanColor.RED, 789));
		brickPlayers.add(new Player("Zan", 3, CatanColor.PURPLE, 000));
		
		HeadBrick.setPlayers(brickPlayers);
		
		HeadBrick.setDeck(new DevCardList(2, 5, 2, 14, 2));
	
		HeadBrick.setBank(new ResourceList(19,19,19,19,19));
		
		HeadBrick.setChat(new MessageList());
		HeadBrick.setLog(new MessageList());
		
		HeadBrick.setTurnTracker(new TurnTracker(0,0,5,3));
		
		TransferMap BrickMap = new TransferMap();
		
		HeadBrick.setVersion(0);
	}

}
