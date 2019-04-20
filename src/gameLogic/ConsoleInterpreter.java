package gameLogic;

import java.util.List;
import java.security.InvalidParameterException;
import java.util.ArrayList;

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
		System.out.println("Input the commands");
		List<Colors> colors = new ArrayList<>(); 
		String[] command = input.split(" ");
		if (command.length > 0) {

			if (command[0].equals("add") && command.length > 2) {
				System.out.println("The add command was processed");
				Segment newSegment = LevelContainer.FindSegment(command[2]);
				if (newSegment == null) {

					if (command[1].equals("fork") && command.length == 3) {
						System.out.println("Adding a fork...\n");
						newSegment = new Fork(command[2]);
						LevelContainer.addSegment(newSegment);
					} else if (command[1].equals("station") && command.length > 3) {
						System.out.println("Adding a station...\n");
						try {
							for(int i = 3; i< command.length; i++) {
								colors.add(Colors.valueOf(command[i]));
								
							}		
							 newSegment = new FinalStation(command[2], colors.toArray(new Colors[command.length - 3]));
							 LevelContainer.addSegment(newSegment);
							}catch(IllegalArgumentException e) {
								System.out.println("The color was not specified correctly");
							}
					} else if (command[1].equals("straight")) {
						System.out.println("Adding a straight...\n");
						newSegment = new Straight(command[2]);
						LevelContainer.addSegment(newSegment);
					} else if (command[1].equals("finalstation") && command.length > 3) {
						System.out.println("Adding a station...\n");
						try {
						for(int i = 3; i< command.length; i++) {
							colors.add(Colors.valueOf(command[i]));
							
						}
						 newSegment = new FinalStation(command[2], colors.toArray(new Colors[command.length - 3]));
						 LevelContainer.addSegment(newSegment);
						}catch(IllegalArgumentException e) {
							System.out.println("The color was not specified correctly");
						}
					}
				}
				else if (newSegment != null) {
						LevelContainer.addSegment(newSegment);
					}
					
					
				}
			
			if (command[0].equals("select") && command.length > 1) {
				System.out.println("The select command was processed");
				Segment presentSegment = LevelContainer.FindSegment(command[1]);
				if (presentSegment != null) {
					presentSegment.Select();
					System.out.println("Segment selected\n");
				}
				System.out.println("Selected segment was not found\n");
			}

			if (command[0].equals("join") &&  command.length == 5) {
				try {
					LevelContainer.Join(command[1], Integer.parseInt(command[2]), command[3],
							Integer.parseInt(command[4]));
				} catch (NumberFormatException e) {
					System.out.println("Incorrect input");
				}
			}
			if (command[0].equals("connect") && command.length == 4) {
				System.out.println("The connect command was processed\n");
				Segment entrance1 = LevelContainer.FindSegment(command[1]);
				Segment entrance2 = LevelContainer.FindSegment(command[2]);
				if (entrance1.getClass() == TunnelEntrance.class && entrance2.getClass() == TunnelEntrance.class) {
					if (!LevelContainer.IsTunnelPossibleBetween((TunnelEntrance) entrance1,
							(TunnelEntrance) entrance2)) {
						Tunnel tunnel = new Tunnel((TunnelEntrance) entrance1, (TunnelEntrance) entrance2);
						LevelContainer.addTunnel(tunnel);
						System.out.println("Entrances connected\n");
					}

				} else {
					throw new InvalidParameterException("Incorrect segments");

				}

			}
			if (command[0].equals("place") && command.length > 3) {
				System.out.println("The place command was processed\n");
				Segment entrance = LevelContainer.FindSegment(command[1]);
				try {
					entrance.GetPathEndingWith(Integer.parseInt(command[2]));
					System.out.println("The train was successfully placed\n");
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
				System.out.println("Loading...\n");
				LevelContainer.Load(command[1]);
				System.out.println("Empty level loaded.\n");

			}
			if (command[0].equals("save") && command.length > 1) {
				System.out.println("Saving...\n");
				//LevelContainer.Save(command[1]);

			}
			if (command[0].equals("pause") && command.length > 1) {
				System.out.println("Pausing\n");
				

			}
			if (command[0].equals("resume") && command.length > 1) {
				System.out.println("Resuming\n");
				

			}
			if (command[0].equals("step") && command.length > 1) {
				System.out.println("The step command was processed\n");
				try {
				LevelContainer.Step(Integer.parseInt(command[1]));
				} catch(IllegalArgumentException e) {
					System.out.println("Incorrect input");
				}
				

			}
			if (command[0].equals("print") && command.length > 1) {

				if(command[1].equals("sType")){
					System.out.println("Printing types of segments\n");
					LevelContainer.getTypes();
					
				}
				if(command[1].equals("sNames")){
					System.out.println("Printing the names of the segments\n");
					LevelContainer.getNames();
					
				}
				if(command[1].equals("sFull")){
					System.out.println("Printing the segment types, their identifiers and the paths with cells belonging to them.\n");
					LevelContainer.getFull();
					
				}
				if(command[1].equals("segments")){
					System.out.println("Printing the segment types and their respective identifiers.\n");
					LevelContainer.getSegments();
					
				}
				if(command[1].equals("trains")){
					System.out.println("Printing trains\n");
					LevelContainer.getTrains();
					
				}
				if(command[1].equals("all")){
					LevelContainer.getAll();
					
				}
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
				System.out.println("Quitting...\n");
				Main.run = false;
			}

		}

	}
}
