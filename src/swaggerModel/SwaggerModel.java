package swaggerModel;

import java.util.List;

import shared.model.DevCardList;
import shared.model.Model;
import shared.model.Player;
import shared.model.Resource;
import shared.model.ResourceList;
import shared.model.TurnTracker;
import shared.model.locations.EdgeDirection;
import shared.model.locations.VertexDirection;
import shared.model.message.MessageList;

public class SwaggerModel {
	private ResourceList bank; //our model
	private MessageList chat; //our model
	private MessageList log; //our model
	private SwaggerMap map;
	private List<Player> players; //our model
	private SwaggerTradeOffer tradeOffer; //our model
	private TurnTracker turnTracker; //our model
	private int version;
	private int winner;
	
	public SwaggerModel(Model model) {
		setBank(model.getBank());
		setChat(model.getChat());
		setLog(model.getLog());
		map = new SwaggerMap(model);
		players = model.getPlayers();
		tradeOffer = new SwaggerTradeOffer(model.getTradeOffer());
		turnTracker = model.getTurnTracker();
		version = model.getVersion();
		winner = model.getWinner();
	}

	public ResourceList getBank() {
		return bank;
	}

	public void setBank(ResourceList bank) {
		this.bank = bank;
	}

	public MessageList getChat() {
		return chat;
	}

	public void setChat(MessageList chat) {
		this.chat = chat;
	}

	public MessageList getLog() {
		return log;
	}

	public void setLog(MessageList log) {
		this.log = log;
	}

	public SwaggerMap getMap() {
		return map;
	}

	public void setMap(SwaggerMap map) {
		this.map = map;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public SwaggerTradeOffer getTradeOffer() {
		return tradeOffer;
	}

	public void setTradeOffer(SwaggerTradeOffer tradeOffer) {
		this.tradeOffer = tradeOffer;
	}

	public TurnTracker getTurnTracker() {
		return turnTracker;
	}

	public void setTurnTracker(TurnTracker turnTracker) {
		this.turnTracker = turnTracker;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}
	
	public static String resourceToString(Resource resource) {
		if (resource == null) {
			return null;
		}	
		switch (resource) {
		case WOOD:
			return "Wood";
		case BRICK:
			return "Brick";
		case SHEEP:
			return "Sheep";
		case WHEAT:
			return "Wheat";
		case ORE:
			return "Ore";
		case DESERT:
		default:
			return null;
		}
	}
	
	public static Resource stringToResource(String resource) {
		if (resource == null) {
			return Resource.DESERT;
		}
		switch (resource) {
		case "Wood":
			return Resource.WOOD;
		case "Brick":
			return Resource.BRICK;
		case "Sheep":
			return Resource.SHEEP;
		case "Wheat":
			return Resource.WHEAT;
		case "Ore":
			return Resource.ORE;
		default:
			return null;
		}
	}

	public static String edgeDirectionToString(EdgeDirection direction) {
		if (direction == null) {
			return null;
		}
		switch (direction) {
		case North:
			return "N";
		case NorthWest:
			return "NW";
		case SouthWest:
			return "SW";
		case South:
			return "S";
		case SouthEast:
			return "SE";
		case NorthEast:
			return "NE";
		default:
			return null;
		}
	}

	public static EdgeDirection stringToEdgeDirection(String direction) {
		if (direction == null) {
			return null;
		}
		switch (direction) {
		case "N":
			return EdgeDirection.North;
		case "NW":
			return EdgeDirection.NorthWest;
		case "SW":
			return EdgeDirection.SouthWest;
		case "S":
			return EdgeDirection.South;
		case "SE":
			return EdgeDirection.SouthEast;
		case "NE":
			return EdgeDirection.NorthEast;
		default:
			return null;
		}
	}

	public static String vertexDirectionToString(VertexDirection direction) {
		if (direction == null) {
			return null;
		}
		switch (direction) {
		case West:
			return "W";
		case NorthWest:
			return "NW";
		case NorthEast:
			return "NE";
		case East:
			return "E";
		case SouthEast:
			return "SE";
		case SouthWest:
			return "SW";
		default:
			return null;
		}
	}
	
	public static VertexDirection stringToVertexDirection(String direction) {
		if (direction == null) {
			return null;
		}
		switch (direction) {
		case "W":
			return VertexDirection.West;
		case "NW":
			return VertexDirection.NorthWest;
		case "NE":
			return VertexDirection.NorthEast;
		case "E":
			return VertexDirection.East;
		case "SE":
			return VertexDirection.SouthEast;
		case "SW":
			return VertexDirection.SouthWest;
		default:
			return null;
		}
	}
	//PLAYER
//	private String color = null;
//	private boolean discarded;
//	private int monuments;
//	private DevCardList newDevCards = null;
//	private DevCardList oldDevCards = null;
//	private boolean playedDevCard;
//	private ResourceList resources = new ResourceList(0,0,0,0,0);
//	private ArrayList<City> cities = new ArrayList<City>();
//	private ArrayList<Road> roads = new ArrayList<Road>();
//	private ArrayList<Settlement> settlements = new ArrayList<Settlement>();
//	private ArrayList<Port> ports = new ArrayList<Port>();
//	private int unplacedCities;
//	private int unplacedRoads;
//	private int unplacedSettlements;
//	private int soldiers;
//	private int victoryPoints;
	
	/*
	private SwaggerMap map;
	private List<SwaggerPlayer> players;
	private SwaggerTradeOffer tradeOffer; //our model
	*/
	public Model toOurModel() {
		Model model = new Model();
		model.setBank(this.bank);
		model.setUndrawnDevCards(new DevCardList(2, 5, 2, 14, 2));
		model.setChat(this.chat);
		model.setLog(this.log);
		model.setMap(this.map.toOurMap()); // DO THIS STILL
		model.setPlayers(this.players);
		model.setTradeOffer(this.tradeOffer.toOurTradeOffer());
		model.setTurnTracker(this.getTurnTracker());
		model.setVersion(this.getVersion());
		model.setWinner(this.getWinner());
		return model;
	}
	/*
	private DevCardList undrawnDevCards; - We gotta subtract how much players have from totals
	private GameMap map;
	private Player[] players;
	private TradeOffer tradeOffer;
	*/
}
