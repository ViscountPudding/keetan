package clientSide.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Test;

import shared.definitions.PortType;
import shared.model.Model;
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
import shared.model.pieces.City;
import shared.model.pieces.Road;
import shared.model.pieces.Settlement;

public class ModelUnitTests {
	@Test
	public void testCanProduceResource() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		Model model = new Model(false, false, false, false, names);
		ModelFacade facade = ModelFacade.createInstance(false, false, false, false, names);
		facade.updateModel(model);
		
		Set<HexLocation> hexLocations = model.getMap().getHexes().keySet();
		ArrayList<VertexDirection> vertexDirections = new ArrayList<VertexDirection>();
		vertexDirections.add(VertexDirection.East);
		vertexDirections.add(VertexDirection.NorthEast);
		vertexDirections.add(VertexDirection.NorthWest);
		vertexDirections.add(VertexDirection.West);
		vertexDirections.add(VertexDirection.SouthWest);
		vertexDirections.add(VertexDirection.SouthEast);

		HexLocation twoTwo = new HexLocation(2, 2);
		HexLocation twoOne = new HexLocation(2, 1);
		HexLocation oneTwo = new HexLocation(1, 2);
		HexLocation oneOne = new HexLocation(1, 1);
		HexLocation negTwoNegTwo = new HexLocation(-2, -2);
		HexLocation nonAdjacentHexLocation;
		for (HexLocation hexLocation : hexLocations) {
			if (hexLocation.equals(twoTwo) || hexLocation.equals(twoOne) || hexLocation.equals(oneTwo) || hexLocation.equals(oneOne)) {
				nonAdjacentHexLocation = negTwoNegTwo;
			}
			else {
				nonAdjacentHexLocation = twoTwo;
			}
			
			for (VertexDirection vertexDirection : vertexDirections) {
				model.getMap().setRobber(nonAdjacentHexLocation);
				model.getPlayers()[0].setResources(new ResourceList(0,0,0,0,0));
				VertexValue vertex = model.getMap().getVertices().get(new VertexLocation(hexLocation,vertexDirection).getNormalizedLocation());

				model.getMap().setRobber(nonAdjacentHexLocation);
				assertEquals(facade.canProduceResource(hexLocation),false); // no municipality
				vertex.setSettlement(new Settlement(0));
				assertEquals(facade.canProduceResource(hexLocation),true); // should work
				model.getMap().setRobber(hexLocation);
				assertEquals(facade.canProduceResource(hexLocation),true); // robber should stop production
				assertEquals(facade.canProduceResource(nonAdjacentHexLocation),false); // no municipality
				vertex.setSettlement(null);
				assertEquals(facade.canProduceResource(hexLocation),false); // no municipality and robber
				

				model.getMap().setRobber(nonAdjacentHexLocation);
				assertEquals(facade.canProduceResource(hexLocation),false); // no municipality
				vertex.setCity(new City(0));
				assertEquals(facade.canProduceResource(hexLocation),true); // should work
				model.getMap().setRobber(hexLocation);
				assertEquals(facade.canProduceResource(hexLocation),true); // robber should stop production
				assertEquals(facade.canProduceResource(nonAdjacentHexLocation),false); // no municipality
				vertex.setCity(null);
				assertEquals(facade.canProduceResource(hexLocation),false); // no municipality and robber
			}
		}
		
		
		VertexValue existing = model.getMap().getVertices().get(new VertexLocation(new HexLocation(1,1),VertexDirection.East).getNormalizedLocation());
		existing.setSettlement(new Settlement(0));
		assertEquals(facade.canProduceResource(new HexLocation(0,0)),false);
		assertEquals(facade.canProduceResource(new HexLocation(1,1)),true);
	}
	
	@Test
	public void testCanReceiveResource() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		Model model = new Model(false, false, false, false, names);
		ModelFacade facade = ModelFacade.createInstance(false, false, false, false, names);
		facade.updateModel(model);
		
		model.setBank(new ResourceList(19,19,19,19,19));
		assertEquals(facade.canReceiveResource(5, Resource.BRICK),true); // bank has plenty to give
		
		model.setBank(new ResourceList(0,0,0,0,0));
		assertEquals(facade.canReceiveResource(5, Resource.BRICK),false);// bank can give nothing
	}
	
	@Test
	public void testCanDomesticTrade() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		Model model = new Model(false, false, false, false, names);
		ModelFacade facade = ModelFacade.createInstance(false, false, false, false, names);
		facade.updateModel(model);
		
		model.getTurnTracker().setPlayerTurn(0);
		model.getPlayers()[0].setResources(new ResourceList(5,5,5,5,5));
		model.getPlayers()[1].setResources(new ResourceList(5,5,5,5,5));
		model.getPlayers()[2].setResources(new ResourceList(5,5,5,5,5));
		model.getPlayers()[3].setResources(new ResourceList(0,0,0,0,0));
		
		ResourceList oneOfAll = new ResourceList(1,1,1,1,1);
		ResourceList nothing = new ResourceList(0,0,0,0,0);
		ResourceList twoSheep = new ResourceList(0,0,2,0,0);
		ResourceList threeOre = new ResourceList(0,3,0,0,0);

		assertEquals(facade.canDomesticTrade(new TradeOffer(0,2,oneOfAll,oneOfAll)),true); // should work
		assertEquals(facade.canDomesticTrade(new TradeOffer(1,2,oneOfAll,oneOfAll)),false); // not player 1's turn
		assertEquals(facade.canDomesticTrade(new TradeOffer(0,0,oneOfAll,oneOfAll)),true); // cannot trade with self
		assertEquals(facade.canDomesticTrade(new TradeOffer(0,3,oneOfAll,oneOfAll)),false); // player 3 has doesn't have enough cards
		model.getPlayers()[0].setResources(new ResourceList(0,0,0,0,0));
		assertEquals(facade.canDomesticTrade(new TradeOffer(0,2,oneOfAll,oneOfAll)),false); // player 1 doesn't have enough cards
		
		model.getPlayers()[0].setResources(new ResourceList(0,3,0,0,0));
		assertEquals(facade.canDomesticTrade(new TradeOffer(0,2,threeOre,twoSheep)),true); // should work
		model.getPlayers()[2].setResources(new ResourceList(0,0,2,0,0));
		assertEquals(facade.canDomesticTrade(new TradeOffer(0,2,threeOre,twoSheep)),true); // should work
		model.getPlayers()[0].setResources(new ResourceList(5,0,5,5,5));
		assertEquals(facade.canDomesticTrade(new TradeOffer(0,2,threeOre,twoSheep)),false); // not enough ore
		model.getPlayers()[2].setResources(new ResourceList(5,5,0,5,5));
		assertEquals(facade.canDomesticTrade(new TradeOffer(0,2,threeOre,twoSheep)),false); // not enough ore, not enough sheep
		model.getPlayers()[0].setResources(new ResourceList(5,5,5,5,5));
		assertEquals(facade.canDomesticTrade(new TradeOffer(0,2,threeOre,twoSheep)),false); // not enough sheep

		model.getPlayers()[0].setResources(new ResourceList(5,5,5,5,5));
		model.getPlayers()[1].setResources(new ResourceList(5,5,5,5,5));
		assertEquals(facade.canDomesticTrade(new TradeOffer(0,1,nothing,nothing)),true); // should work

		model.getPlayers()[0].setResources(new ResourceList(0,0,0,0,0));
		model.getPlayers()[1].setResources(new ResourceList(0,0,0,0,0));
		assertEquals(facade.canDomesticTrade(new TradeOffer(0,1,nothing,nothing)),true); // should work

		model.getPlayers()[1].setResources(new ResourceList(0,0,0,0,0));
		model.getPlayers()[0].setResources(new ResourceList(5,5,5,5,5));
		assertEquals(facade.canDomesticTrade(new TradeOffer(0,1,nothing,nothing)),true); // should work
		
		model.getPlayers()[0].setResources(new ResourceList(0,0,0,0,0));
		model.getPlayers()[1].setResources(new ResourceList(5,5,5,5,5));
		assertEquals(facade.canDomesticTrade(new TradeOffer(0,1,nothing,nothing)),true); // should work
	}
	
	@Test
	public void testCanMaritimeTrade() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		Model model = new Model(false, false, false, false, names);
		ModelFacade facade = ModelFacade.createInstance(false, false, false, false, names);
		facade.updateModel(model);
		
		ArrayList<Port> ports = model.getMap().getPorts();
		for (Port port : ports) {
			
		}
		
		model.getPlayers()[0].addPort(new Port(PortType.THREE, new EdgeLocation(new HexLocation(1,1), EdgeDirection.North), new EdgeLocation(new HexLocation(1,1), EdgeDirection.NorthEast)));
		assertEquals(facade.canMaritimeTrade(0, Resource.BRICK, Resource.ORE), false);

		model.getBank().setOre(0);
		model.getPlayers()[0].addResource(Resource.BRICK, 3);
		assertEquals(facade.canMaritimeTrade(0, Resource.BRICK, Resource.ORE), false);
		
		model.getBank().setOre(5);
		assertEquals(facade.canMaritimeTrade(0, Resource.BRICK, Resource.ORE), true);
		
		model.getPlayers()[0].removeResource(Resource.BRICK, 3);
		model.getPlayers()[0].addPort(new Port(PortType.BRICK, new EdgeLocation(new HexLocation(1,1), EdgeDirection.North), new EdgeLocation(new HexLocation(1,1), EdgeDirection.NorthEast)));
		model.getBank().setOre(0);
		assertEquals(facade.canMaritimeTrade(0, Resource.BRICK, Resource.ORE), false);
		
		model.getPlayers()[0].addResource(Resource.BRICK, 3);
		assertEquals(facade.canMaritimeTrade(0, Resource.BRICK, Resource.ORE), false);
		
		model.getBank().setOre(5);
		assertEquals(facade.canMaritimeTrade(0, Resource.BRICK, Resource.ORE), true);
	}
	
	@Test
	public void testCanBuildRoad() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		Model model = new Model(false, false, false, false, names);
		ModelFacade facade = ModelFacade.createInstance(false, false, false, false, names);
		facade.updateModel(model);
		
		EdgeValue test = new EdgeValue(new EdgeLocation(new HexLocation(1,1),EdgeDirection.NorthEast));
		
		assertEquals(facade.canBuildRoad(0, test),false);
		
		model.getPlayers()[0].setResources(new ResourceList(5,5,5,5,5));
		assertEquals(facade.canBuildRoad(0, test),false);
		
		model.getMap().getEdges().get(new EdgeLocation(new HexLocation(1,1),EdgeDirection.North)).setRoad(new Road(0));
		assertEquals(facade.canBuildRoad(0, test),false);
		
		model.getPlayers()[0].setUnplacedRoads(0);
		assertEquals(facade.canBuildRoad(0, test),false);
	}
	
	@Test
	public void testCanBuildSettlement() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		Model model = new Model(false, false, false, false, names);
		ModelFacade facade = ModelFacade.createInstance(false, false, false, false, names);
		facade.updateModel(model);
		
		VertexValue existing = model.getMap().getVertices().get(new VertexLocation(new HexLocation(-2,0),VertexDirection.East).getNormalizedLocation());
		VertexValue failTest = model.getMap().getVertices().get(new VertexLocation(new HexLocation(-2,0),VertexDirection.NorthEast).getNormalizedLocation());
		VertexValue goodTest = model.getMap().getVertices().get(new VertexLocation(new HexLocation(-2,0),VertexDirection.SouthEast).getNormalizedLocation());
		existing.setSettlement(new Settlement(1));
		EdgeValue existingR = model.getMap().getEdges().get(new EdgeLocation(new HexLocation(-2,0),EdgeDirection.North).getNormalizedLocation());
		existingR.setRoad(new Road(0));
		
		assertEquals(facade.canBuildSettlement(0, goodTest),false);
		
		model.getPlayers()[0].setResources(new ResourceList(5,5,5,5,5));
		assertEquals(facade.canBuildSettlement(0, failTest),false);
		
		model.getPlayers()[0].setUnplacedSettlements(5);
		assertEquals(facade.canBuildSettlement(0, goodTest),true);

		model.getPlayers()[0].setUnplacedSettlements(0);
		assertEquals(facade.canBuildSettlement(0, goodTest),false);

		model.getPlayers()[0].setUnplacedSettlements(5);
		existingR.setRoad(null);
		assertEquals(facade.canBuildSettlement(0, goodTest),false);
	}
	
	@Test
	public void testCanBuildCity() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		
		Model model = new Model(false, false, false, false, names);
		ModelFacade facade = ModelFacade.createInstance(false, false, false, false, names);
		facade.updateModel(model);

		VertexValue failTest = model.getMap().getVertices().get(new VertexLocation(new HexLocation(1,1),VertexDirection.NorthEast));
		VertexValue goodTest = model.getMap().getVertices().get(new VertexLocation(new HexLocation(1,1),VertexDirection.NorthWest));
		goodTest.setSettlement(new Settlement(0));
		
		assertEquals(facade.canBuildCity(0, goodTest),false);
		
		model.getPlayers()[0].setResources(new ResourceList(5,5,5,5,5));
		assertEquals(facade.canBuildCity(0, goodTest),false);
		assertEquals(facade.canBuildCity(0, failTest),false);
		
		model.getPlayers()[0].setUnplacedCities(0);
		assertEquals(facade.canBuildCity(0, goodTest),false);
	}
	
	@Test
	public void testCanBuyDevelopmentCard() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		Model model = new Model(false, false, false, false, names);
		ModelFacade facade = ModelFacade.createInstance(false, false, false, false, names);
		facade.updateModel(model);
		
		assertEquals(facade.canBuyDevelopmentCard(0),false); // does not have enough resources

		model.getTurnTracker().setPlayerTurn(1);
		assertEquals(facade.canBuyDevelopmentCard(0),false); // is not his turn
		
		model.getPlayers()[1].setResources(new ResourceList(5,5,5,5,5));
		assertEquals(facade.canBuyDevelopmentCard(1), true); //should be able to
	}
	
	@Test
	public void testCanLoseCardsFromDieRoll() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		Model model = new Model(false, false, false, false, names);
		ModelFacade facade = ModelFacade.createInstance(false, false, false, false, names);
		facade.updateModel(model);
		
		assertEquals(facade.canLoseCardsFromDieRoll(0),false); // does not have cards
		
		model.getPlayers()[0].setResources(new ResourceList(5,5,5,5,5));
		model.getPlayers()[1].setResources(new ResourceList(5,5,5,5,5));
		
		assertEquals(facade.canLoseCardsFromDieRoll(0),true); // has too many
		assertEquals(facade.canLoseCardsFromDieRoll(1),true); // has too many
	}
	
	@Test
	public void testCanLoseCardsFromRobber() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		Model model = new Model(false, false, false, false, names);
		ModelFacade facade = ModelFacade.createInstance(false, false, false, false, names);
		facade.updateModel(model);
		
		
		Set<HexLocation> hexLocations = model.getMap().getHexes().keySet();
		ArrayList<VertexDirection> vertexDirections = new ArrayList<VertexDirection>();
		vertexDirections.add(VertexDirection.East);
		vertexDirections.add(VertexDirection.NorthEast);
		vertexDirections.add(VertexDirection.NorthWest);
		vertexDirections.add(VertexDirection.West);
		vertexDirections.add(VertexDirection.SouthWest);
		vertexDirections.add(VertexDirection.SouthEast);

		HexLocation twoTwo = new HexLocation(2, 2);
		HexLocation twoOne = new HexLocation(2, 1);
		HexLocation oneTwo = new HexLocation(1, 2);
		HexLocation oneOne = new HexLocation(1, 1);
		HexLocation negTwoNegTwo = new HexLocation(-2, -2);
		HexLocation nonAdjacentHexLocation;
		for (HexLocation hexLocation : hexLocations) {
			if (hexLocation.equals(twoTwo) || hexLocation.equals(twoOne) || hexLocation.equals(oneTwo) || hexLocation.equals(oneOne)) {
				nonAdjacentHexLocation = negTwoNegTwo;
			}
			else {
				nonAdjacentHexLocation = twoTwo;
			}
			
			for (VertexDirection vertexDirection : vertexDirections) {
				model.getMap().setRobber(nonAdjacentHexLocation);
				model.getPlayers()[0].setResources(new ResourceList(0,0,0,0,0));
				VertexValue vertex = model.getMap().getVertices().get(new VertexLocation(hexLocation,vertexDirection).getNormalizedLocation());
	
				vertex.setSettlement(new Settlement(0));
	
				model.getMap().setRobber(hexLocation);
				assertEquals(facade.canLoseCardsFromRobber(0), false); // player has no resources to steal
	
				model.getPlayers()[1].setResources(new ResourceList(2,2,2,2,2));
				assertEquals(facade.canLoseCardsFromRobber(1), false); // player has no settlement adjacent to the robber's hex
				
				model.getPlayers()[0].setResources(new ResourceList(2,2,2,2,2));
				assertEquals(facade.canLoseCardsFromRobber(0), true); // should be able to
		
				model.getMap().setRobber(nonAdjacentHexLocation);
				assertEquals(facade.canLoseCardsFromRobber(0), false); // robber moved to non adjacent hex
	
				vertex.setSettlement(null);
				vertex.setCity(new City(0));
	
				model.getMap().setRobber(hexLocation);
				model.getPlayers()[0].setResources(new ResourceList(0,0,0,0,0));
				assertEquals(facade.canLoseCardsFromRobber(0), false); // player has no resources to steal
	
				model.getPlayers()[1].setResources(new ResourceList(2,2,2,2,2));
				assertEquals(facade.canLoseCardsFromRobber(1), false); // player has no settlement adjacent to the robber's hex
				
				model.getPlayers()[0].setResources(new ResourceList(2,2,2,2,2));
				assertEquals(facade.canLoseCardsFromRobber(0), true); // should be able to
		
				model.getMap().setRobber(nonAdjacentHexLocation);
				assertEquals(facade.canLoseCardsFromRobber(0), false); // robber no longer on hex
				vertex.setCity(null);
				//System.out.println("Worked for Hex: " + hexLocation);
			}
		}
	}
	
	@Test
	public void testCanWin() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Michael");
		names.add("Stephen");
		names.add("Josh");
		names.add("Will");
		Model model = new Model(false, false, false, false, names);
		ModelFacade facade = ModelFacade.createInstance(false, false, false, false, names);
		facade.updateModel(model);
		
		assertEquals(facade.canWin(0),false);
		
		model.getPlayers()[0].setVictoryPoints(10);
		assertEquals(facade.canWin(0), true);
		
		model.getTurnTracker().endPlayerTurn();
		assertEquals(facade.canWin(0),false);
	}
	
	
	public static void main(String[] args) {

		String[] testClasses = new String[] {
				"clientSide.Phase1UnitTests"
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}

}