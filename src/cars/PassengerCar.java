package cars;

import java.awt.Color;

import gameLogic.*;

/**
 * This class is a car that carries passengers.
 */
@SuppressWarnings("serial")
public class PassengerCar extends Car {

	/**
	 * Constructor that assigns the car's current cell, and the color which will be
	 * compared with the stations' colors to drop passengers.
	 */
	public PassengerCar(Cell cell, Cell rearCell, Colors color) {
		super(cell, rearCell);
		this.color = color;
		this.full = true;
	}

	/**
	 * This attribute stores the color of the car for it to drop the passengers if
	 * it matches the station¡¦s color.
	 */
	private Colors color;

	/**
	 * This attribute tells us if the train is empty or full of passengers.
	 */
	private boolean full;

	/**
	 * This method is a getter for the attribute full.
	 */
	public boolean isFull() {
		return full;
	}
	@Override
	public boolean IsEmpty() {
		return super.IsEmpty() && !full;
	}
	
	@Override
	public Color getColor() {
		return (full)?color.getAwtColor():new Color(0.6f, 0.6f, 0.6f);
	}
	/**
	 * This method compares the colors of the station with the train's colors if the
	 * train is full. If the color matches, the full attribute is set to null and
	 * true is returned. If the color doesn't match the color of the station, false
	 * is returned. if the car is empty, the superclass' similarly method is called
	 * which switches on to the next attached car.
	 */
	@Override
	public boolean CurrentlyAtTheStation_Universal(Colors[] colors) {
		if (full) {
			for (Colors c : colors) {
				if (c.equals(color)) {
					full = false;
					return true;
				}
			}
			return false;
		} else {
			return super.CurrentlyAtTheStation_Universal(colors);
		}
	}
	
	@Override
	public void CustomPrint (int tabs, int index) {
		String string = "\t";
		for(int i = 0; i < tabs; i++) string += "\t";
		System.out.println(string + "\tColor: " + color.toString());
		System.out.println(string + "\tFull: " + full);
	}
}
