package clientSide.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import shared.model.Resource;
import shared.model.TradeOffer;
import shared.model.gamemap.Direction;
import shared.model.locations.HexLocation;
import shared.transferClasses.AcceptTrade;
import shared.transferClasses.AddAIRequest;
import shared.transferClasses.BuildCity;
import shared.transferClasses.BuildRoad;
import shared.transferClasses.BuildSettlement;
import shared.transferClasses.BuyDevCard;
import shared.transferClasses.CreateGameRequest;
import shared.transferClasses.EdgeLocationSwag;
import shared.transferClasses.FinishTurn;
import shared.transferClasses.Game;
import shared.transferClasses.JoinGameRequest;
import shared.transferClasses.MaritimeTrade;
import shared.transferClasses.Monopoly;
import shared.transferClasses.Monument;
import shared.transferClasses.RoadBuilding;
import shared.transferClasses.RobPlayer;
import shared.transferClasses.RollNumber;
import shared.transferClasses.SendChat;
import shared.transferClasses.Soldier;
import shared.transferClasses.UserCredentials;
import shared.transferClasses.YearOfPlenty;
import clientSide.exceptions.ServerException;
import clientSide.server.ClientServerFacade;
import clientSide.server.IServer;

public class ServerProxyUnitTests {
	private IServer server;
	private Random rand;
	
	@Before
	public void setup() {
		server = new ClientServerFacade("localhost:8081");
		rand = new Random();
	}
	
	@After
	public void teardown() {
	}

	@Test
	public void userRegisterTest() {
		UserCredentials creds = new UserCredentials("Pig" + rand.nextInt(999999), "canFly");
		try {
			server.register(creds);
		}
		catch (ServerException e) {
			if (!e.getReason().equals("Failed to register - someone already has that username.")) {
				assertTrue(false);
			}
		}
	}
	
	@Test
	public void userLoginTest() {
		UserCredentials creds = new UserCredentials("Pig", "canFly");
		try {
			server.register(creds);
			server.login(creds);
		}
		catch (ServerException e) {
			try {
				server.login(creds);
			} catch (ServerException e1) {
				if (e1.getReason().equals("An IOException occurred")) {
					assertTrue(false);
				}
			}
		}
	}
	
	@Test
	public void listGamesTest() {
		try {
			ArrayList<Game> games = server.getGamesList();
		}
		catch (ServerException e) {
			if (e.getReason().equals("An IOException occurred")) {
				assertTrue(false);
			}
		}
	}
	
	@Test
	public void createGameTest() {
		try {
			server.createGame(new CreateGameRequest(false, false, false, "Game name"));
		}
		catch (ServerException e) {
			if (e.getReason().equals("An IOException occurred")) {
				assertTrue(false);
			}
		}
	}
	
	@Test
	public void joinGameTest() {
		try {
			server.joinGame(new JoinGameRequest(8, "red"));
		}
		catch (ServerException e) {
			if (e.getReason().equals("An IOException occurred")) {
				assertTrue(false);
			}
		}
	}

	@Test
	public void addAITest() {
		try {
			server.addAI(new AddAIRequest("LARGEST_ARMY"));
		}
		catch (ServerException e) {
			if (e.getReason().equals("An IOException occurred")) {
				assertTrue(false);
			}
		}
	}
	
	@Test
	public void listAITypesTest() {
		try {
			ArrayList<String> aitypes = server.listAITypes();
		}
		catch (ServerException e) {
			if (e.getReason().equals("An IOException occurred")) {
				assertTrue(false);
			}
		}
	}
	
	@Test
	public void sendChatTest() {
		try {
			server.sendChat(new SendChat(0, "Heya!"));
		}
		catch (ServerException e) {
			if (e.getReason().equals("An IOException occurred")) {
				assertTrue(false);
			}
		}
	}
	
	@Test
	public void serverProxyTests_1() {
			server.rollDice(new RollNumber(0, 7));
			server.robPlayer(new RobPlayer(0, 1, new HexLocation(0, 0)));
			server.finishTurn(new FinishTurn(0));
			server.buyDevCard(new BuyDevCard(0));
			server.yearOfPlenty(new YearOfPlenty(0, Resource.BRICK, Resource.ORE));
			server.roadBuilding(new RoadBuilding(0, new EdgeLocationSwag(0,0,Direction.North), new EdgeLocationSwag(0,1,Direction.North))); // we use different edge location
			server.soldier(new Soldier(0, 0, new HexLocation(0, 0)));
			server.monopoly(new Monopoly(0, Resource.SHEEP));
			server.monument(new Monument(0));
			server.buildRoad(new BuildRoad(0, null, true));
			server.buildSettlement(new BuildSettlement(0, null, null));
			server.buildCity(new BuildCity(0, null, null));
			server.offerTrade(new TradeOffer(0, 0, null, null));
			server.respondToTrade(new AcceptTrade(0, null));
		server.maritimeTrade(new MaritimeTrade(0, 0, null, null));
		}
}
