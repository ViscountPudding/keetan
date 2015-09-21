package server;
import model.*;

/**
 * The Facade to call the server operations
 * @author willvdb
 */

import java.util.List;

import server.Session;
import catana.resources.PlayerColor;
import catana.resources.Resource;
import catana.resources.EdgeLocation;
import catana.resources.GridLocation;
import catana.resources.VertexLocation;
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

public class ServerFacade {
	
	/**
	 * when a command is made, theModel will be set to whatever is returned by the corresponding command.
	 */
	private ClientModel theModel;

	public Session login(String username, String password)
			throws WrongUserException, ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	public Session register(String username, String password)
			throws WrongUserException, ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Session joinGame(Session user, int gameID, PlayerColor color)
			throws CannotJoinGameException, ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void saveGame(int gameID, String filename)
			throws CannotSaveGameException, ServerException {
		// TODO Auto-generated method stub

	}

	
	public void loadGame(String filename) throws CannotSaveGameException,
			ServerException {
		// TODO Auto-generated method stub

	}

	
	public ClientModel getModel(Session user, int version)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ClientModel resetGame(Session user) throws ServerException{
		// TODO Auto-generated method stub
		return null;
	}

	
	public ClientModel sendChat(Session user, String message)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ClientModel rollDice(Session user, int number)
			throws ServerException, IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ClientModel robPlayer(Session user, GridLocation newRobberLocation,
			Player victim) throws ServerException,
			IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ClientModel buyDevCard(Session user) throws ServerException,
			IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ClientModel yearOfPlenty(Session user, Resource type1,
			Resource type2) throws ServerException, IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ClientModel roadBuilding(Session user, EdgeLocation road1,
			EdgeLocation road2) throws ServerException, IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ClientModel soldier(Session user, GridLocation newRobberLocation,
			Player victim) throws ServerException,
			IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ClientModel monopoly(Session user, Resource type)
			throws ServerException, IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ClientModel buildRoad(Session user, EdgeLocation location,
			boolean free) throws ServerException, IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ClientModel buildSettlement(Session user, VertexLocation location,
			boolean free) throws ServerException, IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ClientModel buildCity(Session user, VertexLocation location)
			throws ServerException, IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ClientModel respondToTrade(Session user, boolean accept)
			throws ServerException, InvalidTradeException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ClientModel maritimeTrade(Session user, Resource inResource,
			Resource outResource, int ratio) throws ServerException,
			IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ClientModel finishTurn(Session user) throws ServerException,
			IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ClientModel offerTrade(Session user, TradeOffer offer)
			throws ServerException, OutOfTurnException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ClientModel discardCards(Session user, ResourceList cards)
			throws ServerException, IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

}