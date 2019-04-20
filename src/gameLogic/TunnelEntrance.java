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
	
	public void printFull() {
		System.out.println("Fork \"" + id + "\"");
		System.out.println("\t path01");
		path01.print();
		System.out.println("\t path10");
		path10.print();
	}
}