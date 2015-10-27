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
		String username = getLoginView().getRegisterUsername();
		String password = getLoginView().getRegisterPassword();
		if (username.contains(" ")) {
			System.out.println("Username cannot have a space in it");
			messageView.setMessage("Username cannot have a space in it");
			return;
		}
		if (username.length() < 3) {
			System.out.println("Username must be at least 3 characters long");
			messageView.setMessage("Username must be at least 3 characters long");
			return;
		}
		if (!password.equals(getLoginView().getRegisterPasswordRepeat())) {
			System.out.println(password + " no match");
			messageView.setMessage("Passwords don't match");
			return;
		}
		if (password.length() < 6) {
			System.out.println("Password too short");
			messageView.setMessage("Password must be at least 6 characters long");
			return;
		}
		System.out.println(password + " match!");
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

