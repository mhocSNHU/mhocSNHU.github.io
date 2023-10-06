/*****************************************************************
@author Matthew Hocking
@file ChessGUI.java
@purpose A basic class used to initialize the primary GUI from which
the user interacts with the connected model
@version Fall 2023
@institution SNHU / GVSU
*****************************************************************/

/** Used to create a dimension variable to set the initial window size */
import java.awt.Dimension;

	/** Used to generate the user interface that the player interacts with */
import javax.swing.JFrame; 

public class ChessGUI {

    public static void main(String[] args) {

        // Setup default values
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the panel
        ChessPanel panel = new ChessPanel();
        frame.getContentPane().add(panel);

        // Set starting size and resizability components
        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(1100, 737));
        frame.pack();
        frame.setVisible(true);
    }
}
