package segments;

import java.util.*;

import cellLogic.PathEnd;
import gameLogic.*;

/**
 * This class is responsible for directing the train along the different paths
 * that go out of the fork depending on the player¡¦s choice.
 */
@SuppressWarnings("serial")
public class Fork extends Segment {
	/**
	 * Constructor that creates the fork and the respective paths.
	 */

	public Fork(String id) {
		super(id);
		cells = new Cell[17];
		LinkedList<Cell> temp = new LinkedList<Cell>();
		LinkedList<Cell> temp2 = new LinkedList<Cell>();
		
		
		//for (int i = 0; i < 17; i++) {		}
		cells[0] = new Cell(0,4); 
		cells[1] = new Cell(0,3); 
		cells[2] = new Cell(-1,2); 
		cells[3] = new Cell(1,2); 
		cells[4] = new Cell(-2,1);
		cells[5] = new Cell(-3,0);
		cells[6] = new Cell(-4,0);
		cells[7] = new Cell(2,1);
		cells[8] = new Cell(3,0);
		cells[9] = new Cell(4,0);
		cells[10] = new Cell(-4,0);
		cells[11] = new Cell(-2,1); 
		cells[12] = new Cell(-1,2);
		cells[13] = new Cell(4,0);
		cells[14] = new Cell(2,1);
		cells[15] = new Cell(1,2);
		cells[16] = new Cell(0,4);
		
		int i = 0;
		for (; i < 2; i++) {
			temp.add(cells[i]);
		}
		for (; i < 4; i++) {
			temp2.add(cells[i]);
		}
		selectorPath = new SelectorPath(temp.toArray(new Cell[temp.size()]), temp2.toArray(new Cell[temp2.size()]));
		temp = new LinkedList<Cell>();
		temp2 = new LinkedList<Cell>();
		for (; i < 7; i++) {
			temp.add(cells[i]);
		}
		for (; i < 10; i++) {
			temp2.add(cells[i]);
		}
		path01 = new Path(temp.toArray(new Cell[temp.size()]));
		path02 = new Path(temp2.toArray(new Cell[temp2.size()]));
		selectorPath.GetEndLogicByIndex(0).Connect(path01.GetStart());
		selectorPath.GetEndLogicByIndex(1).Connect(path02.GetStart());
		end1 = path01.GetEndLogic();
		end2 = path02.GetEndLogic();
		temp = new LinkedList<Cell>();
		temp2 = new LinkedList<Cell>();
		temp.add(cells[10]);
		temp.add(cells[5]);
		temp.add(cells[11]);
		temp.add(cells[12]);
		temp.add(cells[1]);
		temp.add(cells[16]);
		temp2.add(cells[13]);
		temp2.add(cells[8]);
		temp2.add(cells[14]);
		temp2.add(cells[15]);
		temp2.add(cells[1]);
		temp2.add(cells[16]);
		path10 = new Path(temp.toArray(new Cell[temp.size()]));
		path20 = new Path(temp2.toArray(new Cell[temp2.size()]));
		end0 = path10.GetEndLogic();
		setImage("res/Textures/Fork.png");
	}

	/**
	 * This attribute stores the selectorPath for the Fork to let the player select
	 * the outgoing path.
	 */
	private SelectorPath selectorPath;

	/**
	 * This attribute stores one of the fork¡¦s exits.
	 */
	private Path path20;

	/**
	 * This attribute stores another exit.
	 */
	private Path path02;

	private PathEnd end2;

	/**
	 * This method returns true or false respectively if the cell out of the fork is
	 * occupied by a car or not
	 */
	public boolean IsEmpty() {
		for (Cell cell : this.cells) {
			if (cell.IsOccupied()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public Path GetPathEndingWith (int endID) {
		if (endID == 2)
			return path02;
		if (endID == 1)
			return path01;
		return null;
	}


	/**
	 * This method chooses the path for the train on the fork. The first cell of the
	 * path should be empty which is checked by this method. It is called by the
	 * controller which is controlled by the player.
	 */
	public void Select() {
		if (IsEmpty()) {
			selectorPath.SelectNextExit();

		}
	}

	/**
	 * This method returns a cell that is an edge of the segment if this cell is
	 * free.
	 */
	public Cell GetFreeEnd(int endID) {
		if (!IsEndFree(endID))
			return null;
		if (endID == 0)
			return selectorPath.GetStart();
		if (endID == 1)
			return path10.GetStart();
		if (endID == 2)
			return path20.GetStart();
		System.out.println("Incorrect end(s)\n");
		return null;
	}

	/**
	 * This method returns true if the edge of the segment that is identified by the
	 * integer parameter is free.
	 * 
	 */

	public boolean IsEndFree(int endID) {
		if (endID == 0)
			return !end0.HasConnection();
		if (endID == 1)
			return !end1.HasConnection();
		if (endID == 2)
			return !end2.HasConnection();
		return false;
	}

	/**
	 *
	 * This method connects the segments by its ends, one of which is passed on as a
	 * parameter to this method.
	 */
	public void ConnectTo(int endID, Cell end) {
		if (!IsEndFree(endID))
			return;
		if (endID == 0)
			end0.Connect(end);
		if (endID == 1)
			end1.Connect(end);
		if (endID == 2)
			end2.Connect(end);
	}

	// Method purely for skeleton
	/**
	 * 
	 * 
	 * this method returns a cell where a locomotive should be placed for
	 * demonstraton
	 */
	public Cell GET_DEMO_CELL(int index) {
		index %= 3;
		if (index == 1)
			return cells[5];
		if (index == 2)
			return cells[8];
		return cells[0];
	}

	public void printFull() {
		System.out.println("Fork \"" + id + "\"");
		super.printFull();
		System.out.print("\t selectorPath");
		selectorPath.print();
		System.out.print("\t path02");
		path02.print();
		System.out.print("\t path20");
		path20.print();
	}

	
	

}