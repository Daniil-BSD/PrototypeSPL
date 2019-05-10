package gameLogic;

import java.awt.Color;

/**
 * This is an enumeration that stores all the possible colors.
 */
public enum Colors {
	Red, Green, Blue, Black;
	public static Colors parse(String str) {
		str = str.toLowerCase();
		switch (str) {
		case "red":
			return Red;
		case "green":
			return Green;
		case "blue":
			return Blue;
		default:
			return Black;
		}
	}
	
	public Color getAwtColor() {
		switch (this) {
		case Red:
			return Color.RED;
		case Green:
			return Color.GREEN;
		case Blue:
			return Color.BLUE;
		case Black:
			return Color.BLACK;
		}
		return Color.BLACK;
	}
}