package clientSide.tests;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.model.ModelFacade;
import clientSide.server.ClientServerFacade;
import clientSide.server.IServer;

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
		//IServer server = new MockServer();
		//ServerPoler poller = new ServerPoller();
	}
	
	@Test
	public void serverProxyTests_1() {
		IServer server = new ClientServerFacade("localhost:8081");
		//ServerPoler poller = new ServerPoller();
	}

	public static void main(String[] args) {

		String[] testClasses = new String[] {
				"clientSide.Phase1UnitTests"
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}
}
