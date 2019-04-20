package gameLogic;

import java.util.*;

/**
 * This class is responsible for the tunnels which connect the two entrances.
 */
class Tunnel extends Segment {

	 /**
     * This constructor assigns the entrances.
     */

	public Tunnel(TunnelEntrance entrance1, TunnelEntrance entrance2, int length) {
		super();
		cells = new Cell[4 + length];
		LinkedList<Cell> temp = new LinkedList<Cell>();
		cells[7] = new Cell();
		cells[8] = new Cell();
		for (int i = 0; i < length + 2; i++) {
			cells[i] = new Cell();
			temp.add(cells[i]);
		}
		path01 = new Path(temp.toArray(new Cell[temp.size()]));
		temp = new LinkedList<Cell>();
		temp.add(cells[length + 2]);
		for (int i = length; i > 0; i--) {
			temp.add(cells[i]);
		}
		temp.add(cells[length + 3]);
		path10 = new Path(temp.toArray(new Cell[temp.size()]));
		end0 = path10.GetEndLogic();
		end1 = path01.GetEndLogic();
		this.entrance0 = entrance1;
		this.entrance1 = entrance2;
		end0.Connect(this.entrance0.GetTunnelExitPath().GetStart());
		end1.Connect(this.entrance1.GetTunnelExitPath().GetStart());
	}

	/**
	 * This attribute stores one entrance of the tunnel.
	 */
	protected TunnelEntrance entrance0;

	/**
	 * This attribute stores another entrance of the tunnel.
	 */
	protected TunnelEntrance entrance1;
	/**
	 * This attribute stores the length of a tunnel.
	 */
	protected int tlength;
	/**
	 * This method returns the other end of the tunnel
	 * passed on to this method as te.
	 */
	
	public TunnelEntrance GetTheOtherEnd(TunnelEntrance te) {
		if (te == entrance0) {
			return entrance1;
		}
		if (te == entrance1) {
			return entrance0;
		}
		return null;
	}
	
	public Cell PathStartFor(TunnelEntrance te) {
		if (te == entrance0) {
			return path01.GetStart();
		}
		if (te == entrance1) {
			return path10.GetStart();
		}
		return null;
	}
	
	public boolean IsEndFree(int endID) {
		return true;
	}
	
	public void printFull() {
		System.out.println("Tunnel \"" + id + "\"");
		System.out.println("\t path01");
		path01.print();
		System.out.println("\t path10");
		path10.print();
	}

	

}