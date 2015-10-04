package clientSide.server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import shared.Converter;
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

public class MockServer implements IServer  {
	
	@Override
	public ClientModel getModel(int version) throws ServerException {
		String content = null;
		try {
			content = new String(Files.readAllBytes(Paths.get("Utilities/model.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Converter.fromJson(content, ClientModel.class);
	}
	
	@Override
	public void login(UserCredentials userCredentials) throws ServerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void register(UserCredentials userCredentials)
			throws ServerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Game> getGamesList() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game createGame(CreateGameRequest createGameRequest)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void joinGame(JoinGameRequest joinGameRequest)
			throws ServerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAI(AddAIRequest addAIRequest) throws ServerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<String> listAITypes() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendChat(SendChat sendChat) throws ServerException {
	}

	@Override
	public void rollDice(RollNumber rollNumber) throws ServerException {
	}

	@Override
	public void robPlayer(RobPlayer robPlayer) throws ServerException {
	}

	@Override
	public void finishTurn(FinishTurn finishTurn) throws ServerException {
	}

	@Override
	public void buyDevCard(BuyDevCard buyDevCard) throws ServerException {
	}

	@Override
	public void yearOfPlenty(YearOfPlenty yearOfPlenty)
			throws ServerException {
	}

	@Override
	public void roadBuilding(RoadBuilding roadBuilding)
			throws ServerException {
	}

	@Override
	public void soldier(Soldier soldier) throws ServerException {
	}

	@Override
	public void monopoly(Monopoly monopoly) throws ServerException {
	}

	@Override
	public void monument(Monument monument) throws ServerException {
	}

	@Override
	public void buildRoad(BuildRoad buildRoad) throws ServerException {
	}

	@Override
	public void buildSettlement(BuildSettlement buildSettlement)
			throws ServerException {
	}

	@Override
	public void buildCity(BuildCity buildCity) throws ServerException {
	}

	@Override
	public void offerTrade(TradeOffer offer) throws ServerException {
	}

	@Override
	public void respondToTrade(AcceptTrade acceptTrade)
			throws ServerException {
	}

	@Override
	public void maritimeTrade(MaritimeTrade maritimeTrade)
			throws ServerException {
	}

	@Override
	public void discardCards(DiscardCards discardCards)
			throws ServerException {
	}
	

}
