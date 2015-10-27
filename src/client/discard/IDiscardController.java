package client.discard;

import shared.definitions.ResourceType;
import client.base.IController;

/**
 * Discard controller interface
 */
public interface IDiscardController extends IController
{
	/**
	 * This method is called when the user increases the amount of the specified
	 * resource.
	 * 
	 * @param resource
	 *            The resource that was increased
	 */
	void increaseAmount(ResourceType resource);
	
	/**
	 * This method is called when the user decreases the amount of the specified
	 * resource.
	 * 
	 * @param resource
	 *            The resource that was decreased
	 */
	void decreaseAmount(ResourceType resource);
	
	/**
	 * This method is called when the user clicks the discard button.
	 */
	void discard();
	
}
