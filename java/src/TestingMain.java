import shared.definitions.CatanColor;
import shared.json.Converter;
import shared.transferClasses.CreateGameRequest;
import shared.transferClasses.CreateGameResponse;
import shared.transferClasses.Game;
import shared.transferClasses.JoinGameRequest;
import shared.transferClasses.UserCredentials;
import client.exceptions.ServerException;
import client.model.TransferModel;
import client.server.ServerProxy;




public class TestingMain {
	public static void main(String[] args) {		
		ServerProxy.initialize("localhost:8081");
		
		UserCredentials fox = new UserCredentials("Star_Fox", "WhereYouGoing");
		UserCredentials peppy = new UserCredentials("Harey_Mentor", "DoABarrelRoll");
		UserCredentials falco = new UserCredentials("Bird_Rival", "GeezeFox");
		UserCredentials slippy = new UserCredentials("Frog_Mechanic", "HELPMEIAMBEINGSHOT");
		
		UserCredentials wolf = new UserCredentials("Wolf", "CantLetYouDoThat");
		UserCredentials leon = new UserCredentials("Leon", "AnnoyingBirdIAmTheGreatLeon");
		UserCredentials pigma = new UserCredentials("Pigma", "DaddyScreamedRealGood");
		UserCredentials andrew = new UserCredentials("Andrew", "UNCLEANDROOOOOOOOSS");
		
		
		try {
			ServerProxy.register(wolf);
			ServerProxy.register(leon);
			ServerProxy.register(pigma);
			ServerProxy.register(andrew);
			ServerProxy.register(fox);
			ServerProxy.register(peppy);
			ServerProxy.register(falco);
			ServerProxy.register(slippy);
		}
		catch (ServerException e) {
			System.out.println("The squad is already registered.");
		}
		
		try {
			ServerProxy.login(fox);
			ServerProxy.login(peppy);
			ServerProxy.login(falco);
			ServerProxy.login(slippy);
			
			Game[] games = ServerProxy.getGamesList();
			CreateGameResponse cgr = ServerProxy.createGame(new CreateGameRequest(false, false, false, "Slippy's Game " + games.length));
			System.out.println(games[0].getTitle());
			
			ServerProxy.joinGame(new JoinGameRequest(cgr.getId(), CatanColor.GREEN));
			
			TransferModel model = ServerProxy.getModel(-1);
			System.out.println(Converter.toJson(model.getBank()));
			System.out.println(Converter.toJson(model.getMap()));
			
			ServerProxy.login(wolf);
			ServerProxy.joinGame(new JoinGameRequest(cgr.getId(), CatanColor.YELLOW));
//			ServerProxy.login(peppy);
//			ServerProxy.joinGame(new JoinGameRequest(cgr.getId(), CatanColor.RED));
//			ServerProxy.login(falco);
//			ServerProxy.joinGame(new JoinGameRequest(cgr.getId(), CatanColor.BLUE));
			
			model = ServerProxy.getModel(-1);
			System.out.println(Converter.toJson(model.getBank()));
			System.out.println(Converter.toJson(model.getMap()));
		}
		catch (ServerException e) {
			System.err.println("Bad stuff: " + e.getReason());
		}
	}
}
