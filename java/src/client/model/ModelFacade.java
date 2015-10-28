package client.model;



public class ModelFacade {
	private static Model model = null;
	
	/**
	 * Updates the model if the given model's version is newer.
	 * @pre none
	 * @post If the ModelFacade's model is not yet initialized, or the given model has a higher version,
	 * the model is updated to the given model.
	 * @param model - the model to check for an update
	 */
	public static void updateModel(Model model) {
		if (ModelFacade.model == null) {
			setModel(model);
		}
		else if (model.getVersion() > ModelFacade.model.getVersion()) {
			setModel(model);
		}
	}
	
	/**
	* @pre ModelFacade must be initialized and must have a current valid model
	* @param playerIndex - the index of the player in question
	* @param location - location of hex
	* @return true if the given player owns a settlement or city adjacent to that location, false if otherwise
	* @post see return
	*/
	public static boolean canProduceResource(int playerIndex, HexLocation location) {
		return true;
	}
	
	private static void setModel(Model model) {
		ModelFacade.model = model;
	}

	public int getModelVersion() {
		return model.getVersion();
	}

	public static int whoseTurnIsItAnyway() {
		return model.getTurnTracker().getCurrentPlayer();
	}

	public static boolean canBuildSettlement(int whoseTurnIsItAnyway,
			VertexLocation vertLoc) {
		return false;
	}

	public static boolean canBuildCity(int whoseTurnIsItAnyway,
			VertexLocation vertLoc) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean canBuildRoad(int whoseTurnIsItAnyway,
			EdgeLocation edgeLoc) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void placeRobber(HexLocation hexLoc) {
		// TODO Auto-generated method stub
		
	}

	public static HexLocation findRobber() {
		// TODO Auto-generated method stub
		return null;
	}
}
