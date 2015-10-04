package clientSide.server;

import java.util.ArrayList;

import shared.model.ClientModel;
import shared.model.TradeOffer;
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
import clientSide.exceptions.ServerException;

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
		return communicator.send("/games/list", null, ArrayList.class);
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
		//Swagger doesn't work with us; we want to use our own version of json
		return null;
	}

	@Override
	public void addAI(AddAIRequest addAIRequest) throws ServerException {
		communicator.send("/game/addAI", addAIRequest);
	}

	@Override
	public ArrayList<String> listAITypes() throws ServerException {
		return communicator.send("/game/listAI", null, ArrayList.class);
	}

	@Override
	public void sendChat(SendChat sendChat) throws ServerException {
		communicator.send("/moves/sendChat", sendChat, String.class);
	}

	@Override
	public void rollDice(RollNumber rollNumber) throws ServerException {
		communicator.send("/moves/rollDice", rollNumber, String.class);
	}

	@Override
	public void robPlayer(RobPlayer robPlayer) throws ServerException {
		communicator.send("/moves/robPlayer", robPlayer, String.class);
	}

	@Override
	public void finishTurn(FinishTurn finishTurn) throws ServerException {
		communicator.send("/moves/finishTurn", finishTurn, String.class);
	}

	@Override
	public void buyDevCard(BuyDevCard buyDevCard) throws ServerException {
		communicator.send("/moves/buyDevCard", buyDevCard, String.class);
	}

	@Override
	public void yearOfPlenty(YearOfPlenty yearOfPlenty) throws ServerException {
		communicator.send("/moves/yearOfPlenty", yearOfPlenty, String.class);
	}

	@Override
	public void roadBuilding(RoadBuilding roadBuilding)  throws ServerException {
		communicator.send("/moves/Road_Building", roadBuilding, String.class);
	}

	@Override
	public void soldier(Soldier soldier) throws ServerException {
		communicator.send("/moves/Soldier", soldier, String.class);
	}

	@Override
	public void monopoly(Monopoly monopoly) throws ServerException {
		communicator.send("/moves/Monopoly", monopoly, String.class);
	}

	@Override
	public void monument(Monument monument) throws ServerException {
		communicator.send("/moves/Monument", monument, String.class);
	}

	@Override
	public void buildRoad(BuildRoad buildRoad) throws ServerException {
		communicator.send("/moves/buildRoad", buildRoad, String.class);
	}

	@Override
	public void buildSettlement(BuildSettlement buildSettlement)
			throws ServerException {
		communicator.send("/moves/buildSettlement", buildSettlement, String.class);
	}

	@Override
	public void buildCity(BuildCity buildCity) throws ServerException {
		communicator.send("/moves/buildCity", buildCity, String.class);
	}

	@Override
	public void offerTrade(TradeOffer offer) throws ServerException {
		communicator.send("/moves/offerTrade", offer, String.class);
	}

	@Override
	public void respondToTrade(AcceptTrade acceptTrade)
			throws ServerException {
		communicator.send("/moves/acceptTrade", acceptTrade, String.class);
	}

	@Override
	public void maritimeTrade(MaritimeTrade maritimeTrade)
			throws ServerException {
		communicator.send("/moves/maritimeTrade", maritimeTrade, String.class);
	}

	@Override
	public void discardCards(DiscardCards discardCards)
			throws ServerException {
		communicator.send("/moves/discardCards", discardCards, String.class);
	}

}
