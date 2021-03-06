package gameLogic;

import java.io.Serializable;
import java.util.*;
import cars.Locomotive;
import segments.Tunnel;
import segments.TunnelEntrance;

/**
 * This class manages the levels of the game which store cars, segments and
 * cells within them.
 */

class Level implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4521202955123700825L;

	/**
	 * Default constructor
	 */
	public Level() {
		trains = new ArrayList<Locomotive>();
		segments = new ArrayList<Segment>();
		tunnels = new ArrayList<Tunnel>();
		gameActive = false;
	}

	/**
	 * This attribute stores the trains of the level.
	 */
	protected ArrayList<Locomotive> trains;
	protected ArrayList<Locomotive> activeTrains;
	protected boolean gameActive;

	/**
	 * Segments of the level are stored by this attribute
	 */
	protected ArrayList<Segment> segments;

	/**
	 * Stores the tunnels of the level.
	 */
	protected ArrayList<Tunnel> tunnels;

	/**
	 * System clock is implemented by this method.
	 */
	
	@SuppressWarnings("unchecked")
	public void Run () {
		activeTrains = (ArrayList<Locomotive>) trains.clone();
		gameActive = true;
	}
	

	public void Tick() {
		if(gameActive) {
			@SuppressWarnings("unchecked")
			ArrayList<Locomotive> activeTrainsClone = (ArrayList<Locomotive>) activeTrains.clone();
			for (Locomotive locomotive : activeTrainsClone) {
				locomotive.Step();
			}
		}
	}

	/**
	 * @param String sgm1ID This method returns a segment which was found by its
	 *               string ID.
	 * @return
	 */
	public Segment FindSegment(String sgmID) {
		for (Segment sg : segments) {
			if (sg.AreYou(sgmID)) {
				return sg;
			}
		}
		//System.out.println("Incorrect segment(s)\n");
		return null;
		
	}

	/**
	 * 
	 * This method is called by the level container and
	 * returns true if the tunnel is possible between the
	 * selected point and the entrance which was passed to
	 * this method. Returns false otherwise.
	 */
	public boolean IsTunnelPossibleBetween(TunnelEntrance te1, TunnelEntrance te2) {
		for (Tunnel tunnel : tunnels) {
			if (te1.equals(tunnel.getEntrance0()) && te2.equals(tunnel.getEntrance1())
					|| te1.equals(tunnel.getEntrance1()) && te2.equals(tunnel.getEntrance0()))  {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * This method is called by the level container and
	 * returns the tunnel between the entrances. Returns null otherwise.
	 */
	public Tunnel GetTunnelBetween(TunnelEntrance te1, TunnelEntrance te2) {
		for (Tunnel tunnel : tunnels) {
			if (te1.equals(tunnel.getEntrance0()) && te2.equals(tunnel.getEntrance1())
					|| te1.equals(tunnel.getEntrance1()) && te2.equals(tunnel.getEntrance0())) {
				return tunnel;
			}
		}
		return null;
	}

	/**
	 * This method adds a new tunnel to the level and it is
	 * called by a similarly named method of the LevelContainer class.
	 */
	public void addTunnel(Tunnel newTunnel) {
		tunnels.add(newTunnel);
	}

	/**
	 * This method adds a new segment to the level and it is
	 * called by a similarly named method of the LevelContainer class.
	 */
	public void addSegment(Segment sgm) {
		for (Segment sg : segments) {
			if (sg.AreYou(sgm.id)) {
				System.out.println("A segment with the same identifier already exists.\n");
				return;
			}
		}
		segments.add(sgm);
		System.out.println("A new segment added");
	}
	
	/**
	 * This method adds a new train locomotive to the level and it is
	 * called by a similarly named method of the LevelContainer class.
	 */

	public void addLcomotive(Locomotive locomotive) {
		trains.add(locomotive);
	}


	public void SelectByPoint(vec2 point) {
		for (Segment segment : segments) {
			segment.selectCallAt(point);
		}
	}

}