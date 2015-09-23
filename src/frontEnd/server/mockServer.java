package frontEnd.server;

import bothEnds.model.ClientModel;
import bothEnds.model.Player;
import bothEnds.model.PlayerColor;
import bothEnds.model.Resource;
import bothEnds.model.ResourceList;
import bothEnds.model.TradeOffer;
import bothEnds.model.gamemap.EdgeLocation;
import bothEnds.model.gamemap.HexLocation;
import bothEnds.model.gamemap.VertexObject;
import frontEnd.exceptions.CannotJoinGameException;
import frontEnd.exceptions.CannotSaveGameException;
import frontEnd.exceptions.IllegalActionException;
import frontEnd.exceptions.InvalidTradeException;
import frontEnd.exceptions.OutOfTurnException;
import frontEnd.exceptions.ServerException;
import frontEnd.exceptions.WrongUserException;

public class mockServer implements IServer  {

	@Override
	public Session login(String username, String password)
			throws WrongUserException, ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session register(String username, String password)
			throws WrongUserException, ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session joinGame(Session user, int gameID, PlayerColor color)
			throws CannotJoinGameException, ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveGame(int gameID, String filename)
			throws CannotSaveGameException, ServerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadGame(String filename) throws CannotSaveGameException,
			ServerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClientModel getModel(Session user, int version)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel resetGame(Session user) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel sendChat(Session user, String message)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel rollDice(Session user, int number)
			throws ServerException, IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel robPlayer(Session user, HexLocation newRobberLocation,
			Player victim) throws ServerException, IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel buyDevCard(Session user) throws ServerException,
			IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel yearOfPlenty(Session user, Resource type1, Resource type2)
			throws ServerException, IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel roadBuilding(Session user, EdgeLocation road1,
			EdgeLocation road2) throws ServerException, IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel soldier(Session user, HexLocation newRobberLocation,
			Player victim) throws ServerException, IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel monopoly(Session user, Resource type)
			throws ServerException, IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel buildRoad(Session user, EdgeLocation location,
			boolean free) throws ServerException, IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel buildSettlement(Session user, VertexObject vertex,
			boolean free) throws ServerException, IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel buildCity(Session user, EdgeLocation location)
			throws ServerException, IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel respondToTrade(Session user, boolean accept)
			throws ServerException, InvalidTradeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel maritimeTrade(Session user, Resource inResource,
			Resource outResource, int ratio) throws ServerException,
			IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel finishTurn(Session user) throws ServerException,
			IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel offerTrade(Session user, TradeOffer offer)
			throws ServerException, OutOfTurnException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel discardCards(Session user, ResourceList cards)
			throws ServerException, IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

}
