package userInterface;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Panel;
import java.awt.event.ActionListener;

public class MainFrame {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu mnGame;
	private JMenu mnFile;
	private JMenuItem[] menuItems;
	private Panel CanvasPannel;
	private GameDisplay gameDisplay;
	
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
	 * Initialize the contents of the frame.
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
	
	public void addActionEventListenerToButtons(ActionListener actionListener) {
		for(JMenuItem mItem : menuItems) {
			mItem.addActionListener(actionListener);
		}
	}

}
