package gameLogic;

import java.io.Console;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * This class stores all the levels of the game. Also, the levels are managed
 * internally by this class. The segments are joined and the tunnels are
 * constructed by it.
 */
abstract class LevelContainer {

	/**
	 * This attribute stores the active level.
	 */
	private static Level level;

	/**
	 * This attribute is needed for the game's clock and the Tick() methods.
	 */

	private static volatile GameTick gameTick = null;

	/**
	 * This attribute stores the tunnel entrance highlighted by the player.
	 */
	private static TunnelEntrance selected;

	/**
	 * This attribute stores the tunnel entrance highlighted by the player.
	 */
	private static Segment selSegment;
	/**
	 * This method gets the cars which are arrived at the final station. If the
	 * train is empty, the train waits at the Final Station. If the train is not
	 * empty, the game is lost and the level is restarted.
	 */
	public static void FinalReport(Car car) {
		if (car.IsEmpty())
			Victory();
		else
			Defeat();
		Stop();
	}

	/**
	 * This method starts the process of joining two segments. It is called by the
	 * Controller.
	 */
	public static void Join(String Sgm1ID, int end1ID, String Sgm2ID, int end2ID) {
		Segment segment1 = level.FindSegment(Sgm1ID);
		Segment segment2 = level.FindSegment(Sgm2ID);
		if (segment1 != null && segment2 != null) {
			if (segment1.IsEndFree(end1ID) && segment2.IsEndFree(end2ID)) {
				Cell end1 = segment1.GetFreeEnd(end1ID);
				Cell end2 = segment2.GetFreeEnd(end2ID);
				segment1.ConnectTo(end1ID, end2);
				segment2.ConnectTo(end2ID, end1);
			} else {
			}
		} else {
		}

	}

	/**
	 * This method takes the string segment identifier and returns the respective
	 * Segment. If the segment does not exist, returns null.
	 */
	public static Segment FindSegment(String sgmID) {
		Segment ret = level.FindSegment(sgmID);
		return ret;
	}

	/**
	 * This method returns true if a tunnel entrance is selected to construct a
	 * tunnel between two points.
	 */
	public static boolean IsEntranceSelected() {
	
		return selected != null;
	}

	/**
	 * This method returns boolean value after checking if the tunnel is possible
	 * from the given entrance.
	 */
	public static boolean IsTunnelPossibleFrom(TunnelEntrance te) {

		if (te == null || selected == null)
			return false;
		return level.IsTunnelPossibleBetween(te, selected);
	}

	/**
	 * 
	 * If the tunnel is possible between the two points, this method clears the
	 * current tunnels of the two entrances, creates a new tunnel and sets it for
	 * the te and the selected entrance.
	 */
	public static void ConstructFrom(TunnelEntrance te) {
		te.FullClear();
		selected.FullClear();
		Tunnel newTunnel = LevelContainer.level.GetTunnelBetween(te, selected);
		te.SetTunnel(newTunnel);
		selected.SetTunnel(newTunnel);
		selected = null;
	}

	/**
	 * This method registers a new tunnel to the level.
	 */
	public static void addTunnel(Tunnel newTunnel) {
		level.addTunnel(newTunnel);
	}

	/**
	 * This method registers a new segment to the level.
	 */
	public static void addSegment(Segment sgm) {
		level.addSegment(sgm);
	}

	/**
	 * This method registers a new locomotive to the level.
	 */
	public static void addLocomotive(Locomotive locomotive) {
		level.addLcomotive(locomotive);
	}

	/**
	 * This method is called by the above method to check if the tunnel is possible
	 * between the two entrances.
	 */
	public static boolean IsTunnelPossibleBetween(TunnelEntrance te, TunnelEntrance selected) {
		return level.IsTunnelPossibleBetween(te, selected);
	}

	/**
	 * This method returns true if the player selected the same tunnel entrance
	 * twice.
	 */
	public static boolean IsThisSelected(TunnelEntrance te) {
		return te == selected;
	}

	/**
	 * This method selects a tunnel entrance.
	 */
	public static void SelectEntrance(TunnelEntrance te) {
		selected = te;

	}
	/**
	 * This method selects a segment.
	 */
	public static void SelectSegment(Segment se) {
		selSegment = se;

	}
	/**
	 * This method is called when a train derailed, which leads to defeat on the
	 * current level.
	 */
	public static void Derailed(Car car) {
		Defeat();
	}

	/**
	 * This method is called when a train collided with another car, which, again,
	 * leads to defeat.
	 */
	public static void Collided(Car car) {
		Defeat();
	}

	/**
	 * This method and is called when the conditions for winning on the level have been
	 * fulfilled on the current level.
	 */
	public static void Victory(String message) {
		Defeat();
	}

	public static void Victory() {
		Stop();
	}

	/**
	 * This method is called when the game was lost. It stops the game on the level.
	 */
	public static void Defeat(String message) {
		Defeat();
	}

	public static void Defeat() {
		Stop();
	}
	/**
	 * This method starts the game on the current level, while also starting the clock.
	 */

	public static void Start() {
		gameTick = new GameTick(10);
		gameTick.start();
	}
	/**
	 * This method is loads the level to the level container.
	 */
	public static void Load(String name) {
		try
		{
			FileInputStream fileIn = new FileInputStream("C:\\"+name+".lvl");
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        level = (Level) in.readObject();
	        in.close();
	        fileIn.close();
	        }
		catch(IOException i)
		{
			i.printStackTrace();
	        return;
	        }
		catch(ClassNotFoundException c)
		{
			System.out.println("Level class not found");
	        c.printStackTrace();
	        return;
	        }
	}
	/**
	 * This method is saves the level to the file.
	 */
	public static void Save(String name) {
	      try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("C:\\"+name+".lvl");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(level);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in C:\\"+name+".lvl");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}

	public static void Load(Level level) {
		LevelContainer.level = level;
	}

	public static void Stop() {
		if (gameTick != null) {
			gameTick.run = false;
			try {
				gameTick.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gameTick = null;
		}
	}

	public static void Tick() {
		level.Tick();
	}
}

class GameTick extends Thread {
	public volatile boolean run = false;
	private final int interval;

	public GameTick(int interval) {
		run = false;
		this.interval = interval;
	}

	@Override
	public void run() {
		run = true;
		while (run) {
			LevelContainer.Tick();
			try {
				sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}