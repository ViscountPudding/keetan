package swaggerModel;

import java.util.List;

import shared.model.Model;
import shared.model.ResourceList;
import shared.model.TradeOffer;
import shared.model.TurnTracker;
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
	
	public SwaggerModel(Model clientModel) {
		
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
}
