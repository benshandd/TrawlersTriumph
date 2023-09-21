package nz.ac.wgtn.swen225.lc.app;
import javax.swing.*;
import java.awt.*;

public class HelpDialog extends JDialog {

    public HelpDialog(JFrame parent) {
        super(parent, "Help - Game Rules", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setMargin(new Insets(10, 10, 10, 10));

        // Add game rules and instructions to the text area
        String helpText = "Game Rules:\n" +
                "1. Chip's Challenge is a puzzle game where you control Chip McCallahan.\n" +
                "2. The objective is to collect all computer chips in each level to open the exit door.\n" +
                "3. You must also reach the exit door to complete the level.\n" +
                "4. Avoid hazards such as water, fire, and monsters.\n" +
                "5. Use tools and power-ups to overcome obstacles.\n" +
                "6. Some levels have time limits, so be quick!\n\n" +
                "Instructions:\n" +
                "- Use the arrow keys or WASD to move Chip around the maze.\n" +
                "- Push blocks to create paths and solve puzzles.\n" +
                "- Collect computer chips to progress.\n" +
                "- Use keys to unlock doors.\n" +
                "- Look for tools like boots and flippers to access special areas.\n" +
                "- Plan your moves carefully to avoid getting trapped.\n" +
                "- Be aware of the level's hazards and monsters.\n" +
                "- Enjoy the challenge and have fun!";


        textArea.setText(helpText);

        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane);
    }
}
