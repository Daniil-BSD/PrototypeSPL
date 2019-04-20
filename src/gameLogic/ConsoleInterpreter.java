package gameLogic;

import java.security.InvalidParameterException;
import gameLogic.*;
import main.Main;

/**
 * This class implements the console's behavior for the user to control the game
 * in one way or another.
 */
public abstract class ConsoleInterpreter {

	/**
	 * This method accepts the string input entered by the user, splits it using
	 * space as a separator and performs specific actions based on the command's
	 * content.
	 */
	public static void ConsoleLine(String input) throws Exception {
		System.out.println(">>ConsoleLine(string input): interprets the uuser string input");

		String[] command = input.split(" ");
		if (command.length > 0) {

			if (command[0].equals("add") && command.length > 2) {
				Segment newSegment = LevelContainer.FindSegment(command[2]);

				if (newSegment == null) {

					if (command[1].equals("fork")) {
						newSegment = new Fork(command[2]);
					} else if (command[1].equals("station")) {
						// newSegment = new Station(command[2]);
					} else if (command[1].equals("straight")) {
						System.out.println("Straight Added");
						newSegment = new Straight(command[2]);
					}
					if (newSegment != null) {
						LevelContainer.addSegment(newSegment);
					}
				}
			}
			if (command[0].equals("select") && command.length > 1) {
				Segment presentSegment = LevelContainer.FindSegment(command[1]);
				if (presentSegment != null) {
					presentSegment.Select();
				}
			}

			if (command[0].equals("join") && !command[1].equals("entrances") && command.length > 4) {
				try {
					LevelContainer.Join(command[1], Integer.parseInt(command[2]), command[3],
							Integer.parseInt(command[4]));
				} catch (NumberFormatException e) {
					System.out.println("Incorrect input");
				}
			}
			if (command[0].equals("join") && command[1].equals("entrances") && command.length > 4) {
				Segment entrance1 = LevelContainer.FindSegment(command[2]);
				Segment entrance2 = LevelContainer.FindSegment(command[3]);
				if (entrance1.getClass() == TunnelEntrance.class && entrance2.getClass() == TunnelEntrance.class) {
					if (!LevelContainer.IsTunnelPossibleBetween((TunnelEntrance) entrance1,
							(TunnelEntrance) entrance2)) {
						Tunnel tunnel = new Tunnel((TunnelEntrance) entrance1, (TunnelEntrance) entrance2);
						LevelContainer.addTunnel(tunnel);

					}

				} else {
					throw new InvalidParameterException("Incorrect segments");

				}

			}
			if (command[0].equals("place") && command.length > 4) {
				Segment entrance = LevelContainer.FindSegment(command[1]);
				try {
					entrance.GetPathEndingWith(Integer.parseInt(command[2]));
				} catch (NumberFormatException e) {
					System.out.println("Incorrect input");
				}
			}
			if (command[0].equals("start")) {

				System.out.println("//starting the game");
				LevelContainer.Start();

			}
			if (command[0].equals("stop")) {

				System.out.println("//stopping the game");
				LevelContainer.Stop();

			}
			if (command[0].equals("load") && command.length > 1) {

				LevelContainer.Load(command[1]);

			}

			if (command[0].equals("init") && command.length > 1) {
				if (command[1].equals("level")) {
					LevelInitializer.LevelConstructionDemoInitializer();
					System.out.println("//Initializing Level for Level Construction Demonstration");
				}
				if (command[1].equals("fork")) {
					LevelInitializer.ForkDemoInitializer();
					System.out.println("//Initializing Level for Fork Demonstration");
				}
				if (command[1].equals("station")) {
					LevelInitializer.StatonDemoInitializer();
					System.out.println("//Initializing Level for Station Demonstration");
				}
				if (command[1].equals("collision")) {
					LevelInitializer.CollisionDemoInitializer();
					System.out.println("//Initializing Level for Collision Demonstration");
				}
				if (command[1].equals("tunnel")) {
					LevelInitializer.TunnelDemoInitializer();
					System.out.println("//Initializing Level for Tunnel Construction Demonstration");
				}
			}
			if (command[0].equals("quit")) {
				Main.run = false;
			}

		}

	}
}
