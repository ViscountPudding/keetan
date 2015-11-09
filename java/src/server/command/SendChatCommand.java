package server.command;

import shared.transferClasses.SendChat;

/**
 * SendChatCommand is a command that adds the given chat to the model
 */
public class SendChatCommand implements Command {
	/**
	 * Creates a command that when executed will add the given
	 * chat to the associated model
	 * @param gameID - the ID of the game to add the chat to
	 * @param chat - the chat to add to the model
	 */
	public SendChatCommand(int gameID, SendChat chat) {
		
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
