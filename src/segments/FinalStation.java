package segments;

import gameLogic.Colors;

/**
 * This class is responsible for the final station which, upon getting a train,
 * checks if the train is empty. We lose the game if there are still passengers
 * inside the train at that point.
 */
@SuppressWarnings("serial")
public class FinalStation extends Station {

	/**
	 * Default constructor
	 */

	public FinalStation() {
		super();
	}

	/**
	 * Constructor that sets the id and the colors for the final station.
	 */
	public FinalStation(String id, Colors[] colors) {
		super(id, colors);
	}
	
	public void printFull() {
		System.out.print("Final");
		super.printFull();
	}

	/**
	 * This method is used to identify the station as the final stop in the level.
	 * It always returns true.
	 */
	@Override
	public boolean IsFinal() {
		return true;
	}
	
	public String getTexturePath() {
		Colors[] colors = getColors();
		
		if(colors.length<=1 && colors[0].equals(Colors.valueOf("Red"))) {
			
			return "res/Textures/StationFinalRed.png";
		}
		if(colors.length<=1 && colors[0].equals(Colors.valueOf("Green"))) {
			
			return "res/Textures/StationFinalGreen.png";
		}
		if(colors.length<=1 && colors[0].equals(Colors.valueOf("Blue"))) {
			
			return "res/Textures/StationFinalBlue.png";
		}
		if(colors.length<=2 && (colors[0].equals(Colors.valueOf("Red"))&&colors[1].equals(Colors.valueOf("Green"))||colors[0].equals(Colors.valueOf("Green"))&&colors[1].equals(Colors.valueOf("Red")))) {
			
			return "res/Textures/StationFinalRedGreen.png";
		}
		if(colors.length<=2 && (colors[0].equals(Colors.valueOf("Red"))&&colors[1].equals(Colors.valueOf("Blue"))||colors[0].equals(Colors.valueOf("Blue"))&&colors[1].equals(Colors.valueOf("Red")))) {
			
			return "res/Textures/StationFinalRedBlue.png";
		}
		if(colors.length<=2 && (colors[0].equals(Colors.valueOf("Green"))&&colors[1].equals(Colors.valueOf("Blue"))||colors[0].equals(Colors.valueOf("Blue"))&&colors[1].equals(Colors.valueOf("Green")))) {
			
			return "res/Textures/StationFinalGreenBlue.png";
		}
		
		return "res/Textures/StationFinalRGB.png";
	}
}