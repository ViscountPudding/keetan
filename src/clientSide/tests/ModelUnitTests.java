package clientSide.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import shared.definitions.PortType;
import shared.model.ClientModel;
import shared.model.ModelFacade;
import shared.model.Resource;
import shared.model.ResourceList;
import shared.model.TradeOffer;
import shared.model.gamemap.EdgeValue;
import shared.model.gamemap.Port;
import shared.model.gamemap.VertexValue;
import shared.model.locations.EdgeDirection;
import shared.model.locations.EdgeLocation;
import shared.model.locations.HexLocation;
import shared.model.locations.VertexDirection;
import shared.model.locations.VertexLocation;
import shared.model.pieces.Road;
import shared.model.pieces.Settlement;

public class ModelUnitTests {
	/*@Test
	public void testCanProduceResource() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		ClientModel model = ClientModel.getInstance(false,false,false,false,names);
		ModelFacade facade = ModelFacade.getInstance(false, false, false, false, names);
		
		VertexValue existing = model.getMap().getVertices().get(new VertexLocation(new HexLocation(1,1),VertexDirection.East));
		existing.setSettlement(new Settlement(0));
		facade.UpdateModel(model);
		assertEquals(facade.canProduceResource(new HexLocation(0,0)),false);
		assertEquals(facade.canProduceResource(new HexLocation(1,1)),true);
	}*/
	
	@Test
	public void testCanReceiveResource() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		ClientModel model = ClientModel.getInstance(false,false,false,false,names);
		ModelFacade facade = ModelFacade.getInstance(false, false, false, false, names);
		
		model.setBank(new ResourceList(19,19,19,19,19));
		facade.UpdateModel(model);
		assertEquals(facade.canReceiveResource(5, Resource.BRICK),true);
		
		model.setBank(new ResourceList(0,0,0,0,0));
		facade.UpdateModel(model);
		assertEquals(facade.canReceiveResource(5, Resource.BRICK),false);
	}
	
	@Test
	public void testCanDomesticTrade() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		ClientModel model = ClientModel.getInstance(false,false,false,false,names);
		ModelFacade facade = ModelFacade.getInstance(false, false, false, false, names);
		
		for(int n = 0; n < 5; n++) {
			model.getTurnTracker().endPlayerTurn();
		}
		model.getPlayers()[0].setResources(new ResourceList(5,5,5,5,5));
		model.getPlayers()[1].setResources(new ResourceList(5,5,5,5,5));
		model.getPlayers()[2].setResources(new ResourceList(5,5,5,5,5));
		ResourceList test = new ResourceList(1,1,1,1,1);
		
		facade.UpdateModel(model);
		assertEquals(facade.canDomesticTrade(new TradeOffer(0,2,test,test)),false); //It's player 1's turn
		assertEquals(facade.canDomesticTrade(new TradeOffer(1,2,test,test)),true);
		assertEquals(facade.canDomesticTrade(new TradeOffer(1,3,test,test)),false);
	}
	
	@Test
	public void testCanMaritimeTrade() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		ClientModel model = ClientModel.getInstance(false,false,false,false,names);
		ModelFacade facade = ModelFacade.getInstance(false, false, false, false, names);
		
		model.getPlayers()[0].addPort(new Port(PortType.THREE, new EdgeLocation(new HexLocation(1,1), EdgeDirection.North), new EdgeLocation(new HexLocation(1,1), EdgeDirection.NorthEast)));
		facade.UpdateModel(model);
		assertEquals(facade.canMaritimeTrade(0, Resource.BRICK, Resource.ORE), true);
		
		model.getPlayers()[0].addResource(Resource.BRICK, 3);
		facade.UpdateModel(model);
		assertEquals(facade.canMaritimeTrade(0, Resource.BRICK, Resource.ORE), true);
		
		model.getBank().setOre(5);
		facade.UpdateModel(model);
		assertEquals(facade.canMaritimeTrade(0, Resource.BRICK, Resource.ORE), true);
		
		model.getPlayers()[0].removeResource(Resource.BRICK, 3);
		model.getPlayers()[0].addPort(new Port(PortType.BRICK, new EdgeLocation(new HexLocation(1,1), EdgeDirection.North), new EdgeLocation(new HexLocation(1,1), EdgeDirection.NorthEast)));
		model.getBank().setOre(0);
		facade.UpdateModel(model);
		assertEquals(facade.canMaritimeTrade(0, Resource.BRICK, Resource.ORE), false);
		
		model.getPlayers()[0].addResource(Resource.BRICK, 3);
		facade.UpdateModel(model);
		assertEquals(facade.canMaritimeTrade(0, Resource.BRICK, Resource.ORE), false);
		
		model.getBank().setOre(5);
		facade.UpdateModel(model);
		assertEquals(facade.canMaritimeTrade(0, Resource.BRICK, Resource.ORE), true);
	}
	
	@Test
	public void testCanBuildRoad() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		ClientModel model = ClientModel.getInstance(false,false,false,false,names);
		ModelFacade facade = ModelFacade.getInstance(false, false, false, false, names);
		EdgeValue test = new EdgeValue(new EdgeLocation(new HexLocation(1,1),EdgeDirection.NorthEast));
		
		facade.UpdateModel(model);
		assertEquals(facade.canBuildRoad(0, test),false);
		
		model.getPlayers()[0].setResources(new ResourceList(5,5,5,5,5));
		facade.UpdateModel(model);
		assertEquals(facade.canBuildRoad(0, test),false);
		
		model.getMap().getEdges().get(new EdgeLocation(new HexLocation(1,1),EdgeDirection.North)).setRoad(new Road(0));
		facade.UpdateModel(model);
		assertEquals(facade.canBuildRoad(0, test),false);
		
		model.getPlayers()[0].setUnplacedRoads(0);
		facade.UpdateModel(model);
		assertEquals(facade.canBuildRoad(0, test),false);
	}
	
	/*@Test
	public void testCanBuildSettlement() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		ClientModel model = ClientModel.getInstance(false,false,false,false,names);
		ModelFacade facade = ModelFacade.getInstance(false, false, false, false, names);
		
		VertexValue existing = model.getMap().getVertices().get(new VertexLocation(new HexLocation(1,1),VertexDirection.East));
		VertexValue failTest = model.getMap().getVertices().get(new VertexLocation(new HexLocation(1,1),VertexDirection.NorthEast));
		VertexValue goodTest = model.getMap().getVertices().get(new VertexLocation(new HexLocation(1,1),VertexDirection.NorthWest));
		existing.setSettlement(new Settlement(1));
		EdgeValue existingR = model.getMap().getEdges().get(new EdgeLocation(new HexLocation(1,1),EdgeDirection.North));
		existingR.setRoad(new Road(0));
		
		facade.UpdateModel(model);
		assertEquals(facade.canBuildSettlement(0, goodTest),false);
		
		model.getPlayers()[0].setResources(new ResourceList(5,5,5,5,5));
		facade.UpdateModel(model);
		assertEquals(facade.canBuildSettlement(0, failTest),false);
		assertEquals(facade.canBuildSettlement(0, goodTest),true);

		model.getPlayers()[0].setUnplacedSettlements(0);
		facade.UpdateModel(model);
		assertEquals(facade.canBuildSettlement(0, goodTest),false);

		model.getPlayers()[0].setUnplacedSettlements(5);
		existingR.setRoad(null);
		facade.UpdateModel(model);
		assertEquals(facade.canBuildSettlement(0, goodTest),false);
	}*/
	
	@Test
	public void testCanBuildCity() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		ClientModel model = ClientModel.getInstance(false,false,false,false,names);
		ModelFacade facade = ModelFacade.getInstance(false, false, false, false, names);

		VertexValue failTest = model.getMap().getVertices().get(new VertexLocation(new HexLocation(1,1),VertexDirection.NorthEast));
		VertexValue goodTest = model.getMap().getVertices().get(new VertexLocation(new HexLocation(1,1),VertexDirection.NorthWest));
		goodTest.setSettlement(new Settlement(0));
		
		facade.UpdateModel(model);
		assertEquals(facade.canBuildCity(0, goodTest),false);
		
		model.getPlayers()[0].setResources(new ResourceList(5,5,5,5,5));
		facade.UpdateModel(model);
		assertEquals(facade.canBuildCity(0, goodTest),false);
		assertEquals(facade.canBuildCity(0, failTest),false);
		
		model.getPlayers()[0].setUnplacedCities(0);
		facade.UpdateModel(model);
		assertEquals(facade.canBuildCity(0, goodTest),false);
	}
	
	@Test
	public void testCanBuyDevelopmentCard() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		ClientModel model = ClientModel.getInstance(false,false,false,false,names);
		ModelFacade facade = ModelFacade.getInstance(false, false, false, false, names);
		
		facade.UpdateModel(model);
		assertEquals(facade.canBuyDevelopmentCard(0),false);
		
		model.getPlayers()[1].setResources(new ResourceList(5,5,5,5,5));
		facade.UpdateModel(model);
		assertEquals(facade.canBuyDevelopmentCard(1),true);
		
		model.getTurnTracker().endPlayerTurn();
		facade.UpdateModel(model);
		assertEquals(facade.canBuyDevelopmentCard(0),false);
	}
	
	@Test
	public void testCanLoseCardsFromDieRoll() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		ClientModel model = ClientModel.getInstance(false,false,false,false,names);
		ModelFacade facade = ModelFacade.getInstance(false, false, false, false, names);
		
		facade.UpdateModel(model);
		assertEquals(facade.canLoseCardsFromDieRoll(0),true);
		
		model.getPlayers()[0].setResources(new ResourceList(5,5,5,5,5));
		facade.UpdateModel(model);
		assertEquals(facade.canLoseCardsFromDieRoll(0),true);
	}
	
	/*@Test
	public void testCanLoseCardsFromRobber() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		ClientModel model = ClientModel.getInstance(false,false,false,false,names);
		ModelFacade facade = ModelFacade.getInstance(false, false, false, false, names);
		
		VertexValue existing = model.getMap().getVertices().get(new VertexLocation(new HexLocation(1,1),VertexDirection.East));
		existing.setSettlement(new Settlement(0));
		model.getMap().setRobber(new HexLocation(1,1));
		facade.UpdateModel(model);
		assertEquals(facade.canLoseCardsFromRobber(0),false);
		
		model.getPlayers()[0].setResources(new ResourceList(2,2,2,2,2));
		facade.UpdateModel(model);
		assertEquals(facade.canLoseCardsFromRobber(0),true);

		model.getMap().setRobber(new HexLocation(-1,-1));
		facade.UpdateModel(model);
		assertEquals(facade.canLoseCardsFromRobber(0),false);
	}*/
	
	@Test
	public void testCanWin() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		ClientModel model = ClientModel.getInstance(false,false,false,false,names);
		ModelFacade facade = ModelFacade.getInstance(false, false, false, false, names);
		
		facade.UpdateModel(model);
		assertEquals(facade.canWin(0),false);
		
		model.getPlayers()[0].setVictoryPoints(10);
		facade.UpdateModel(model);
		assertEquals(facade.canWin(0),false);
		
		model.getTurnTracker().endPlayerTurn();
		facade.UpdateModel(model);
		assertEquals(facade.canWin(0),false);
	}
	
	
	public static void main(String[] args) {

		String[] testClasses = new String[] {
				"clientSide.Phase1UnitTests"
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}

}
