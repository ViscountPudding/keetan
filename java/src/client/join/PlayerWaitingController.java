package client.join;

import shared.transferClasses.AddAIRequest;
import client.base.Controller;
import client.data.PlayerInfo;
import client.exceptions.ServerException;
import client.model.ModelFacade;
import client.server.ServerProxy;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {

	public PlayerWaitingController(IPlayerWaitingView view) {
		super(view);
		
		ModelFacade.addObserver(this);
	}

	@Override
	public IPlayerWaitingView getView() {

		numberOfJoinedPlayers = 0;
		return (IPlayerWaitingView)super.getView();
	}
	
	
	/**
	 * Refreshes the game list in the join game veiw
	 */
	private void refreshPlayerInfo() {
		PlayerInfo[] info - ModelFacade.getJoinedPlayersInfo();
		numberOfJoinedPlayers =
		getView().setPlayers(ModelFacade.getJoinedPlayersInfo());
		getView().showModal();
	}
	
	@Override
	public void start() {
		try {
			String[] aiTypes = ServerProxy.listAITypes();
			getView().setAIChoices(aiTypes);
			refreshPlayerInfo();
			getView().showModal();
		}
		catch (ServerException e) {
			// To the Ta's of the past. Why is there no message view?  ('n')
		}
	}

	@Override
	public void addAI() {
		try {
			ServerProxy.addAI(new AddAIRequest(getView().getSelectedAI()));
		}
		catch (ServerException e) {
			// To the Ta's of the past. Why is there no message view?  ('n')
			getView().closeModal();
		}
	}

	/**
	 * The number of players shown to the view
	 */
	private int numberOfJoinedPlayers;
	
	@Override
	public void update() {
		if (ModelFacade.getJoinedPlayersInfo().length == 4) {
			System.out.println("No change has four");
			getView().closeModal();
		}
		else {
			System.out.println("Change!");
			refreshPlayerInfo();
		}
	}
}

