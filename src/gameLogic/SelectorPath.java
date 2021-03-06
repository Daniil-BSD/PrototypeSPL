package gameLogic;

import java.security.InvalidParameterException;

import cellLogic.PathEnd;
import cellLogic.PathStart;

/**
 * This class is responsible for choosing the path out of the fork.
 */
@SuppressWarnings("serial")
public class SelectorPath extends Path {

	/**
	 * Constructor that accepts the cells and exits and assigns them to the Selector
	 * Path.
	 * 
	 */
	public SelectorPath(Cell[] cells, Cell[] exits) {
		this.cells = cells;
		this.exits = exits;
		if (this.cells.length > 0 && this.exits.length > 0) {
			this.cells[0].SetLogic(new PathStart(this));
			for (Cell cell : this.exits) {
				cell.SetLogic(new PathEnd());
			}
		} else {
			throw new InvalidParameterException();
		}
		selectedIndex = 0;
	}

	/**
	 * This attribute stores the exits out of the fork.
	 */
	private Cell[] exits;

	/**
	 * This attribute stores the number that corresponds to the player selection
	 * based on which the trains will be directed out of the fork.
	 */

	private int selectedIndex;

	/**
	 * This method chooses the path out of the fork.
	 */
	public void SelectNextExit() {
		selectedIndex = (1 + selectedIndex) % exits.length;
	}

	/**
	 * This method returns the next cell after the given cell keeping in mind that
	 * it's in the fork.
	 */

	@Override
	public Cell NextCell(Cell cell) {
		int index = 0;
		for (Cell localCell : cells) {
			index++;
			if (localCell == cell)
				break;
		}
		if (index < cells.length)
			return cells[index];
		if (index == cells.length)
			return exits[selectedIndex];
		return null;
	}

	/**
	 * This method returns one of the exits that corresponds to the integer
	 * parameter.
	 */

	public Cell GetEndByIndex(int index) {
		return exits[index % exits.length];
	}
	


	/**
	 * This method gets the cell, which is occupied by a car and makes the amount of
	 * cells equal to length behind the car occupied as well.
	 */
	public void UpdatePresence(int length, Cell current) {
		int index = 0;
		boolean found = false;
		for (index = 0; index < cells.length; index++) {
			if (cells[index] == current) {
				found = true;
				break;
			}
		}
		if (found) {
			int i = 0;
			for (; i < length && index - i >= 0; i++) {
				cells[index - i].setOccupied(true);
			}
			if (i == length && index - i >= 0) {
				cells[index - i].setOccupied(false);
			}
		}else {
			found = false;
			for (index = 0; index < cells.length; index++) {
				if (exits[index] == current) {
					found = true;
					break;
				}
			}
			if(found) {
				for (int i = 1; i < length + 1  && cells.length - i >= 0; i++) {
					cells[cells.length - i].setOccupied(false);
				}
			}
		}
	}

	
	/**
     * This method returns an exit that corresponds to the given index.
     */
	@Override
	public Cell GetEnd() {
		return exits[selectedIndex];
	}

	public int GetSelectedIndex() {
		return selectedIndex;
	}
	
	/**
     * This method returns the PathEnd logic of the exit matched with the given integer index.
     */
	public PathEnd GetEndLogicByIndex(int index) {
		return (PathEnd) exits[index % exits.length].GetLogic();
	}
	/**
     * This method returns the PathEnd logic of selected exit.
     */
	@Override
	public PathEnd GetEndLogic() {
		return (PathEnd) exits[selectedIndex].GetLogic();
	}
	
	@Override
	public void print() {
		super.print();
		System.out.println("\t\t<Ends>");
		for (Cell cell : exits) {
			System.out.println("\t\t" + cell.toString());
		}
		System.out.println("\t\t</Ends>");
	}

}