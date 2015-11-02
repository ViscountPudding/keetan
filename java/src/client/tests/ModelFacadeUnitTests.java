package client.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.CatanColor;
import shared.transferClasses.CreateGameRequest;
import shared.transferClasses.CreateGameResponse;
import shared.transferClasses.JoinGameRequest;
import shared.transferClasses.UserCredentials;
import client.exceptions.ServerException;
import client.model.DevCardList;
import client.model.MessageList;
import client.model.ModelFacade;
import client.model.Player;
import client.model.ResourceList;
import client.model.TransferMap;
import client.model.TransferModel;
import client.model.TurnTracker;
import client.server.ServerProxy;

public class ModelFacadeUnitTests {

	@Before
	public void setup() {
		UserCredentials fu = new UserCredentials("Fu_", "Wind_");
		UserCredentials rin = new UserCredentials("Rin", "Forest");
		UserCredentials ka = new UserCredentials("Ka_", "Fire_");
		UserCredentials zan = new UserCredentials("Zan", "Mountain");
		
		ServerProxy.initialize("localhost:8081");
		
		try {
			ServerProxy.register(fu);
			ServerProxy.register(rin);
			ServerProxy.register(ka);
			ServerProxy.register(zan);
		}	catch (ServerException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		}
		
		try {

			
			ServerProxy.login(fu);
			CreateGameRequest brickRequest = new CreateGameRequest(false, false, false, "Kagemusha");
			CreateGameResponse brickResponse = ServerProxy.createGame(brickRequest);

			ServerProxy.joinGame(new JoinGameRequest(brickResponse.getId(), CatanColor.BLUE));
			
			ServerProxy.login(rin);
			ServerProxy.joinGame(new JoinGameRequest(brickResponse.getId(), CatanColor.GREEN));
			
			ServerProxy.login(ka);
			ServerProxy.joinGame(new JoinGameRequest(brickResponse.getId(), CatanColor.RED));
			
			ServerProxy.login(zan);
			ServerProxy.joinGame(new JoinGameRequest(brickResponse.getId(), CatanColor.PURPLE));
			
			
			
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() {
		
		TransferModel headBrick = null;
		
		try {
			headBrick = ServerProxy.getModel(-1);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(headBrick == null) {
			fail("Didn't get a transfer model");
		}
		
		testUpdateModel(headBrick);
		
	}
	
	public void testUpdateModel(TransferModel headBrick) {
		
		ModelFacade.updateModel(headBrick);
		
		assertEquals(19, ModelFacade.getHexes().size());
		assertEquals(4, headBrick.getPlayers().size());	
	}
	
	public void testCanProduceResource() {
		
	}

}
