package client.main;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import shared.definitions.CatanColor;
import shared.json.Converter;
import shared.transferClasses.CreateGameRequest;
import shared.transferClasses.CreateGameResponse;
import shared.transferClasses.Game;
import shared.transferClasses.JoinGameRequest;
import shared.transferClasses.UserCredentials;
import client.base.IAction;
import client.catan.CatanPanel;
import client.exceptions.ServerException;
import client.join.JoinGameController;
import client.join.JoinGameView;
import client.join.NewGameView;
import client.join.PlayerWaitingController;
import client.join.PlayerWaitingView;
import client.join.SelectColorView;
import client.login.LoginController;
import client.login.LoginView;
import client.misc.MessageView;
import client.model.ModelFacade;
import client.model.TransferModel;
import client.server.ServerProxy;

/**
 * Main entry point for the Catan program
 */
@SuppressWarnings("serial")
public class Catan extends JFrame
{

	private CatanPanel catanPanel;

	public Catan()
	{
		ServerProxy.initialize("localhost:8081");
		System.out.println("ServerProxy Started");
		
		UserCredentials fox = new UserCredentials("StarFox", "nintendo64");
		try {
			ServerProxy.register(fox);
		}
		catch (ServerException e) {}
		try {
			ServerProxy.login(fox);
			List<Game> games = ServerProxy.getGamesList();
			CreateGameResponse cgr = ServerProxy.createGame(new CreateGameRequest(false, false, false, "Slippy's Game " + games.size()));
			
			ServerProxy.joinGame(new JoinGameRequest(cgr.getId(), CatanColor.GREEN));
			
			TransferModel model = ServerProxy.getModel(-1);
			System.out.println(Converter.toJson(model.getBank()));
			System.out.println(Converter.toJson(model.getMap()));
			ModelFacade.updateModel(model);
			System.out.println("ModelFacade updated with model");
		}
		catch (ServerException e) {
			System.err.println("Server Exception in Catan.run(): " + e.getReason());
		}
		
		
		
		client.base.OverlayView.setWindow(this);

		this.setTitle("Settlers of Catan");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		catanPanel = new CatanPanel();
		this.setContentPane(catanPanel);

		display();
	}

	private void display()
	{
		pack();
		setVisible(true);
	}

	//
	// Main
	//

	public static void main(final String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				new Catan();

				PlayerWaitingView playerWaitingView = new PlayerWaitingView();
				final PlayerWaitingController playerWaitingController = new PlayerWaitingController(
																									playerWaitingView);
				playerWaitingView.setController(playerWaitingController);

				JoinGameView joinView = new JoinGameView();
				NewGameView newGameView = new NewGameView();
				SelectColorView selectColorView = new SelectColorView();
				MessageView joinMessageView = new MessageView();
				final JoinGameController joinController = new JoinGameController(
																				 joinView,
																				 newGameView,
																				 selectColorView,
																				 joinMessageView);
				joinController.setJoinAction(new IAction() {
					@Override
					public void execute()
					{
						playerWaitingController.start();
					}
				});
				joinView.setController(joinController);
				newGameView.setController(joinController);
				selectColorView.setController(joinController);
				joinMessageView.setController(joinController);

				LoginView loginView = new LoginView();
				MessageView loginMessageView = new MessageView();
				LoginController loginController = new LoginController(
																	  loginView,
																	  loginMessageView);
				loginController.setLoginAction(new IAction() {
					@Override
					public void execute()
					{
						joinController.start();
					}
				});
				loginView.setController(loginController);
				loginView.setController(loginController);

				loginController.start();
			}
		});
	}

}
