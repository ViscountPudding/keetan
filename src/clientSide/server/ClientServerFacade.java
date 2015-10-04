package clientSide.server;

import java.util.ArrayList;

import shared.model.ClientModel;
import shared.model.Player;
import shared.model.PlayerColor;
import shared.model.Resource;
import shared.model.ResourceList;
import shared.model.TradeOffer;
import shared.model.locations.EdgeLocation;
import shared.model.locations.HexLocation;
import shared.model.gamemap.VertexValue;
import shared.transferClasses.AcceptTrade;
import shared.transferClasses.AddAIRequest;
import shared.transferClasses.BuildCity;
import shared.transferClasses.BuildRoad;
import shared.transferClasses.BuildSettlement;
import shared.transferClasses.BuyDevCard;
import shared.transferClasses.CreateGameRequest;
import shared.transferClasses.DiscardCards;
import shared.transferClasses.FinishTurn;
import shared.transferClasses.Game;
import shared.transferClasses.JoinGameRequest;
import shared.transferClasses.MaritimeTrade;
import shared.transferClasses.Monopoly;
import shared.transferClasses.Monument;
import shared.transferClasses.RoadBuilding;
import shared.transferClasses.RobPlayer;
import shared.transferClasses.RollNumber;
import shared.transferClasses.SendChat;
import shared.transferClasses.Soldier;
import shared.transferClasses.UserCredentials;
import shared.transferClasses.YearOfPlenty;
import clientSide.exceptions.CannotJoinGameException;
import clientSide.exceptions.CannotSaveGameException;
import clientSide.exceptions.IllegalActionException;
import clientSide.exceptions.InvalidTradeException;
import clientSide.exceptions.OutOfTurnException;
import clientSide.exceptions.ServerException;
import clientSide.exceptions.WrongUserException;

/**
 * This class is a facade which will interperate requests recieved from a server and 
 * run commands based on the request recieved.
 * @author willvdb
 *
 */

public class ClientServerFacade implements IServer{
	private String targetServer;
	private ClientCommunicator communicator;
	
	public ClientServerFacade(String targetServer) {
		this.targetServer = targetServer;
		this.communicator = new ClientCommunicator(targetServer);
	}
	
	//getters and setters
	public void setTargetServer(String targetServer) {
		this.targetServer = targetServer;
		this.communicator.setServerUrl(targetServer);
	}
	public String getTargetServer() {
		return this.targetServer;
	}

	//server functions
	public void login(UserCredentials userCredentials) throws ServerException {
		communicator.send("/user/login", userCredentials);
	}

	@Override
	public void register(UserCredentials userCredentials)
			throws ServerException {
		communicator.send("/user/register", userCredentials);		
	}

	@Override
	public ArrayList<Game> getGamesList() throws ServerException {
		return communicator.send("games/list", null, ArrayList.class);
	}

	@Override
	public Game createGame(CreateGameRequest createGameRequest)
			throws ServerException {
		return communicator.send("/games/create", createGameRequest, Game.class);
	}

	@Override
	public void joinGame(JoinGameRequest joinGameRequest)
			throws ServerException {
		communicator.send("/games/join", joinGameRequest);
	}

	@Override
	public ClientModel getModel(int version) throws ServerException {
		communicator.sendGet("/game/model", version, ClientModel.class);
		return null;
	}

	@Override
	public void addAI(AddAIRequest addAIRequest) throws ServerException {
		communicator.send("/game/addAI", addAIRequest);
	}

	@Override
	public ArrayList<String> listAITypes() throws ServerException {
		return communicator.sendGet("/game/listAI", null, ArrayList.class);
	}

	@Override
	public ClientModel sendChat(SendChat sendChat) throws ServerException {
		return communicator.send("/moves/sendChat", sendChat, ClientModel.class);
	}

	@Override
	public ClientModel rollDice(RollNumber rollNumber) throws ServerException {
		return communicator.send("/moves/rollDice", rollNumber, ClientModel.class);
	}

	@Override
	public ClientModel robPlayer(RobPlayer robPlayer) throws ServerException {
		return communicator.send("/moves/robPlayer", robPlayer, ClientModel.class);
	}

	@Override
	public ClientModel finishTurn(FinishTurn finishTurn) throws ServerException {
		return communicator.send("/moves/finishTurn", finishTurn, ClientModel.class);
	}

	@Override
	public ClientModel buyDevCard(BuyDevCard buyDevCard) throws ServerException {
		return communicator.send("/moves/buyDevCard", buyDevCard, ClientModel.class);
	}

	@Override
	public ClientModel yearOfPlenty(YearOfPlenty yearOfPlenty) throws ServerException {
		return communicator.send("/moves/yearOfPlenty", yearOfPlenty, ClientModel.class);
	}

	@Override
	public ClientModel roadBuilding(RoadBuilding roadBuilding)  throws ServerException {
		return communicator.send("/moves/Road_Building", roadBuilding, ClientModel.class);
	}

	@Override
	public ClientModel soldier(Soldier soldier) throws ServerException {
		return communicator.send("/moves/Soldier", soldier, ClientModel.class);
	}

	@Override
	public ClientModel monopoly(Monopoly monopoly) throws ServerException {
		return communicator.send("/moves/Monopoly", monopoly, ClientModel.class);
	}

	@Override
	public ClientModel monument(Monument monument) throws ServerException {
		return communicator.send("/moves/Monument", monument, ClientModel.class);
	}

	@Override
	public ClientModel buildRoad(BuildRoad buildRoad) throws ServerException {
		return communicator.send("/moves/buildRoad", buildRoad, ClientModel.class);
	}

	@Override
	public ClientModel buildSettlement(BuildSettlement buildSettlement)
			throws ServerException {
		return communicator.send("/moves/buildSettlement", buildSettlement, ClientModel.class);
	}

	@Override
	public ClientModel buildCity(BuildCity buildCity) throws ServerException {
		return communicator.send("/moves/buildCity", buildCity, ClientModel.class);
	}

	@Override
	public ClientModel offerTrade(TradeOffer offer) throws ServerException {
		return communicator.send("/moves/offerTrade", offer, ClientModel.class);
	}

	@Override
	public ClientModel respondToTrade(AcceptTrade acceptTrade)
			throws ServerException {
		return communicator.send("/moves/acceptTrade", acceptTrade, ClientModel.class);
	}

	@Override
	public ClientModel maritimeTrade(MaritimeTrade maritimeTrade)
			throws ServerException {
		return communicator.send("/moves/maritimeTrade", maritimeTrade, ClientModel.class);
	}

	@Override
	public ClientModel discardCards(DiscardCards discardCards)
			throws ServerException {
		return communicator.send("/moves/discardCards", discardCards, ClientModel.class);
	}

}
