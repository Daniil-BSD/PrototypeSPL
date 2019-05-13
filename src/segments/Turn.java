package segments;

import java.util.LinkedList;

import gameLogic.Cell;
import gameLogic.Path;
import gameLogic.Segment;
import gameLogic.vec2;

public class Turn  extends Segment {
	
	 /**
    * Constructor that accepts a string id.
    */
	
	public Turn(String id) {
		super(id);
		cells = new Cell[8];
		LinkedList<Cell> temp = new LinkedList<Cell>();
		cells[0] = new Cell(0,3); 
		cells[1] = new Cell(0,2); 
		cells[2] = new Cell(0.5f,1); 
		cells[3] = new Cell(1,0.5f);
		cells[4] = new Cell(2,0);
		cells[5] = new Cell(3,0);
		cells[6] = new Cell(3,0);
		cells[7] = new Cell(0,3);
		for (int i = 0; i < 6; i++) {
			temp.add(cells[i]);
		}
		path01 = new Path(temp.toArray(new Cell[temp.size()]));
		temp = new LinkedList<Cell>();
		temp.add(cells[6]);
		for (int i = 4; i > 0; i--) {
			temp.add(cells[i]);
		}
		temp.add(cells[7]);
		path10 = new Path(temp.toArray(new Cell[temp.size()]));
		end0 = path10.GetEndLogic();
		end1 = path01.GetEndLogic();
	}
	
	public void printFull() {
		System.out.println("Turn \"" + id + "\"");
		super.printFull();
	}


	@Override
	public String getTexturePath() {
		return "res/Textures/FullTurn.png";
	}

	@Override
	public vec2 getSize() {
		return new vec2(7, 7);
	}
}
