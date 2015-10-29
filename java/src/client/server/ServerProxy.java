package client.server;

import java.util.ArrayList;
import java.util.List;

import shared.transferClasses.AcceptTrade;
import shared.transferClasses.AddAIRequest;
import shared.transferClasses.BuildCity;
import shared.transferClasses.BuildRoad;
import shared.transferClasses.BuildSettlement;
import shared.transferClasses.BuyDevCard;
import shared.transferClasses.CreateGameRequest;
import shared.transferClasses.CreateGameResponse;
import shared.transferClasses.DiscardCards;
import shared.transferClasses.FinishTurn;
import shared.transferClasses.Game;
import shared.transferClasses.JoinGameRequest;
import shared.transferClasses.MaritimeTrade;
import shared.transferClasses.Monopoly;
import shared.transferClasses.Monument;
import shared.transferClasses.OfferTrade;
import shared.transferClasses.RoadBuilding;
import shared.transferClasses.RobPlayer;
import shared.transferClasses.RollNumber;
import shared.transferClasses.SendChat;
import shared.transferClasses.Soldier;
import shared.transferClasses.UserCredentials;
import shared.transferClasses.YearOfPlenty;
import client.exceptions.ServerException;
import client.model.TradeOffer;
import client.model.TransferModel;


/**
 * This class is a facade which will interperate requests recieved from a server and 
 * run commands based on the request recieved.
 * @author willvdb
 *
 */

public class ServerProxy {
	private static ClientCommunicator communicator = null;
	
	/**
	 * Initializes this ServerProxy to target the given server.
	 * @pre targetServer must specify a valid server
	 * @param targetServer - a string that follows the form "[IP-address]:[port-number]
	 * @post ServerProxy will now target the given server
	 */
	public static void initialize(String targetServer) {
		communicator = new ClientCommunicator(targetServer);
	}

	//server functions
	public static void login(UserCredentials userCredentials) throws ServerException {
		communicator.send("/user/login", userCredentials);
	}

	public static void register(UserCredentials userCredentials)
			throws ServerException {
		communicator.send("/user/register", userCredentials);		
	}

	public static Game[] getGamesList() throws ServerException {
		return communicator.send("/games/list", null, Game[].class);
	}

	public static CreateGameResponse createGame(CreateGameRequest createGameRequest)
			throws ServerException {
		return communicator.send("/games/create", createGameRequest, CreateGameResponse.class);
	}

	public static void joinGame(JoinGameRequest joinGameRequest)
			throws ServerException {
		communicator.send("/games/join", joinGameRequest);
	}

	public static TransferModel getModel(int version) throws ServerException {
		return communicator.send("/game/model", version, TransferModel.class);
	}

	public static void addAI(AddAIRequest addAIRequest) throws ServerException {
		communicator.send("/game/addAI", addAIRequest);
	}

	public static List<String> listAITypes() throws ServerException {
		return communicator.send("/game/listAI", null, ArrayList.class);
	}

	public static void sendChat(SendChat sendChat) throws ServerException {
		communicator.send("/moves/sendChat", sendChat, String.class);
	}

	public static void rollDice(RollNumber rollNumber) throws ServerException {
		communicator.send("/moves/rollDice", rollNumber, String.class);
	}

	public static void robPlayer(RobPlayer robPlayer) throws ServerException {
		communicator.send("/moves/robPlayer", robPlayer, String.class);
	}

	public static void finishTurn(FinishTurn finishTurn) throws ServerException {
		communicator.send("/moves/finishTurn", finishTurn, String.class);
	}

	public static void buyDevCard(BuyDevCard buyDevCard) throws ServerException {
		communicator.send("/moves/buyDevCard", buyDevCard, String.class);
	}

	public static void yearOfPlenty(YearOfPlenty yearOfPlenty) throws ServerException {
		communicator.send("/moves/yearOfPlenty", yearOfPlenty, String.class);
	}

	public static void roadBuilding(RoadBuilding roadBuilding)  throws ServerException {
		communicator.send("/moves/Road_Building", roadBuilding, String.class);
	}

	public static void soldier(Soldier soldier) throws ServerException {
		communicator.send("/moves/Soldier", soldier, String.class);
	}

	public static void monopoly(Monopoly monopoly) throws ServerException {
		communicator.send("/moves/Monopoly", monopoly, String.class);
	}

	public static void monument(Monument monument) throws ServerException {
		communicator.send("/moves/Monument", monument, String.class);
	}

	public static void buildRoad(BuildRoad buildRoad) throws ServerException {
		communicator.send("/moves/buildRoad", buildRoad, String.class);
	}

	public static void buildSettlement(BuildSettlement buildSettlement)
			throws ServerException {
		communicator.send("/moves/buildSettlement", buildSettlement, String.class);
	}

	public static void buildCity(BuildCity buildCity) throws ServerException {
		communicator.send("/moves/buildCity", buildCity, String.class);
	}

	public static void offerTrade(OfferTrade offer) throws ServerException {
		communicator.send("/moves/offerTrade", offer, String.class);
	}

	public static void respondToTrade(AcceptTrade acceptTrade)
			throws ServerException {
		communicator.send("/moves/acceptTrade", acceptTrade, String.class);
	}

	public static void maritimeTrade(MaritimeTrade maritimeTrade)
			throws ServerException {
		communicator.send("/moves/maritimeTrade", maritimeTrade, String.class);
	}

	public static void discardCards(DiscardCards discardCards)
			throws ServerException {
		communicator.send("/moves/discardCards", discardCards, String.class);
	}
}
