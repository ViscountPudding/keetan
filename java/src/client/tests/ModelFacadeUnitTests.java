package client.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import shared.definitions.CatanColor;
import client.model.Player;
import client.model.TransferModel;

public class ModelFacadeUnitTests {

	@Test
	public void test() {
		fail("Not yet implemented");
		
		TransferModel HeadBrick = new TransferModel();
	
		List<Player> brickPlayers = new ArrayList<Player>();
		
		brickPlayers.add(new Player("Fu", 0, CatanColor.BLUE, 123));
		brickPlayers.add(new Player("Rin", 1, CatanColor.GREEN, 456));
		brickPlayers.add(new Player("Ka", 2, CatanColor.RED, 789));
		
		
	}

}
