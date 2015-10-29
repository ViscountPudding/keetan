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
	 * Checks the username based on certain conditions.
	 * <ul>
	 * <li>username must be at least 3 characters long</li>
	 * <li>username cannot exceed 7 characters</li>
	 * <li>username can only contain letters, digits, underscores, and hyphens (Underscore: '_', Hyphen: '-')</li>
	 * </ul>
	 * @pre username must not be null
	 * @param username - the username to check
	 * @post see return
	 * @return true if the username is valid, false if otherwise
	 */
	private boolean validateUsername(String username) {
        if (username.length() < 3) {
            return false;
        }
        else if (username.length() > 7) {
            return false;
        }
        else {
            for (char c : username.toCharArray()) {
                if (!Character.isLetterOrDigit(c) && c != '_' && c != '-') {
                    return false;
                }
            }
        }
        return true;
	}
	
	/**
	 * Checks the password based on certain conditions.
	 * <ul>
	 * <li>passwords musts match</li>
	 * <li>password cannot exceed 5 characters</li>
	 * <li>password can only contain letters, digits, underscores, and hyphens (Underscore: '_', Hyphen: '-')</li>
	 * </ul>
	 * @param password1 - the first password
	 * @param password2 - the password to double check
	 * @return boolean true - if passwords match and are valid, false - if otherwise
	 */
	private boolean validatePasswords(String password1, String password2) {
		if (!password1.equals(password2)) {
			return false;
		}
		else {
			if (password1.length() < 5) {
                return false;
            }
			else {
				for (char c : password1.toCharArray()) {
                    if (!Character.isLetterOrDigit(c) && c != '_' && c != '-') {
                        return false;
                    }
                }
            }
		}
		return true;
	}

	
	@Override
	public void register() {
		String username = getLoginView().getRegisterUsername();
		String password1 = getLoginView().getRegisterPassword();
		String password2 = getLoginView().getRegisterPassword();
		
		if (!validateUsername(username)) {
			showModalError("Username must be at least 3 characters long." +
					"<br>Username cannot be longer than  7 characters." +
					"<br>Username must consist of only letters, digits, underscores or hyphens. Example \"2F_Wa-e\"");
		}
		else if (!validatePasswords(password1, password2)) {
			showModalError("Password must be at least 5 characters long." +
					"<br>Password must consist of only letters, digits, underscores or hyphens. Example \"Five-2Fishy_Wal-ee\"" +
					"<br>Password must match Password (Again)");
		}
		else {
			try {
				UserCredentials credentials = new UserCredentials(username, password1);
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

