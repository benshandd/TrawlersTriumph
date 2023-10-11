package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class KeyBindingsPanel extends JPanel {
	public KeyBindingsPanel() {
		setLayout(new GridLayout(8, 1, 10, 10));
		setBorder(new EmptyBorder(10, 10, 10, 10));

		addKeyBinding("CTRL-X", "Exit (lose progress)");
		addKeyBinding("CTRL-S", "Save and Exit");
		addKeyBinding("CTRL-R", "Resume a saved game");
		addKeyBinding("CTRL-1", "Start a new game at level 1");
		addKeyBinding("CTRL-2", "Start a new game at level 2");
		addKeyBinding("SPACE", "Pause the game");
		addKeyBinding("ESC", "Resume the game from pause");
		addKeyBinding("Arrow Keys", "Move the character");
	}

	private void addKeyBinding(String keyCombination, String description) {
		JLabel label = new JLabel(keyCombination + " - " + description);
		label.setFont(new Font("Arial", Font.PLAIN, 14));
		add(label);
	}
}
