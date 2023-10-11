package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;

/**
 * HelpDialog is a custom dialog that displays game rules and instructions for
 * the Larry Croft's Adventures game. It provides information about the game's
 * objective, rules, and how to play.
 *  *  @author Matthew Kerr (300613741)
 */
public class HelpDialog extends JDialog {

	/**
	 * Constructs a HelpDialog.
	 *
	 * @param parent The parent JFrame to which this dialog is attached.
	 */
	public HelpDialog(JFrame parent) {
		super(parent, "Help - Game Rules", true); // Set dialog title and make it modal.
		setSize(400, 300); // Set the size of the dialog.
		setLocationRelativeTo(parent); // Center the dialog relative to the parent frame.
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Dispose the dialog when it's closed.

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false); // Make the text area read-only.
		textArea.setWrapStyleWord(true); // Enable word wrapping.
		textArea.setLineWrap(true); // Enable line wrapping.
		textArea.setMargin(new Insets(10, 10, 10, 10)); // Set text area margins for spacing.

		// Add game rules and instructions to the text area
		String helpText = "Game Rules:\n" + "1. Larry Crofts Adventures is a puzzle game where you control Chap.\n"
				+ "2. The objective is to collect all keys in each level to open the exit door.\n"
				+ "3. You must also reach the exit door to complete the level.\n"
				+ "4. Avoid hazards such as water, fire, and monsters.\n"
				+ "5. Use tools and power-ups to overcome obstacles.\n"
				+ "6. Some levels have time limits, so be quick!\n\n" + "Instructions:\n"
				+ "- Use the arrow keys or WASD to move Chip around the maze.\n"
				+ "- Push blocks to create paths and solve puzzles.\n" + "- Collect computer chips to progress.\n"
				+ "- Use keys to unlock doors.\n"
				+ "- Look for tools like boots and flippers to access special areas.\n"
				+ "- Plan your moves carefully to avoid getting trapped.\n"
				+ "- Be aware of the level's hazards and monsters.\n" + "- Enjoy the challenge and have fun!";

		textArea.setText(helpText);

		JScrollPane scrollPane = new JScrollPane(textArea);
		getContentPane().add(scrollPane); // Add the scrollable text area to the dialog's content pane.
	}
}
