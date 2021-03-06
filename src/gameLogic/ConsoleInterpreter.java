package gameLogic;
import java.util.List;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import main.Main;
import segments.*;
import cars.*;

/**
 * This class implements the console's behavior for the user to control the game
 * in one way or another.
 */
public abstract class ConsoleInterpreter {
	public static void Prepare() {
		LevelContainer.Load(new Level());
		LevelContainer.OpenWindow();
	}

	/**
	 * This method accepts the string input entered by the user, splits it using
	 * space as a separator and performs specific actions based on the command's
	 * content.
	 */
	public static void ConsoleLine(String input) throws Exception {
		String[] command = input.split(" ");
		if (command.length > 0) {
			if (command[0].equals("add") && command.length > 2) {
				Segment newSegment = LevelContainer.FindSegment(command[2]);
				if (newSegment == null) {
					if (command[1].equals("fork") && command.length == 3) {
						System.out.println("Adding a fork...");
						newSegment = new Fork(command[2]);
						LevelContainer.addSegment(newSegment);
					} else if (command[1].equals("station") && command.length > 3) {
						System.out.println("Adding a station...");
						try {
							List<Colors> colors = new ArrayList<>();
							for (int i = 3; i < command.length; i++) {
								colors.add(Colors.parse(command[i]));
							}
							newSegment = new Station(command[2], colors.toArray(new Colors[command.length - 3]));
							LevelContainer.addSegment(newSegment);
						} catch (IllegalArgumentException e) {
							System.out.println("The color was not specified correctly");
						}
					} else if (command[1].equals("straight")) {
						System.out.println("Adding a straight...");
						newSegment = new Straight(command[2]);
						LevelContainer.addSegment(newSegment);
					} else if (command[1].equals("turn")) {
						System.out.println("Adding a turn...");
						newSegment = new Turn(command[2]);
						LevelContainer.addSegment(newSegment);
					} else if (command[1].equals("entrance")) {
						System.out.println("Adding a tunnel entrance...");
						newSegment = new TunnelEntrance(command[2]);
						LevelContainer.addSegment(newSegment);
					} else if (command[1].equals("finalstation") && command.length > 3) {
						System.out.println("Adding a final station...");
						try {
							List<Colors> colors = new ArrayList<>();
							for (int i = 3; i < command.length; i++) {
								colors.add(Colors.parse(command[i]));
							}
							newSegment = new FinalStation(command[2], colors.toArray(new Colors[command.length - 3]));
							LevelContainer.addSegment(newSegment);
						} catch (IllegalArgumentException e) {
							System.out.println("The color was not specified correctly");
						}
					}
				} else {
					System.out.println("A segment with the same identifier already exists.\n");
				}
			}

			if (command[0].equals("select") && command.length > 1) {
				System.out.println("The select command is being processed");
				Segment presentSegment = LevelContainer.FindSegment(command[1]);
				if (presentSegment != null) {
					presentSegment.Select();
					System.out.println("Segment selected");
				} else {
					System.out.println("Selected segment was not found");
				}
			}

			if (command[0].equals("join") && command.length == 5) {
				System.out.println("The join command is being processed");
				try {
					LevelContainer.Join(command[1], Integer.parseInt(command[2]), command[3],
							Integer.parseInt(command[4]));
				} catch (NumberFormatException e) {
					System.out.println("Incorrect input");
				}
			}
			if (command[0].equals("connect") && command.length == 4) {
				System.out.println("The connect command is being processed");
				Segment entrance1 = LevelContainer.FindSegment(command[1]);
				Segment entrance2 = LevelContainer.FindSegment(command[2]);
				if (entrance1.getClass() == TunnelEntrance.class && entrance2.getClass() == TunnelEntrance.class) {
					try {
						if (!LevelContainer.IsTunnelPossibleBetween((TunnelEntrance) entrance1,
								(TunnelEntrance) entrance2) && Integer.parseInt(command[3]) > 0) {
							Tunnel tunnel = new Tunnel((TunnelEntrance) entrance1, (TunnelEntrance) entrance2,
									Integer.parseInt(command[3]));
							LevelContainer.addTunnel(tunnel);
							System.out.println("Entrances connected");
						}
					} catch (NumberFormatException e) {
					}
				} else {
					throw new InvalidParameterException("Incorrect segments");
				}

			}
			if (command[0].equals("place") && command.length > 3) {
				System.out.println("The place command is being processed");
				Segment segment = LevelContainer.FindSegment(command[1]);
				if(segment != null) {
					try {
						Path path = segment.GetPathEndingWith(Integer.parseInt(command[2]));
						if(path != null) {
							try {
								List<Colors> colors = new ArrayList<>();
								for (int i = 3; i < command.length; i++) {
									colors.add(Colors.parse(command[i]));
								}
								if(path.length() > 2 * colors.size() + 2) {
									Locomotive locomotive = new Locomotive(path.GetCellByInverseIndex(0), path.GetCellByInverseIndex(1));
									locomotive.SetPath(path);
									Car oldTemp = locomotive;
									for(int i = 0; i < colors.size(); i++) {
										Car loopTemp = new PassengerCar(path.GetCellByInverseIndex(2 + i * 2),path.GetCellByInverseIndex(3 + i * 2), colors.get(i));
										loopTemp.SetPath(path);
										oldTemp.attach(loopTemp);
										oldTemp = loopTemp;
									}
									LevelContainer.addLocomotive(locomotive);
								}else {
									System.out.println("The Specified color is not long enough");
								}
							} catch (IllegalArgumentException e) {
								System.out.println("The color was not specified correctly");
							}
						}else {
							System.out.println("Selected segment has no such path");
						}
					} catch (NumberFormatException e) {
						System.out.println("Incorrect input");
					}
				}else {
					System.out.println("Selected segment was not found");
				}
			}
			if(command[0].equals("move") && command.length == 4) {
				Segment segment = LevelContainer.FindSegment(command[1]);
				if(segment != null) {
					try {
						segment.setPosition(new vec2(
								Float.parseFloat(command[2]),
								Float.parseFloat(command[3])
								));
						System.out.println(segment.id + " moved to (" + command[2] + " , " + command[3] + ")");
					}catch( NumberFormatException e){
						System.out.println("Poorly specified position.");
					}
				}else {
					System.out.println("Selected segment was not found");
				}
			}
			if(command[0].equals("rotate") && command.length == 3) {
				Segment segment = LevelContainer.FindSegment(command[1]);
				if(segment != null) {
					try {
						segment.setRotation(Integer.parseInt(command[2]));
						System.out.println(segment.id + " rotated " + (Integer.parseInt(command[2]) * 90) + " degrees)");
					}catch( NumberFormatException e){
						System.out.println("Poorly specified angle.");
					}
				}else {
					System.out.println("Selected segment was not found");
				}
			}
			
			if (command[0].equals("start") && command.length == 1 ) {
				System.out.println("Starting the game");
				LevelContainer.Start();
			}

			if (command[0].equals("start") && command.length == 2 && command[1].equals("stop") ) {
				System.out.println("Starting the game and pauses it");
				LevelContainer.StartWithoutClock();
			}
			if (command[0].equals("stop") && command.length == 1) {

				System.out.println("//stopping the game");
				LevelContainer.Stop();

			}
			if (command[0].equals("load") && command.length > 1) {
				System.out.println("Loading...");
				if (command[1].equals("empty")) {
					LevelContainer.Load(new Level());
					System.out.println("Empty level loaded.");
				} else {
					LevelContainer.Load(command[1]);
				}

			}
			if (command[0].equals("save") && command.length > 1) {
				System.out.println("Saving...");
				LevelContainer.Save(command[1]);
			}
			if (command[0].equals("pause") && command.length == 1) {
				System.out.println("Pausing");
				LevelContainer.Pause();
			}
			if (command[0].equals("resume") && command.length == 1) {
				System.out.println("Resuming");
				LevelContainer.Resume();
			}
			if (command[0].equals("tick") && command.length == 1) {
				System.out.println("Tick");
				LevelContainer.Tick();
			}
			if (command[0].equals("tick") && command.length > 1) {
				try {
					if(Integer.parseInt(command[1]) > 0) {
						int n = Integer.parseInt(command[1]);
						System.out.println("Tick x" + n);
						for(int i = 0; i < n; i++) {
							LevelContainer.Tick();
						}
				}
				}catch(NumberFormatException exception ) {
					
				}
			}
			if (command[0].equals("step") && command.length > 1) {
				System.out.println("The step command is being processed");
				try {
					LevelContainer.Step(Integer.parseInt(command[1]));
				} catch (IllegalArgumentException e) {
					System.out.println("Incorrect input");
				}

			}
			if (command[0].equals("print") && command.length > 1) {

				if (command[1].equals("sType")) {
					System.out.println("Printing types of segments");
					LevelContainer.printTypes();

				}
				if (command[1].equals("sNames")) {
					System.out.println("Printing the names of the segments");
					LevelContainer.printSimpleNames();

				}
				if (command[1].equals("sFull")) {
					System.out.println(
							"Printing the segment types, their identifiers and the paths with cells belonging to them.");
					LevelContainer.printFull();

				}
				if (command[1].equals("segments")) {
					System.out.println("Printing the segment types and their respective identifiers.");
					LevelContainer.printSegments();

				}
				if (command[1].equals("trains")) {
					System.out.println("Printing trains");
					LevelContainer.printTrains();

				}
				if (command[1].equals("all")) {
					LevelContainer.printAll();

				}

			}
			if(command[0].equals("camera") && command.length > 2) {
				String action = "";
				if(command[1].equals("zoom") && command[2].equals("in")) {
					action = "in";
					System.out.println("Zooming in");
				}else if(command[1].equals("zoom") && command[2].equals("out")) {
					action = "out";
					System.out.println("Zooming out");
				}else if(command[1].equals("move") && command[2].equals("up")) {
					action = "up";
					System.out.println("Moving up");
				}else if(command[1].equals("move") && command[2].equals("down")) {
					action = "down";
					System.out.println("Moving down");
				}else if(command[1].equals("move") && command[2].equals("right")) {
					action = "right";
					System.out.println("Moving right");
				}else if(command[1].equals("move") && command[2].equals("left")) {
					action = "left";
					System.out.println("Moving left");
				}
				LevelContainer.CameraAction(action);
			}
			
			LevelContainer.repaint();
			if (command[0].equals("quit")) {
				System.out.println("Quitting...");
				Main.run = false;
			} else {
				System.out.println("Input the command:");
			}
		}
	}
}
