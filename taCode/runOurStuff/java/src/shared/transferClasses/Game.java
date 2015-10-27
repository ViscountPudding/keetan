package shared.transferClasses;

import java.util.ArrayList;

public class Game {
	private String title;
	private int id;
	private ArrayList<Player> players;
	
	public Game(String title, int id) {
		this.title = title;
		this.id = id;
		this.players = new ArrayList<Player>(4);
	}

	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public Player getPlayer(int index) {
		return this.players.get(index);
	}
	
	public void setPlayer(int index, Player player) {
		this.players.set(index, player);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
