package client.login;

import shared.transferClasses.UserCredentials;
import client.base.Controller;
import client.base.IAction;
import client.exceptions.ServerException;
import client.misc.IMessageView;
import client.server.ClientServerFacade;


/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController {

	private IMessageView messageView;
	private IAction loginAction;
	
	/**
	 * LoginController constructor
	 * 
	 * @param view Login view
	 * @param messageView Message view (used to display error messages that occur during the login process)
	 */
	public LoginController(ILoginView view, IMessageView messageView) {

		super(view);
		
		this.messageView = messageView;
	}
	
	public ILoginView getLoginView() {
		
		return (ILoginView)super.getView();
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	
	/**
	 * Sets the action to be executed when the user logs in
	 * 
	 * @param value The action to be executed when the user logs in
	 */
	public void setLoginAction(IAction value) {
		
		loginAction = value;
	}
	
	/**
	 * Returns the action to be executed when the user logs in
	 * 
	 * @return The action to be executed when the user logs in
	 */
	public IAction getLoginAction() {
		
		return loginAction;
	}

	@Override
	public void start() {
		
		getLoginView().showModal();
	}

	@Override
	public void signIn() {
		String username = getLoginView().getLoginUsername();
		String password = getLoginView().getLoginPassword();
		UserCredentials credentials = new UserCredentials(username, password);
		
		try {
			ClientServerFacade.getInstance().login(credentials);

			getLoginView().closeModal();
			loginAction.execute();
		} catch (ServerException e) {
			messageView.setMessage(e.getReason());
		}
	}

	@Override
	public void register() {
		String username = getLoginView().getLoginUsername();
		String password = getLoginView().getLoginPassword();
		UserCredentials credentials = new UserCredentials(username, password);
		
		try {
			ClientServerFacade.getInstance().register(credentials);
			ClientServerFacade.getInstance().login(credentials);

			getLoginView().closeModal();
			loginAction.execute();
		} catch (ServerException e) {
			messageView.setMessage(e.getReason());
		}
	}

}

