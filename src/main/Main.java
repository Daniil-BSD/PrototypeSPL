package main;

import java.io.*;

import gameLogic.ConsoleInterpreter;

/**
 * 
 */

/**
 * @author Daniil Besedin
 * The main class that executes the game.
 */
public class Main {

	/**
	 * Attribute that tells us if the game is running.
	 */
	
	public static volatile boolean run = true;
	
	 /**
     * This method is where the game runs.
     */

	public static void main(String[] args) {
		run = true;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		System.out.println("Input the command:");
		ConsoleInterpreter.Prepare();
		while (run) {
			try {
				ConsoleInterpreter.ConsoleLine( br.readLine());
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		System.out.println("Application Terminated.");
		//test
		
		//Test -B
	}

}
