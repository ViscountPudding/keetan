package clientSide.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.transferClasses.CreateGameRequest;
import shared.transferClasses.UserCredentials;
import clientSide.exceptions.ServerException;
import clientSide.server.ClientServerFacade;
import clientSide.server.IServer;

public class ServerProxyUnitTests {
	@Before
	public void setup() {
	}
	
	@After
	public void teardown() {
	}
	
	@Test
	public void serverProxyTests_1() {
		System.out.println("Starting ServerProxyTests");
		IServer server = new ClientServerFacade("localhost:8081");
		UserCredentials creds = new UserCredentials("Pigmasa", "canFly");
		try {
			server.register(creds);
			server.login(creds);
			server.createGame(new CreateGameRequest(false, false, false, "Game name"));
		}
		catch (ServerException e) {
			System.out.println(e.getReason());
		}
	}
}
