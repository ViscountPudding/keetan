package model;

import java.util.ArrayList;
import java.util.List;

import shared.model.message.MessageList;
import shared.transferClasses.Player;

public class Model {
	private DevCardList deck;
	private ResourceList bank;
	private MessageList chat;
	private MessageList log;
	private Map map;
	private List<Player> players;
	private TradeOffer tradeOffer;
	private TurnTracker turnTracker;
	private int version;
	private int winner;
	
	public Model() {
		players = new ArrayList<Player>();
	}
	
	public boolean validate() {
		boolean valid = true;
		if (deck == null || !deck.validate()) return false;
		if (bank == null || !bank.validate()) return false;
		if (chat == null || !chat.validate()) return false;
		if (log == null || !log.validate()) return false;
		if (map == null || !map.validate(players.size())) return false;
		return true;
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

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
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
	
	public DevCardList getDeck() {
		return deck;
	}
	
	public void setDeck(DevCardList deck) {
		this.deck = deck;
	}
}
