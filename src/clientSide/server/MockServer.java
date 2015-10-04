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
	public ClientModel sendChat(SendChat sendChat) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel rollDice(RollNumber rollNumber) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel robPlayer(RobPlayer robPlayer) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel finishTurn(FinishTurn finishTurn) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel buyDevCard(BuyDevCard buyDevCard) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel yearOfPlenty(YearOfPlenty yearOfPlenty)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel roadBuilding(RoadBuilding roadBuilding)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel soldier(Soldier soldier) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel monopoly(Monopoly monopoly) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel monument(Monument monument) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel buildRoad(BuildRoad buildRoad) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel buildSettlement(BuildSettlement buildSettlement)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel buildCity(BuildCity buildCity) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel offerTrade(TradeOffer offer) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel respondToTrade(AcceptTrade acceptTrade)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel maritimeTrade(MaritimeTrade maritimeTrade)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientModel discardCards(DiscardCards discardCards)
			throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
