package client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.model.ModelFacade;
import client.server.IServer;
import client.server.MockServer;
import client.server.ServerPoller;

public class ServerPollerUnitTests {
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
		ModelFacade modelFacade = ModelFacade.createInstance(false, false, false, false, names);
		IServer server = new MockServer();
		ServerPoller poller = new ServerPoller(server, modelFacade);
		poller.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			assert(false);
		}

		assertEquals(modelFacade.getModelVersion(), 5);
		assertFalse(modelFacade.canReceiveResource(1, Resource.BRICK));
		poller.stop();
	}
}
