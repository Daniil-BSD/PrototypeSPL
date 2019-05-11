package segments;

import java.util.*;
import gameLogic.*;

/**
 * This class is responsible for the tunnels which connect the two entrances.
 */
@SuppressWarnings("serial")
public class Tunnel extends Segment {

	 /**
     * This constructor assigns the entrances.
     */

	public Tunnel(TunnelEntrance entrance1, TunnelEntrance entrance2, int length) {
		super();
		cells = new Cell[4 + length];
		LinkedList<Cell> temp = new LinkedList<Cell>();
		cells[length + 2] = new Cell(0,0, false);
		cells[length + 3] = new Cell(0,0, false);
		for (int i = 0; i < length + 2; i++) {
			cells[i] = new Cell(0,0, false);
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
		end0.Connect(this.getEntrance0().GetTunnelExitPath().GetStart());
		end1.Connect(this.getEntrance1().GetTunnelExitPath().GetStart());
	}

	/**
	 * This attribute stores one entrance of the tunnel.
	 */
	private TunnelEntrance entrance0;

	/**
	 * This attribute stores another entrance of the tunnel.
	 */
	private TunnelEntrance entrance1;
	/**
	 * This attribute stores the length of a tunnel.
	 */
	protected int tlength;
	/**
	 * This method returns the other end of the tunnel
	 * passed on to this method as te.
	 */
	
	public TunnelEntrance GetTheOtherEnd(TunnelEntrance te) {
		if (te == getEntrance0()) {
			return getEntrance1();
		}
		if (te == getEntrance1()) {
			return getEntrance0();
		}
		return null;
	}
	
	public Cell PathStartFor(TunnelEntrance te) {
		if (te == getEntrance0()) {
			return path01.GetStart();
		}
		if (te == getEntrance1()) {
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

	/**
	 * @return the entrance0
	 */
	public TunnelEntrance getEntrance0() {
		return entrance0;
	}

	/**
	 * @return the entrance1
	 */
	public TunnelEntrance getEntrance1() {
		return entrance1;
	}

	@Override
	public String getTexturePath() {
		return "NONE";
	}

	@Override
	public vec2 getSize() {
		return new vec2(7, 7);
	}
}