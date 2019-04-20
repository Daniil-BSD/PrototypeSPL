package gameLogic;

import java.util.*;

/**
 * This class is responsible for the stations that have color. Depending on
 * these colors, passengers embark or disembark.
 */
class Station extends Segment {

	/**
	 * Default constructor
	 */
	public Station() {
	}

	/**
	 * Constructor that assigns the string id and the given colors to the newly
	 * created Station while also creating paths and needed cells.
	 */

	public Station(String id, Colors[] colors) {
		super(id);
		this.colors = colors;
		cells = new Cell[17];
		LinkedList<Cell> temp = new LinkedList<Cell>();
		cells[0] = new Cell();
		cells[13] = new Cell();
		cells[16] = new Cell();
		for (int i = 1; i < 16; i++) {
			if (i == 13)
				i++;
			cells[i] = new Cell();
			temp.add(cells[i]);
		}
		path01 = new Path(temp.toArray(new Cell[temp.size()]));
		temp = new LinkedList<Cell>();
		for (int i = 16; i >= 0; i--) {
			if (i == 15 || i == 12 || i == 1)
				i--;
			temp.add(cells[i]);
		}
		path10 = new Path(temp.toArray(new Cell[temp.size()]));
		end0 = path10.GetEndLogic();
		end1 = path01.GetEndLogic();
		cells[12].SetLogic(new StationLogic(this));
	}

	/**
	 * This attribute stores the colors of the station.
	 */
	private Colors colors[];
	/**
	 * This method returns false if the station is not final.
	 */
	public boolean IsFinal() {
		return false; // only in the skeleton

		// return false; //proper return
	}

	/**
	 * This attribute returns the colors of the station.
	 * 
	 * @return
	 */
	public Colors[] GetColors() {
		// TODO implement here
		return colors;
	}

	// Method purely for skeleton
	/**
	 * 
	 * 
	 * this method returns a cell where a locomotive should be placed for
	 * demonstraton
	 */
	public Cell GET_DEMO_CELL(int index) {
		index %= 2;
		if (index == 1)
			return cells[11];
		return cells[9];
	}

	// Method purely for skeleton
	/**
	 * 
	 * 
	 * this method returns a cell where a locomotive should be placed for
	 * demonstraton
	 */
	public Path GET_DEMO_PATHL() {
		return path01;
	}
	
	public void printFull() {
		System.out.println("Station \"" + id + "\"");
		System.out.println("\t path01");
		path01.print();
		System.out.println("\t path10");
		path10.print();
	}
}