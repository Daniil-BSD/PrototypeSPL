package gameLogic;

import java.util.*;

/**
 * This class is responsible for the entrances of the tunnels. When the train
 * disappears at one end, it emerges at the other end which is implemented by
 * this class.
 */
class TunnelEntrance extends Segment {

	/**
	 * Default constructor
	 */
	public TunnelEntrance(String id) {
		super(id);
		cells = new Cell[4];
		LinkedList<Cell> temp = new LinkedList<Cell>();
		cells[0] = new Cell();
		cells[1] = new Cell();
		cells[2] = new Cell();
		cells[3] = new Cell();
		temp.add(cells[1]);
		temp.add(cells[2]);
		path01 = new Path(temp.toArray(new Cell[temp.size()]));
		temp = new LinkedList<Cell>();
		temp.add(cells[4]);
		temp.add(cells[3]);
		path10 = new Path(temp.toArray(new Cell[temp.size()]));
		end0 = path10.GetEndLogic();
		end1 = path01.GetEndLogic();
	}

	/**
	 * This attribute stores the tunnel which corresponds to the given entrance.
	 */
	protected Tunnel tunnel;

	/**
	 * This method sets the entrance of the tunnel to null.
	 */
	public void Clear() {
		tunnel = null;
	}

	/**
	 * This method sets the tunnel of both entrances to null.
	 */
	public void FullClear() {
		
		if (tunnel != null)
			tunnel.GetTheOtherEnd(this).Clear();
		Clear();
	}

	/**
	 * This method sets the tunnel for the current Tunnel Entrance.
	 */
	public void SetTunnel(Tunnel newTunnel) {
		this.tunnel = newTunnel;
	}

	/**
	 * This method selects the current entrance.
	 */

	@Override
	public void Select() {
		if (LevelContainer.IsEntranceSelected()) {
			if (LevelContainer.IsTunnelPossibleFrom(this)) {
				LevelContainer.ConstructFrom(this);
			}
			if (LevelContainer.IsThisSelected(this)) {
				LevelContainer.SelectEntrance(null);
			}

		} else {
			LevelContainer.SelectEntrance(this);
		}

	}

	@Override
	public boolean IsEndFree(int endID) {
		if (endID == 0)
			return !end0.HasConnection();
		return false;
	}
	
	@Override
	public Path GetPathEndingWith(int endID) {
		if (endID == 0)
			return path01;
		return null;

	}
	
	public Path GetTunnelExitPath() {
		return path10;
	}

	@Override
	public void printFull() {
		System.out.println("Tunnel Entrance \"" + id + "\"");
		System.out.println("\t path01");
		path01.print();
		System.out.println("\t path10");
		path10.print();
	}
}