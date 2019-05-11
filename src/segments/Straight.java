package segments;

import java.util.LinkedList;

import gameLogic.*;

/**
 * This class realizes the segment with straight paths i.e, without turns.
 */

@SuppressWarnings("serial")
public class Straight extends Segment {
	
	 /**
     * Constructor that accepts a string id.
     */
	
	public Straight(String id) {
		super(id);
		cells = new Cell[9];
		LinkedList<Cell> temp = new LinkedList<Cell>();
		cells[7] = new Cell(0,-3);
		cells[8] = new Cell(0,3);
		int j=3;
		for (int i = 0; i < 7; i++) {
			cells[i] = new Cell(0,j);
			j--;
			temp.add(cells[i]);
		}
		path01 = new Path(temp.toArray(new Cell[temp.size()]));
		temp = new LinkedList<Cell>();
		temp.add(cells[7]);
		for (int i = 5; i > 0; i--) {
			temp.add(cells[i]);
		}
		temp.add(cells[8]);
		path10 = new Path(temp.toArray(new Cell[temp.size()]));
		end0 = path10.GetEndLogic();
		end1 = path01.GetEndLogic();
	}
	
	public void printFull() {
		System.out.println("Straight \"" + id + "\"");
		super.printFull();
	}


	@Override
	public String getTexturePath() {
		return "res/Textures/FullStraight.png";
	}
}
