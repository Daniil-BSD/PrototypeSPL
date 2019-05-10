package gameLogic;

import java.io.Serializable;

/**
 * This class is responsible for the basic spacial units of the level, cells.
 */
public class Cell implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1750788500748700957L;

	/**
	 * This attribute stores true if the cell is occupied by a car, false otherwise.
	 */
	private boolean occupied;
	
	private boolean visible;

	/**
	 * This attribute stores the logic which decides the next move for the car.
	 */
	private CellLogic logic;
	
	/**
	 * Coordinates of the cell
	 */
	public final vec2 localPosition;
	
	public vec2 globalPosition;
	
	public vec2 getLocalPosition() {
		return new vec2(localPosition);
	}
	
	public vec2 getGlobalPosition() {
		return new vec2(globalPosition);
	}
	
	public void setGlobalPosition(vec2 position) {
		this.globalPosition = position;
	}

	/**
	 * Default constructor
	 */
	public Cell(vec2 position) {
		this(position.x, position.y, true);
	}
	
	public Cell(float X, float Y) {
		this(X, Y, true);
	}
	
	public Cell(float X, float Y, boolean visible) {
		localPosition = new vec2(X,Y);
		globalPosition = new vec2(X,Y);
		occupied = false;
		this.visible = visible;
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
	
	public boolean IsVisisble() {
		return visible;
	}
	
	@Override
	public String toString() {
		
		return "Cell [" + System.identityHashCode(this) + "]: occupied = " + occupied  + "; Coordinates: ("+ localPosition.x + ","+ localPosition.y +")"+ " ; logic: " + 
		((logic != null)? logic.toString(): "(none)");
	}

}