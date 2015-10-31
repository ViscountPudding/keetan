package client.join;

import java.util.Observable;

import shared.transferClasses.AddAIRequest;
import client.base.Controller;
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

		return (IPlayerWaitingView)super.getView();
	}
	
	
	/**
	 * Refreshes the game list in the join game veiw
	 */
	private void refreshPlayerInfo() {
		getView().setPlayers(ModelFacade.getPlayers());
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

	@Override
	public void update(Observable o, Object arg) {
		if (ModelFacade.getPlayers().length == 4) {
			refreshPlayerInfo();
			getView().closeModal();
		}
		else {
			refreshPlayerInfo();
		}
	}
}

