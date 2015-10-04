package clientSide.tests;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.model.ModelFacade;
import shared.transferClasses.UserCredentials;
import clientSide.exceptions.ServerException;
import clientSide.server.ClientServerFacade;
import clientSide.server.IServer;
import clientSide.server.MockServer;
import clientSide.server.ServerPoller;

public class Phase1UnitTests {
	@Before
	public void setup() {
	}
	
	@After
	public void teardown() {
	}
	
	@Test
	public void serverPollerTest_1() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Alice");
		names.add("Bob");
		names.add("Eve");
		names.add("Adam");
		ModelFacade modelFacade = ModelFacade.getInstance(false, false, false, false, names);
		IServer server = new MockServer();
		ServerPoller poller = new ServerPoller(server, modelFacade);
		poller.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			assert(false);
		}
	}
	
	@Test
	public void serverProxyTests_1() {
		IServer server = new ClientServerFacade("localhost:8081");
		UserCredentials creds = new UserCredentials("Pigs", "canFly");
		try {
			server.register(creds);
			server.login(creds);
		}
		catch (ServerException e) {
			System.out.println(e.getReason());
		}
	}

	public static void main(String[] args) {

		String[] testClasses = new String[] {
				"clientSide.Phase1UnitTests"
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}
}
