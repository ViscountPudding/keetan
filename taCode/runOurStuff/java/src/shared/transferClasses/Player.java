package shared.transferClasses;

public class Player {
	private String color;
	private String name;
	private int id;
	
	public Player(String color, String name, int id) {
		super();
		this.color = color;
		this.name = name;
		this.id = id;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}	
}
