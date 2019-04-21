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
		}
		return Black;
	}
}