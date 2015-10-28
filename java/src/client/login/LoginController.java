package client.login;

import shared.transferClasses.UserCredentials;
import client.base.Controller;
import client.base.IAction;
import client.exceptions.ServerException;
import client.misc.IMessageView;
import client.server.ServerProxy;


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
			
		try {
			UserCredentials credentials = new UserCredentials(username, password);
			ServerProxy.login(credentials);

			getLoginView().closeModal();
			loginAction.execute();
		}
		catch (ServerException e) {
			showModalError(e.getReason());
		}
	}

	private void showModalError(String errorMessage) {
		messageView.setMessage(errorMessage);
		messageView.setTitle("Error");
		messageView.showModal();
	}
	
	/**
	 * this variable is used by validation to show the input was valid
	 */
	private static final String VALID = "VALID";
	
	/**
	 * Checks the username based on certain conditions.
	 * @param username
	 * @return
	 */
	private String validateUsername(String username) {
		final int MIN_UNAME_LENGTH = 3;
        final int MAX_UNAME_LENGTH = 7;

        if (username.length() < MIN_UNAME_LENGTH) {
            return "Username must be at least " + MIN_UNAME_LENGTH + " characters long";
        }
        else if (username.length() > MAX_UNAME_LENGTH) {
            return "Username cannot be longer than " + MAX_UNAME_LENGTH + " characters";
        }
        else
        {
            for (char c : username.toCharArray())
            {
                if (!Character.isLetterOrDigit(c)
                        && c != '_' && c != '-')
                {
                    return "Username must consist of only letters, digits, underscores or hyphens. Example \"2F_Wa-e\"";
                }
            }
        }
        return VALID;
	}
	
	@Override
	public void register() {
		String username = getLoginView().getRegisterUsername();
		String password = getLoginView().getRegisterPassword();
		
		if (validateUsername(username) != VALID) {
			
		}
		
		if (username.contains(" ")) {
			showModalError("Username cannot have a space in it");
		}
		else if (username.length() < 3) {
			showModalError("Username must be at least 3 characters long");
		}
		else if (!password.equals(getLoginView().getRegisterPasswordRepeat())) {
			showModalError("Passwords don't match");
		}
		else if (password.length() < 6) {
			showModalError("Password must be at least 6 characters long");
		}
		else {
			try {
				UserCredentials credentials = new UserCredentials(username, password);
				ServerProxy.register(credentials);
				ServerProxy.login(credentials);

				getLoginView().closeModal();
				loginAction.execute();
			}
			catch (ServerException e) {
				showModalError(e.getReason());
			}
		}
	}

}

