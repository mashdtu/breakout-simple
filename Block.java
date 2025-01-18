// Purpose: This class is used to create a breakable/killable block in the game.

import java.awt.*;

public class Block extends Object2D {
    // Boolean for whether the block exists or not (true by default).
    private boolean exists;

    public Block (int width, int height, int x, int y, Color color) {
        // Initialize a block using the given parameters, inhereting from the Object2D class.
        super(width, height, x, y, color);
        this.exists = true;
    }

    public void destroy () {
        // Destroy the block by setting exists to false.
        this.exists = false;
    }

    public void revive () {
        // Revive the block by setting exists to true.
        this.exists = true;
    }

    public boolean exists () {
        // Return whether the block exists or not.
        return this.exists;
    }
}


