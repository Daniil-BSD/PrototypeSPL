package gameLogic;

import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class InputInterpriter implements ActionListener, KeyListener, MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_UP:
			LevelContainer.CameraAction("up");
			break;
		case KeyEvent.VK_DOWN:
			LevelContainer.CameraAction("down");
			break;
		case KeyEvent.VK_LEFT:
			LevelContainer.CameraAction("left");
			break;
		case KeyEvent.VK_RIGHT:
			LevelContainer.CameraAction("right");
			break;
		case KeyEvent.VK_EQUALS:
			LevelContainer.CameraAction("in");
			break;
		case KeyEvent.VK_MINUS:
			LevelContainer.CameraAction("out");
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "save":
			save();
			break;
		case "load":
			load();
			break;
		case "start":
			LevelContainer.Start();
			break;
		case "stop":
			LevelContainer.Defeat();
			break;
		case "pause":
			LevelContainer.Pause();
			break;
		case "resume":
			LevelContainer.Resume();
			break;
		}
	}


	public void load() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Save File",LevelContainer.FILE_EXTENSION);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(fileNameExtensionFilter);
		chooser.setAcceptAllFileFilterUsed(false);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			if (file.isFile() && file.getName().endsWith("." + LevelContainer.FILE_EXTENSION)) {
				LevelContainer.Load(file);
			} else {
				JOptionPane jOptionPane = new JOptionPane("Failed to load the file", JOptionPane.ERROR_MESSAGE);
				JDialog jDialog = jOptionPane.createDialog("Error");
				jDialog.setVisible(true);
			}
		}
	}

	public void save() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Save File",
				LevelContainer.FILE_EXTENSION);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(fileNameExtensionFilter);
		chooser.setAcceptAllFileFilterUsed(false);
		int returnVal = chooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			if (!file.getName().endsWith("." + LevelContainer.FILE_EXTENSION)) {
				file = new File(file.getPath() + "." + LevelContainer.FILE_EXTENSION);
			}
			if ((file.exists() && file.isFile() && file.getName().endsWith(LevelContainer.FILE_EXTENSION))
					|| !file.exists()) {
				LevelContainer.Save(file);
			} else {
			}
		}
	}

}
