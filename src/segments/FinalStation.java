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
		
		
		return "res/Textures/StationFinal.png";
	}
}