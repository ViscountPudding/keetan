package client.join;

import java.util.List;

import shared.definitions.CatanColor;
import shared.transferClasses.Game;
import shared.transferClasses.Player;
import client.base.Controller;
import client.base.IAction;
import client.data.GameInfo;
import client.data.PlayerInfo;
import client.exceptions.ServerException;
import client.misc.IMessageView;
import client.server.ClientServerFacade;


/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController {

	private INewGameView newGameView;
	private ISelectColorView selectColorView;
	private IMessageView messageView;
	private IAction joinAction;
	
	/**
	 * JoinGameController constructor
	 * 
	 * @param view Join game view
	 * @param newGameView New game view
	 * @param selectColorView Select color view
	 * @param messageView Message view (used to display error messages that occur while the user is joining a game)
	 */
	public JoinGameController(IJoinGameView view, INewGameView newGameView, 
								ISelectColorView selectColorView, IMessageView messageView) {

		super(view);

		setNewGameView(newGameView);
		setSelectColorView(selectColorView);
		setMessageView(messageView);
	}
	
	public IJoinGameView getJoinGameView() {
		
		return (IJoinGameView)super.getView();
	}
	
	/**
	 * Returns the action to be executed when the user joins a game
	 * 
	 * @return The action to be executed when the user joins a game
	 */
	public IAction getJoinAction() {
		
		return joinAction;
	}

	/**
	 * Sets the action to be executed when the user joins a game
	 * 
	 * @param value The action to be executed when the user joins a game
	 */
	public void setJoinAction(IAction value) {	
		
		joinAction = value;
	}
	
	public INewGameView getNewGameView() {
		
		return newGameView;
	}

	public void setNewGameView(INewGameView newGameView) {
		
		this.newGameView = newGameView;
	}
	
	public ISelectColorView getSelectColorView() {
		
		return selectColorView;
	}
	public void setSelectColorView(ISelectColorView selectColorView) {
		
		this.selectColorView = selectColorView;
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	public void setMessageView(IMessageView messageView) {
		
		this.messageView = messageView;
	}

	
	
	
	
	
	@Override
	public void start() {
		
		getJoinGameView().showModal();
		List<Game> games;
		try {
			games = ClientServerFacade.getInstance().getGamesList();
		} catch (ServerException e) {
			System.err.println("Could not get list of games in JoinGameCtrl.start(): " + e.getReason());
			return;
		}
		GameInfo[] gameInfoArray = new GameInfo[games.size()];
		
		for (int i = 0; i < games.size(); i++) {
			GameInfo gameInfo = new GameInfo();
			gameInfo.setId(games.get(i).getId());
			gameInfo.setTitle(games.get(i).getTitle());
			List<Player> players = games.get(i).getPlayers();
			for (int j = 0; j < players.size(); j++) {
				PlayerInfo playerInfo = new PlayerInfo();
				//playerInfo.set;
				gameInfo.addPlayer(new PlayerInfo());
			}
			gameInfoArray [i] = new GameInfo();
		}
	}

	@Override
	public void startCreateNewGame() {
		
		getNewGameView().showModal();
	}

	@Override
	public void cancelCreateNewGame() {
		
		getNewGameView().closeModal();
	}

	@Override
	public void createNewGame() {
		
		getNewGameView().closeModal();
	}

	@Override
	public void startJoinGame(GameInfo game) {

		getSelectColorView().showModal();
	}

	@Override
	public void cancelJoinGame() {
	
		getJoinGameView().closeModal();
	}

	@Override
	public void joinGame(CatanColor color) {
		
		// If join succeeded
		getSelectColorView().closeModal();
		getJoinGameView().closeModal();
		joinAction.execute();
	}

}

