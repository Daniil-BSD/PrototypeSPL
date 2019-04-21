package gameLogic;

import java.awt.Color;

/**
 * This class initializes the level for the demonstration purposes.
 */
public abstract class LevelInitializer {

	/**
	 * These method is called for the sake of demonstrating the code functionality.
	 * It is called by the ConsoleInterpreter. Initializes the fork on the level.
	 */
	public static void ForkDemoInitializer() {

		LevelContainer.Load(new Level());
		Fork fork = new Fork("fork");
		LevelContainer.addSegment(fork);
		LevelContainer.addLocomotive(new Locomotive(fork.GET_DEMO_CELL(0)));
		fork.GET_DEMO_CELL(1).SetLogic(new LoggerCell("car goes west"));
		fork.GET_DEMO_CELL(2).SetLogic(new LoggerCell("car goes east"));
	}

	/**
	 * This method initializes the level.
	 */
	public static void LevelConstructionDemoInitializer() {
		LevelContainer.Load(new Level());
	}

	/**
	 * This method creates a station adds it to the level while also creating a
	 * train with a passenger car attached to it.
	 */
	public static void StatonDemoInitializer() {
		LevelContainer.Load(new Level());
		Station station = new FinalStation("station", new Colors[] { Colors.Blue });
		LevelContainer.addSegment(station);
		PassengerCar passengerCar = new PassengerCar(station.GET_DEMO_CELL(0), Colors.Blue);
		Locomotive locomotive = new Locomotive(station.GET_DEMO_CELL(1));
		locomotive.attach(passengerCar);
		passengerCar.SetPath(station.path01);
		locomotive.SetPath(station.path01);
		LevelContainer.addLocomotive(locomotive);
	}

	/**
	 * This method imitates the collision of two trains by creating them and putting
	 * them on the same path.
	 */
	public static void CollisionDemoInitializer() {
		LevelContainer.Load(new Level());
		Straight straight = new Straight("straight");
		LevelContainer.addSegment(straight);
		Locomotive locomotive1 = new Locomotive(straight.GET_DEMO_CELL(0));
		Locomotive locomotive2 = new Locomotive(straight.GET_DEMO_CELL(1));
		locomotive1.SetPath(straight.GET_DEMO_PATHL(0));
		locomotive2.SetPath(straight.GET_DEMO_PATHL(1));
		LevelContainer.addLocomotive(locomotive1);
		LevelContainer.addLocomotive(locomotive2);
	}

	/**
	 * This method initializes a Tunnel with its entrances.
	 */
	public static void TunnelDemoInitializer() {
		LevelContainer.Load(new Level());
		TunnelEntrance entrance1 = new TunnelEntrance("e1");
		TunnelEntrance entrance2 = new TunnelEntrance("e2");
		TunnelEntrance entrance3 = new TunnelEntrance("e3");
		//Tunnel tunnel = new Tunnel(entrance1, entrance2);
		LevelContainer.addSegment(entrance1);
		LevelContainer.addSegment(entrance2);
		LevelContainer.addSegment(entrance3);
		//LevelContainer.addTunnel(tunnel);
	}
}

/**
 * This class is needed to add a specific logic to the cell, which is, again,
 * needed for the demonstration purposes.
 */
class LoggerCell implements CellLogic {

	/**
	 * This attribute stores the string value to check the output.
	 */
	public String output;

	/**
	 * Copy constructor.
	 */
	public LoggerCell(String output) {
		this.output = output;

	}

	/**
	 * This method prints the output value to the console.
	 */

	@Override
	public boolean LogicRequest(Car car) {
		System.out.println(output);
		return true;
	}
}