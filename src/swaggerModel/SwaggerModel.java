package swaggerModel;

import java.util.List;

import shared.model.Model;
import shared.model.Resource;
import shared.model.ResourceList;
import shared.model.TradeOffer;
import shared.model.TurnTracker;
import shared.model.gamemap.Direction;
import shared.model.locations.EdgeDirection;
import shared.model.message.MessageList;

public class SwaggerModel {
	private ResourceList bank; //our model
	private MessageList chat; //our model
	private MessageList log; //our model
	private SwaggerMap map;
	private List<SwaggerPlayer> players;
	private SwaggerTradeOffer tradeOffer; //our model
	private TurnTracker turnTracker; //our model
	private int version;
	private int winner;
	
	public SwaggerModel(Model model) {
		setBank(model.getBank());
		setChat(model.getChat());
		setLog(model.getLog());
		map = new SwaggerMap(model);
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

	public List<SwaggerPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(List<SwaggerPlayer> players) {
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
}
