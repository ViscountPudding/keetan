package shared.model;

import java.util.ArrayList;

import shared.model.gamemap.GameMap;
import shared.model.message.MessageList;

/**
 * Contains the game client's version of the game model
 */
public class ClientModel {

	private ResourceList bank;
	private DevCardList undrawnDevCards;
	private MessageList chat;
	private MessageList log;
	private GameMap map;
	private Player[] players;
	private TradeOffer tradeOffer;
	private TurnTracker turnTracker;
	private int version;
	private int winner;

	//SINGLETON!
	private static ClientModel instance = null;
	
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

	public GameMap getMap() {
		return map;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public TradeOffer getTradeOffer() {
		return tradeOffer;
	}

	public void setTradeOffer(TradeOffer tradeOffer) {
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
	
	public DevCardList getUndrawnDevCards() {
		return undrawnDevCards;
	}

	public void setUndrawnDevCards(DevCardList undrawnDevCards) {
		this.undrawnDevCards = undrawnDevCards;
	}

	private ClientModel(boolean randomHexes, boolean randomChits, boolean randomPorts, boolean loadGame, ArrayList<String> names) {
		bank = new ResourceList(19,19,19,19,19);
		undrawnDevCards = new DevCardList(2, 5, 2, 14, 2);
		chat = new MessageList();
		log = new MessageList();
		map = new GameMap(randomHexes, randomChits, randomPorts, loadGame);
		players = new Player[4];
		
		for(int i = 0; i < 4; i++)
		{
			Player newPlayer = new Player(names.get(0), i, i);
			players[i] = newPlayer;
		}
		
		tradeOffer = null;
		version = 1;	
		winner = -1;
	}
	
	/** The singleton generator for the ClientModel
	 * @pre the game must be starting (there must be a server model to copy)
	 * @return the singleton of the ClientModel
	 */
	
	public static ClientModel getInstance(boolean randomHexes, boolean randomChits, boolean randomPorts, boolean loadGame, ArrayList<String> names) {
		if(instance == null) {
			instance = new ClientModel(randomHexes, randomChits, randomPorts, loadGame, names);
		}
		return instance;
	}
	
}
