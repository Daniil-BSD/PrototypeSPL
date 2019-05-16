package userInterface;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

//This class is implements the main frame of the program where 
//the drawing will take place. Also, some configuration elements 
//will be present on the frame. Attributes and methods are inherited
//from the parent class. The LevelContainer class will also have 
//the MainFrame-type attribute for it to interact with the frame.

public class MainFrame {
	/**
	 * This attribute stores the JFrame object 
	 * as the base to draw objects on it.
	 */
	private JFrame frame;
	/**
	 * This attribute is needed for the configuration elements.
	 */
	private JMenuBar menuBar;
	/**
	 * This menu will contain options tied to the Game.
	 */
	private JMenu mnGame;
	/**
	 * This menu will contain the file options like save, load.
	 */
	private JMenu mnFile;
	/**
	 * This array contains menu elements.
	 */
	private JMenuItem[] menuItems;
	/**
	 * This attribute stores the panel on which the objects will 
	 * be grouped.
	 */
	private Panel CanvasPannel;
	/**
	 * This attribute will display the level.
	 */
	private GameDisplay gameDisplay;
	/**
	 * this method returns the current GameDisplay.
	 */
	public GameDisplay getGameDisplay() {
		return gameDisplay;
	}
	
	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * The initialization process will be handled by this method, that is, 
	 * initializes the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		menuItems = new JMenuItem[6];
		
		menuItems[0] = new JMenuItem("Start");
		menuItems[0].setActionCommand("start");
		mnGame.add(menuItems[0] );
		
		menuItems[1]  = new JMenuItem("Stop");
		menuItems[1].setActionCommand("stop");
		mnGame.add(menuItems[1] );
		
		menuItems[2] = new JMenuItem("Pause");
		menuItems[2].setActionCommand("pause");
		mnGame.add(menuItems[2]);
		
		menuItems[3]  = new JMenuItem("Resume");
		menuItems[3].setActionCommand("resume");
		mnGame.add(menuItems[3]);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		menuItems[4]  = new JMenuItem("Save");
		menuItems[4].setActionCommand("save");
		mnFile.add(menuItems[4] );
		
		menuItems[5]  = new JMenuItem("Load");
		menuItems[5].setActionCommand("load");
		mnFile.add(menuItems[5] );
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		CanvasPannel = new Panel();
		frame.getContentPane().add(CanvasPannel, BorderLayout.CENTER);
		CanvasPannel.setLayout(new BorderLayout(0, 0));
		
		gameDisplay = new GameDisplay();
		CanvasPannel.add(gameDisplay);
	}
	/**
	 * this method assigns an ActionEventListener to buttons so that
	 *  when the button is pressed, some process will start.
	 */
	public void addActionEventListenerToButtons(ActionListener actionListener) {
		for(JMenuItem mItem : menuItems) {
			mItem.addActionListener(actionListener);
		}
	}
	/**
	 * this method assigns a KeyListener to keys so that when a key 
	 * is pressed, some process will start.
	 */
	public void addKeyListener(KeyListener keyListener) {
		frame.addKeyListener(keyListener);
		gameDisplay.addKeyListener(keyListener);
	}

}
