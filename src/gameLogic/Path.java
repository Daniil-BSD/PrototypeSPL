package gameLogic;

import java.io.Serializable;
import java.security.InvalidParameterException;

import cellLogic.*;

/**
 * The path is a sequence of cells along which the train moves which is
 * implemented by this class.
 */
public class Path implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2709873888519577921L;
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
		for (index = 0; index < cells.length; index++) {
			if (cells[index] == cell) {
				break;
			}
		}

	if (index + 1 < cells.length)
			return cells[index + 1 ];
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
	
	public Cell GetCellByInverseIndex(int index) {
		int temp = cells.length - 1 -index;
		if(temp < 0) throw new IllegalArgumentException();
		return cells[temp];
	}
	
	public int length() {
		return cells.length;
	}

	/**
	 * This method gets the cell, which is occupied by a car and makes the amount of
	 * cells equal to length behind the car occupied as well.
	 */
	public void UpdatePresence(int length, Cell current) {
		int index = 0;
;		boolean found = false;
		for (index = 0; index < cells.length; index++) {
			if (cells[index] == current) {
				found = true;
				break;
			}
		}
		if (found) {
			if (index < cells.length - 1) {
				int i = 0;
				for (; i < length && index - i >= 0; i++) {
					cells[index - i].setOccupied(true);
				}
				if (i == length && index - i >= 0) {
					cells[index - i].setOccupied(false);
				}
			} else {
				for (int i = 0; i < length + 1  && index - i >= 0; i++) {
					cells[index - i].setOccupied(false);
				}
			}
		}
	}

	public void print() {
		System.out.println("\t[" + System.identityHashCode(this) + "]");
		for (Cell cell : cells) {
			System.out.println("\t\t" + cell.toString());
		}
	}

}