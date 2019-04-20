package gameLogic;

import java.util.*;

/**
 * This class is responsible for the tunnels which connect the two entrances.
 */
class Tunnel extends Segment {

	 /**
     * This constructor assigns the entrances.
     */

	public Tunnel(TunnelEntrance entrance1, TunnelEntrance entrance2) {
		this.entrance0 = entrance1;
		this.entrance1 = entrance2;

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
	
	public void printFull() {
		System.out.println("Straight \"" + id + "\"");
		System.out.println("\t path01");
		path01.print();
		System.out.println("\t path10");
		path10.print();
	}

	

}