package clientSide.server;
import shared.model.ClientModel;
import shared.model.Player;
import shared.model.PlayerColor;
import shared.model.Resource;
import shared.model.ResourceList;
import shared.model.TradeOffer;
import shared.model.locations.EdgeLocation;
import shared.model.locations.HexLocation;
import shared.model.gamemap.VertexValue;
import clientSide.exceptions.CannotJoinGameException;
import clientSide.exceptions.CannotSaveGameException;
import clientSide.exceptions.IllegalActionException;
import clientSide.exceptions.InvalidTradeException;
import clientSide.exceptions.OutOfTurnException;
import clientSide.exceptions.ServerException;
import clientSide.exceptions.WrongUserException;

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

	
	abstract public ClientModel buildSettlement(Session user, VertexValue vertex, //Maybe create vertex location object?
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