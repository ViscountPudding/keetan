package server;
import model.*;

import java.util.List;

import server.Session;
import model.PlayerColor;
import model.Resource;
import model.gamemap.EdgeLocation;
import model.gamemap.HexLocation;
import model.gamemap.VertexObject;
import exceptions.CannotSaveGameException;
import exceptions.IllegalActionException;
import exceptions.CannotJoinGameException;
import exceptions.OutOfTurnException;
import exceptions.ServerException;
import exceptions.InvalidTradeException;
import exceptions.WrongUserException;
import model.ClientModel;
import model.Player;
import model.ResourceList;
import model.TradeOffer;

public interface IServer {
	
	/**
	 * when a command is made, theModel will be set to whatever is returned by the corresponding command.
	 */
	//private ClientModel theModel;

	abstract public Session login(String username, String password)
			throws WrongUserException, ServerException;

	abstract public Session register(String username, String password)
			throws WrongUserException, ServerException;

	
	abstract public Session joinGame(Session user, int gameID, PlayerColor color)
			throws CannotJoinGameException, ServerException;

	
	abstract public void saveGame(int gameID, String filename)
			throws CannotSaveGameException, ServerException;

	
	abstract public void loadGame(String filename) throws CannotSaveGameException,
			ServerException;

	
	abstract public ClientModel getModel(Session user, int version)
			throws ServerException;

	
	abstract public ClientModel resetGame(Session user) throws ServerException;

	
	abstract public ClientModel sendChat(Session user, String message)
			throws ServerException;

	
	abstract public ClientModel rollDice(Session user, int number)
			throws ServerException, IllegalActionException;

	
	abstract public ClientModel robPlayer(Session user, HexLocation newRobberLocation,
			Player victim) throws ServerException,
			IllegalActionException;

	
	abstract public ClientModel buyDevCard(Session user) throws ServerException,
			IllegalActionException;

	
	abstract public ClientModel yearOfPlenty(Session user, Resource type1,
			Resource type2) throws ServerException, IllegalActionException;

	
	abstract public ClientModel roadBuilding(Session user, EdgeLocation road1,
			EdgeLocation road2) throws ServerException, IllegalActionException;

	
	abstract public ClientModel soldier(Session user, HexLocation newRobberLocation,
			Player victim) throws ServerException,
			IllegalActionException;

	
	abstract public ClientModel monopoly(Session user, Resource type)
			throws ServerException, IllegalActionException;

	
	abstract public ClientModel buildRoad(Session user, EdgeLocation location,
			boolean free) throws ServerException, IllegalActionException;

	
	abstract public ClientModel buildSettlement(Session user, VertexObject vertex, //Maybe create vertex location object?
			boolean free) throws ServerException, IllegalActionException;

	
	abstract public ClientModel buildCity(Session user, EdgeLocation location) //Maybe create vertex location object?
			throws ServerException, IllegalActionException;

	
	abstract public ClientModel respondToTrade(Session user, boolean accept)
			throws ServerException, InvalidTradeException;

	
	abstract public ClientModel maritimeTrade(Session user, Resource inResource,
			Resource outResource, int ratio) throws ServerException,
			IllegalActionException;

	
	abstract public ClientModel finishTurn(Session user) throws ServerException,
			IllegalActionException;

	
	abstract public ClientModel offerTrade(Session user, TradeOffer offer)
			throws ServerException, OutOfTurnException;

	
	abstract public ClientModel discardCards(Session user, ResourceList cards)
			throws ServerException, IllegalActionException;
}