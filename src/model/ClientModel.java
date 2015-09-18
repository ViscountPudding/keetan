package model;

import model.message.MessageList;
import model.gamemap.GameMap;

/**
 * Contains the game client's version of the game model
 */
public class ClientModel {

	private ResourceList bank;
	private MessageList chat;
	private MessageList log;
	private GameMap map;
	private Player[] players;
	private TradeOffer tradeOffer;
	private TurnTracker turnTracker;
	private int version;
	private int winner;
	
	public ClientModel() {
		
	}
	
	
	
}
