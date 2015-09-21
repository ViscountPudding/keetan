package model;

import java.awt.Color;

public enum PlayerColor {
	
	RED,ORANGE,YELLOW,BLUE,GREEN,PURPLE,WHITE;
	
	private Color color;
	
	// TODO set the hex values for each enumerated color

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
