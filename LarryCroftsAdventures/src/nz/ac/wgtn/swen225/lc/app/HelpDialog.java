package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;

/**
 * HelpDialog is a custom dialog that displays game rules and instructions for
 * the Larry Croft's Adventures game. It provides information about the game's
 * objective, rules, and how to play.
 * *  @author Matthew Kerr (300613741)
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
        String helpText = "Game Rules:\n" + "1. 'Reel it in' is a puzzle game where you control a boat.\n"
                + "2. The objective is to collect all fish in each level to get through the fishing pier.\n"
                + "3. You must also reach the whirpool to complete the level.\n"
                + "4. Avoid hazards such as seagulls and pirates.\n"
                + "5. Some levels have time limits, so be quick!\n\n" + "Instructions:\n"
                + "- Use the arrow keys or WASD to move the Boat around the maze.\n"
                + "- Collect fish to progress.\n"
                + "- Use keys to unlock doors.\n"
                + "- Plan your moves carefully to avoid getting trapped.\n"
                + "- Be aware of the level's hazards and monsters.\n" + "- Enjoy the challenge and have fun!";

        textArea.setText(helpText);

        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane); // Add the scrollable text area to the dialog's content pane.
    }
}
