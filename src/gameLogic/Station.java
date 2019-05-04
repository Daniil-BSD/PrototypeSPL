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
		cells = new Cell[18];
		LinkedList<Cell> temp = new LinkedList<Cell>();
		for (int i = 0; i <= 13; i++) {
			cells[i] = new Cell(0,3-i);
		}
		cells[14] = new Cell(0, -10);
		cells[15] = new Cell(0, -8);
		cells[16] = new Cell(0, 1);
		cells[17] = new Cell(0, 3);
		for (int i = 0; i < 14; i++) {
			temp.add(cells[i]);
		}
		path01 = new Path(temp.toArray(new Cell[temp.size()]));
		temp = new LinkedList<Cell>();
		for (int i = 13; i >= 0; i--) {
			switch(i){
			case 13: 
				temp.add(cells[14]);
				break;
			case 11: 
				temp.add(cells[15]);
				break;
			case 2: 
				temp.add(cells[16]);
				break;
			case 0: 
				temp.add(cells[17]);
				break;
			default:
				temp.add(cells[i]);
			}
		}
		path10 = new Path(temp.toArray(new Cell[temp.size()]));
		end0 = path10.GetEndLogic();
		end1 = path01.GetEndLogic();
		cells[11].SetLogic(new StationLogic(this));
		cells[16].SetLogic(new StationLogic(this));
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
	public Colors[] getColors() {
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
			return cells[9];
		return cells[7];
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
		System.out.print("Colors: ");
		for(int i = 0; i< colors.length; i++) {
			System.out.print(colors[i].name() + " ");
		}
		System.out.println();
		super.printFull();
	}
}