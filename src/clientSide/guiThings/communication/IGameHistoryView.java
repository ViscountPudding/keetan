package clientSide.guiThings.communication;

import java.util.List;

import clientSide.guiThings.base.*;

/**
 * Game history view interface
 */
public interface IGameHistoryView extends IView
{
	
	/**
	 * Sets the history messages to be displayed in the view.
	 * 
	 * @param entries
	 *            The history messages to display
	 */
	void setEntries(List<LogEntry> entries);
}

