package shared.transferClasses;

public class SendChat {
	
	public SendChat(int index, String contentIn){
		playerIndex = index;
		content = contentIn;
	}
	
	protected int playerIndex;
	protected String content;
	
	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
