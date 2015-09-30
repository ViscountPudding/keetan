package shared.transferClasses;

import shared.model.Resource;

public class MaritimeTrade {

	public MaritimeTrade(int playerIndex, int ratio, Resource input, Resource output) {
		this.playerIndex = playerIndex;
		this.ratio = ratio;
		this.inputResource = input;
		this.outputResource = output;
	}
	
	protected int playerIndex;
	/**
	 * ratio
	 * The ratio of the trade your doing as an integer (ie. put 3 for a 3:1 trade)
	 */
	protected int ratio;
	protected Resource inputResource;
	protected Resource outputResource;
	
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
