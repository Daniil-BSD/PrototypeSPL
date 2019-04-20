package gameLogic;

import java.security.InvalidParameterException;
import java.util.*;

/**
 * The path is a sequence of cells along which the train moves which is
 * implemented by this class.
 */
class Path {

	protected Cell[] cells;
	
	/**
	 * Constructor that sets the cells and assigns the PathStart and PathEnd logic
	 * to the first and the last cells of the path respectively if the path is
	 * appropriately long. Throws an exception if the path is too short.
	 */
	public Path(Cell[] cells) {
		this.cells = cells;
		if (this.cells.length > 1) {
			this.cells[0].SetLogic(new PathStart(this));
			this.cells[this.cells.length - 1].SetLogic(new PathEnd());
		} else {
			throw new InvalidParameterException();
		}
	}

	/**
	 * Default constructor.
	 */
	protected Path() {
	}

	/**
	 * 
	 * This method gets the cell, and returns the cell which is next to it along the
	 * same path.
	 */

	public Cell NextCell(Cell cell) {
		int index = 0;
		for (Cell localCell : cells) {
			index++;
			if (localCell == cell)
				break;
		}

	if (index < cells.length)
			return cells[index];
		return null;
	}

	/**
	 * This method returns the first cell of the path.
	 */
	public Cell GetStart() {
		return cells[0];
	}

	/**
	 * This method returns the last cell of the path.
	 */
	public Cell GetEnd() {
		return cells[this.cells.length - 1];
	}

	/**
	 * This method returns the PathEnd logic of the last cell of the path.
	 */
	public PathEnd GetEndLogic() {
		return (PathEnd) cells[this.cells.length - 1].GetLogic();
	}

	/**
	 * This method gets the cell, which is occupied by a car and makes the amount of
	 * cells equal to length behind the car occupied as well.
	 */
	public void UpdatePresence(int length, Cell current) {

		// TODO implement here
		// finish this!!

		int index = 0;
		boolean found = false;
		for (Cell localCell : cells) {
			if (localCell == current) {
				found = true;
				break;
			}
			index++;
		}
		if (found) {
			if (index < cells.length - 1) {
				int i = 0;
				for (; i < length && index - i >= 0; i++) {
					cells[index - i].setOccupied(true);
				}
				if (++i == length) {
					cells[index - i].setOccupied(false);
				}
			} else {
				for (int i = 0; i < length && index - i >= 0; i++) {
					cells[index - i].setOccupied(false);
				}
			}
		}
	}

	public void print() {
		for (Cell cell : cells) {
			System.out.println("\t\t" + cell.toString());
		}
	}

}