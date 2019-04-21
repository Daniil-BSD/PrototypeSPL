package gameLogic;

import java.io.Serializable;
import java.util.*;

/**
 * This class is responsible for the basic spacial units of the level, cells.
 */
class Cell implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1750788500748700957L;

	/**
	 * This attribute stores true if the cell is occupied by a car, false otherwise.
	 */
	private Boolean occupied;

	/**
	 * This attribute stores the logic which decides the next move for the car.
	 */
	private CellLogic logic;

	/**
	 * Default constructor
	 */
	public Cell() {
		occupied = false;
	}

	public boolean LogicRequest(Car car) {
		if (logic != null) {
			Boolean messageBool = logic.LogicRequest(car);

			return messageBool;
		}
		return true;
	}

	/**
	 * This method returns the logic of the cell.
	 */
	public CellLogic GetLogic() {
		return logic;
	}

	/**
	 * This method sets the logic for the cell.
	 */
	public void SetLogic(CellLogic logic) {
		this.logic = logic;
	}

	/**
	 * This method returns the value depending on the occupied attribute.
	 */
	public boolean IsOccupied() {

		return occupied;
	}

	/**
	 * This method sets the occupied value of the cell.
	 */
	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}
	
	@Override
	public String toString() {
		return "Cell [" + System.identityHashCode(this) + "]: occupied = " + occupied +" ; logic: " + 
		((logic != null)? logic.toString(): "(none)");
	}

}