package cars;

import gameLogic.*;

/**
 * This class implements the locomotive's behavior. Locomotive is the engine of
 * a train, the number of locomotives corresponds to the number of the trains on
 * the level.
 */
@SuppressWarnings("serial")
public class Locomotive extends Car {

	/**
	 * Constructor that calls the superclass Car's constructor which then sets the
	 * given cell as the one where the locomotive is currently at.
	 */
	int gloIndex = 0;
	int curIndex;
	public Locomotive(Cell cell, Cell rearCell) {
		super(cell, rearCell);
		curIndex = gloIndex;
		gloIndex++;
	}
	
	/**
	 * When the locomotive is at the station, this method gets the 
	 * colors of the attached cars. 
	 */
	@Override
	public boolean CurrentlyAtTheStation_Locomotive(Colors[] colors) {
		if (attachedCar != null) {
			return attachedCar.CurrentlyAtTheStation_Universal(colors);
		}
		return false;
	};

}