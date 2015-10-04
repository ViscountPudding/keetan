package clientSide.tests;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.model.ModelFacade;
import shared.transferClasses.CreateGameRequest;
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

	public static void main(String[] args) {

		String[] testClasses = new String[] {
				"clientSide.tests.ModelUnitTests",
				"clientSide.tests.ProxyServerUnitTests",
				"clientSide.tests.ServerPollerUnitTests"
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}
}
