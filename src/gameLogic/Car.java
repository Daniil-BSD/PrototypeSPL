package gameLogic;

import java.util.*;

import javax.xml.ws.handler.LogicalHandler;

/**
 * This class is responsible for the moving objects on the level, like the
 * locomotive and the passenger car which are derived from this class.
 */
abstract class Car {
	/**
	 * Default constructor
	 */
	public Car() {
	}

	/**
	 * Constructor that sets the starting cell for the car.
	 */
	public Car(Cell cell) {
		this.cell = cell;
	}

	/**
	 * We store this to know which cell our car is currently at.
	 */
	private Cell cell;

	/**
	 * A path is stored which is a sequence of cells to have an idea of the train or
	 * its component's future position.
	 */
	private Path path;

	/**
	 * We need this for the car to always know its next step and path.
	 */
	protected Cell nextCell;

	/**
	 * This attribute is for a car to know which other cars is it attached to. A
	 * train is moved by a locomotive which is a car, so cars should be connected
	 * with each other for this to work.
	 */
	protected Car attachedCar;

	/**
	 * This attribute is here because of the cell logic. This is needed for the
	 * train to know if he has to determine the next step or stay in one place.
	 */
	private boolean permissionToLeave;

	/**
	 * When the train is at the station, this attribute is used to know whether the
	 * colors of the train and the station match so people should leave the station,
	 * or the train can just pass through the station without dropping the
	 * passengers.
	 */
	private Boolean peopleDisembarking;

	/**
	 * This method makes the objects on the level think about their next step and
	 * consequently, move or not depending on the logic that is responsible for this
	 * decision. For example, the train on the station will move if it does not
	 * match the station or stay and disembark the passengers if it has a matching
	 * color. It will be bound to the system clock.
	 */
	public void Step() {

		permissionToLeave = cell.LogicRequest(this);
		if (permissionToLeave) {
			if (nextCell == null && path != null)
				nextCell = this.path.NextCell(cell);
			if (nextCell == null) {
				LevelContainer.Derailed(this);
				return;
			}
			if (!this.nextCell.IsOccupied()) {
				this.cell = this.nextCell;
				this.nextCell = null;
				this.path.UpdatePresence(2, cell);
				if (this.attachedCar != null) {
					attachedCar.Step();
				}

			} else
				LevelContainer.Collided(this);
		}

	}

	/**
	 * This method attaches a car to the car passed on as a parameter.
	 */
	public void attach(Car car) {
		attachedCar = car;
	}

	/**
	 * This method sets the path for the car.
	 */
	public void SetPath(Path path) {
		this.path = path;
	}

	/**
	 * This method sets the next cell for the car.
	 */
	public void SetNextCell(Cell cell) {
		this.nextCell = cell;
	}

	/**
	 * When the locomotive is at the station, this method gets the colors of the
	 * train, checks if any of them match with the station¡¦s colors. If they do, it
	 * then drops the passengers.
	 */
	public boolean CurrentlyAtTheStation_Universal(Colors[] colors) {
		if (attachedCar != null) {
			return attachedCar.CurrentlyAtTheStation_Universal(colors);
		}
		return false;
	}
	
	public boolean CurrentlyAtTheStation_Locomotive(Colors[] colors) {
		return false;
	};

	/**
	 * This method checks if the train has passengers or not. Returns true or false
	 * respectively.
	 */
	public boolean IsEmpty() {
		if (attachedCar != null) {
			return attachedCar.IsEmpty();
		}
		return false;
	}

}