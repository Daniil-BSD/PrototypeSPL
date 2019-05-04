package gameLogic;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import javax.print.attribute.PrintJobAttributeSet;

import userInterface.GameDisplay;
import userInterface.MainFrame;

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

	public static final String PERSISTANCE_PATH = "C:\\Users\\Public\\";
	public static final String FILE_EXTENSION = ".lvl";

	private static MainFrame frame = null;
	private static GameDisplay gameDisplay = null;
	private static InputInterpriter inputInterpriter = null;

	public static void OpenWindow() {
		if (frame == null) {
			System.out.println("Opening the window");
			frame = new MainFrame();
			inputInterpriter = new InputInterpriter();
			frame.addActionEventListenerToButtons(inputInterpriter);
			gameDisplay = frame.getGameDisplay();
		}
	}
	
	
	public static void FullLevelRedraw() {
		if(gameDisplay != null && level != null) {
			gameDisplay.FullRedraw(level.segments, level.trains);
		}
	}

	/**
	 * This method gets the cars which are arrived at the final station. If the
	 * train is empty, the train waits at the Final Station. If the train is not
	 * empty, the game is lost and the level is restarted.
	 */
	public static void FinalReport(Car car) {
		if (car.IsEmpty()) {
			if (level.activeTrains.contains(car))
				level.activeTrains.remove(car);
			if (level.activeTrains.isEmpty())
				Victory();
		} else {
			Defeat();
		}
	}

	/**
	 * This method starts the process of joining two segments. It is called by the
	 * Controller.
	 */
	public static void Join(String Sgm1ID, int end1ID, String Sgm2ID, int end2ID) {
		if (!level.gameActive) {
			Segment segment1 = level.FindSegment(Sgm1ID);
			Segment segment2 = level.FindSegment(Sgm2ID);
			if (segment1 != null && segment2 != null) {
				if (segment1.IsEndFree(end1ID) && segment2.IsEndFree(end2ID)) {
					Cell end1 = segment1.GetFreeEnd(end1ID);
					Cell end2 = segment2.GetFreeEnd(end2ID);
					segment1.ConnectTo(end1ID, end2);
					segment2.ConnectTo(end2ID, end1);
				} else {
					System.out.println("Incorrect end(s)");
					return;
				}
			} else {
				System.out.println("Incorrect segment(s)");
			}
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

	public static void Step(int index) {
		if (level.trains.size() > index) {
			System.out.println("Stepping the specified locomotive");
			level.trains.get(index).Step();
		}
		System.out.println("Locomotive was not found");

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
		if (!level.gameActive) {
			System.out.println("Tunnel Constructed");
			te.FullClear();
			selected.FullClear();
			Tunnel newTunnel = LevelContainer.level.GetTunnelBetween(te, selected);
			te.SetTunnel(newTunnel);
			selected.SetTunnel(newTunnel);
			selected = null;
		}
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
		if(gameDisplay != null)
			gameDisplay.AddSegment(sgm);
	}

	/**
	 * This method registers a new locomotive to the level.
	 */
	public static void addLocomotive(Locomotive locomotive) {
		level.addLcomotive(locomotive);
		if(gameDisplay != null) {
			gameDisplay.AddCar(locomotive);
		}
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
	 * This method is called when a train derailed, which leads to defeat on the
	 * current level.
	 */
	public static void Derailed(Car car) {
		System.out.println("A car was derailed");
		Defeat();
	}

	/**
	 * This method is called when a train collided with another car, which, again,
	 * leads to defeat.
	 */
	public static void Collided(Car car) {
		System.out.println("A car has collided");
		Defeat();
	}

	/**
	 * This method and is called when the conditions for winning on the level have
	 * been fulfilled on the current level.
	 */
	public static void Victory(String message) {
		Victory();
	}

	public static void Victory() {
		System.out.println("Victory!");
		Stop();
	}

	/**
	 * This method is called when the game was lost. It stops the game on the level.
	 */
	public static void Defeat(String message) {
		System.out.print(message);
		Defeat();
	}

	public static void Defeat() {
		if (gameTick != null) {
			Pause();
			System.out.println("The game is lost! Duration: " + gameTick.getTime());
		} else {
			System.out.println("The game is lost!");
		}
		Stop();
	}

	/**
	 * This method starts the game on the current level, while also starting the
	 * clock.
	 */

	public static void StartWithoutClock() {
		if (gameTick == null) {
			level.Run();
			gameTick = new GameTick(100);
			gameTick.active = false;
			gameTick.start();
		}
	}

	public static void Start() {
		if (gameTick == null) {
			level.Run();
			gameTick = new GameTick(100);
			gameTick.start();
		}
	}

	public static void Resume() {
		if (gameTick != null) {
			gameTick.active = true;
		}
	}

	public static void Pause() {
		if (gameTick != null) {
			gameTick.active = false;
		}
	}

	/**
	 * This method is loads the level to the level container.
	 */
	public static void Load(String name) {
		File file = new File(PERSISTANCE_PATH + name + FILE_EXTENSION);
		if (file.isFile() && file.getName().endsWith(FILE_EXTENSION) && file.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				level = (Level) ois.readObject();
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Failed to load the file (does not exsist)");
			Load(new Level());
		}
	}

	/**
	 * This method is saves the level to the file.
	 */
	public static void Save(String name) {
		File file = new File(PERSISTANCE_PATH + name + FILE_EXTENSION);
		if (file.exists() && file.isFile() && file.getName().endsWith(FILE_EXTENSION) || !file.exists()) {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(level);
				oos.close();
				System.out.println("Saved into the file \"" + file.getName() + "\"");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Failed to save into the file (Try a simpler name)");
		}
	}

	public static void Load(Level level) {
		Stop();
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

	public static void printTypes() {
		for (Segment s : level.segments)
			System.out.println("\t" + s.getClass().getSimpleName());

	}

	public static void printSimpleNames() {
		for (Segment s : level.segments)
			System.out.println("\t\"" + s.id + "\"");
	}

	public static void printSegments() {
		System.out.println("Printing the segment types, their identifiers and the paths with cells belonging to them.");
		for (Segment s : level.segments)
			System.out.println("\t" + s.getClass().getSimpleName() + " \"" + s.id + "\"");
	}

	public static void printTrains() {
		int i = 0;
		System.out.println("Printing trains");
		for (Locomotive l : level.trains) {
			l.printFull(0, i++);
		}
	}

	public static void printAll() {
		printFull();
		printTrains();

	}

	public static void printFull() {
		if (level.segments.size() == 0) {
			System.out.println("No data");
			return;
		}
		for (Segment s : level.segments) {

			s.printFull();
		}

	}

}

class GameTick extends Thread {
	public volatile boolean run = false;
	public volatile boolean active;
	private final int interval;
	private int time;

	public int getTime() {
		return time;
	}

	public GameTick(int interval) {
		time = 0;
		run = false;
		active = true;
		this.interval = interval;
	}

	@Override
	public void run() {
		run = true;
		while (run) {
			if (active) {
				LevelContainer.Tick();
				time++;
			}
			try {
				sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}