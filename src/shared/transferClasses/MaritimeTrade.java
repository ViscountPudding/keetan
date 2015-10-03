package shared.transferClasses;

import shared.model.Resource;

/**
 * Used to execute a maritime trade
 * @param playerIndex the player executing the trade
 * @param ratio The ratio of the trade your doing as an integer (ie. put 3 for a 3:1 trade)
 * @param inputResource the resource provided by the player
 * @param outputResource the resource returned to the player
 * @author willvdb
 *
 */

public class MaritimeTrade {

	public MaritimeTrade(int playerIndex, int ratio, Resource input, Resource output) {
		this.playerIndex = playerIndex;
		this.ratio = ratio;
		this.inputResource = input;
		this.outputResource = output;
	}

	private String type = "maritimeTrade";	
	private int playerIndex;
	private int ratio;
	private Resource inputResource;
	private Resource outputResource;

	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public int getRatio() {
		return ratio;
	}
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
	public Resource getInputResource() {
		return inputResource;
	}
	public void setInputResource(Resource inputResource) {
		this.inputResource = inputResource;
	}
	public Resource getOutputResource() {
		return outputResource;
	}
	public void setOutputResource(Resource outputResource) {
		this.outputResource = outputResource;
	}

}
