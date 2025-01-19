// Purpose: This class contains the main method and acts as the entry-point to the program.

import java.awt.event.KeyEvent;

public class Breakout {
    public static void main(String[] args) {
        int noOfBlocksX = 0, noOfBlocksY = 0;

        try {
            // Set a constant width to the first argument.
            noOfBlocksX = Integer.parseInt(args[0]);

            // Set a constant height to the first argument.
            noOfBlocksY = Integer.parseInt(args[1]);

        } catch (Exception e) {
            // If the arguments are not integers, the program throws an error:
            System.out.println("Error: Enter two integers as arguments.");
            System.exit(0);
        }

        // Set the GUI window dimensions.
        final int GUIwidth = 800;
        final int GUIheight = 600;

        // Initialize a new Game object g with the width and height.
        Game g = new Game(GUIwidth, GUIheight, noOfBlocksX, noOfBlocksY);

        // Draw the preview before the game starts.
        g.preview();

        // Await player input
        boolean gameON = false;
        while (!gameON) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT) || StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                gameON = true;
            }
        }

        // Run the game.
        g.run();
    }
}