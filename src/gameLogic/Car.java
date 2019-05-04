package gameLogic;

import java.awt.print.Printable;
import java.io.Serializable;
import java.util.*;

import javax.xml.ws.handler.LogicalHandler;

/**
 * This class is responsible for the moving objects on the level, like the
 * locomotive and the passenger car which are derived from this class.
 */
public abstract class Car implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3574417194240339480L;

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
	 * This method makes the objects on the level think about their next step and
	 * consequently, move or not depending on the logic that is responsible for this
	 * decision. For example, the train on the station will move if it does not
	 * match the station or stay and disembark the passengers if it has a matching
	 * color. It will be bound to the system clock.
	 */
	public void Step() {
		if (cell.LogicRequest(this)) {
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

	public Car getAttachedCar() {
		return attachedCar;
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
		return true;
	}
	
	public void printFull(int tabs, int index) {
		String string = "";
		for(int i = 0; i < tabs; i++) string += "\t";
		System.out.println("Train index: " + index + " Car id: [" + System.identityHashCode(this) + "]");
		System.out.println(string + "\tCurrent Cell: [" + System.identityHashCode(cell) + "]");
		System.out.println(string + "\tCurrent Path: [" + System.identityHashCode(path) + "]");
		CustomPrint(tabs, index);
		System.out.print(string + "\tAttached Car: ");
		if(attachedCar != null) attachedCar.printFull(tabs + 1, index);
		else System.out.println("(none)");
	}
	
	public void CustomPrint(int tabs, int index) {}
	
}