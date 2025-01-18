// Purpose: This class handles all the different components inside the game and contains the game-loop.

public class Game {
    // List of all the blocks in the game.
    private final Block[] blocks;

    // Instance of the platform the player moves.
    private final Platform platform;

    // Instance of the ball.
    private final Ball ball;

    // Boolean to determine if the game has ended.
    private boolean gameOver;

    // Integers for the width and height inside the game.
    public static int width, height;

    // Integers for the width and height of the actual window the game runs inside.
    public int GUIwidth, GUIheight;

    // Dummy variable used to determine whether or not all the blocks are destroyed.
    private int deadBlocks;

    public Game (int guiwidth, int guiheight, int blockFieldWidth, int blockFieldHeight) {
        // Set the gameover bool to false.
        this.gameOver = false;

        // Set the GUI window width and height in pixels (800x600).
        this.GUIwidth = guiwidth;
        this.GUIheight = guiheight;
        
        // Create a instance of the gameSetup class to initialize the blocks, platform and ball before the game starts.
        GameSetup gameSetup = new GameSetup(blockFieldWidth, blockFieldHeight);
        
        // Fetch the game matrix dimensions from the gamesetup.
        Game.width = gameSetup.getWidth();
        Game.height = gameSetup.getHeight();

        // Fetch the blocks, platform and ball instances from the gameSetup class.
        this.blocks = gameSetup.getBlocks();
        this.platform = gameSetup.getPlatform();
        this.ball = gameSetup.getBall();

        // Set the StdDraw canvas size and x and y scales.
        StdDraw.setCanvasSize(this.GUIwidth, this.GUIheight);
        StdDraw.setXscale(0, Game.width);
        StdDraw.setYscale(0, Game.height);
    }


    public void run () {
        // Check for collisions with the ball and the walls and bounce the ball.
        this.ball.bounceOffWalls();
        
        // Check for collisions between the ball and platform, handle any possible collision.
        if (CollisionHandler.checkCollision(this.ball, this.platform)) {
            CollisionHandler.handleCollision(this.ball, this.platform);
        }

        // Check for collisions between the ball and any block, handle any possible collision(s).
        boolean hasCollided = false;
        for (Block block : this.blocks) {
            if (CollisionHandler.checkCollision(this.ball, block) && block.exists() && !hasCollided) {
                CollisionHandler.handleCollision(this.ball, block);
                hasCollided = true;
            }
        }
        
        // Start drawing on the next frame
        StdDraw.show(0);

        // Clear the StdDraw canvas.
        StdDraw.clear(StdDraw.WHITE);

        
        // Draw the ball and platform.
        this.ball.draw();
        this.platform.draw();

        // Draw every existing/alive block (i.e. any block that has not been hit).
        for (Block block : this.blocks) {
            if (block.exists()) {
                block.draw();
            }
        }
        
        // Check if the ball is out of bounds and end the game if it is.
        this.gameOver = this.ball.outOfBounds() ? true : this.gameOver;

        // Count the number of blocks that have been destroyed, i.e. the number of dead blocks.
        this.deadBlocks = 0;
        for (Block block : this.blocks) {
            if (!block.exists()) {
                this.deadBlocks += 1;
            }
        }
        // If the number of dead blocks is greater than or equal to the number of total blocks, i.e. if all the blocks are dead, end the game.
        this.gameOver = this.deadBlocks >= this.blocks.length ? true : this.gameOver;

        // To keep a specific framerate, a short delay is added before the next frame starts.
        try {
            Thread.sleep(16); 
        } catch(InterruptedException e) {
            // Do nothing.
        }
        
        // Switch frame to the new one.
        StdDraw.show(0);
        
        // If the game is not over, the game-loop is repeated.
        if (!this.gameOver) {
            run();
        }
    }

    public void preview () {
        // Start drawing on the frame
        StdDraw.show(0);

        // Clear the StdDraw canvas.
        StdDraw.clear(StdDraw.WHITE);
        
        // Draw the ball and platform.
        this.ball.draw();
        this.platform.draw();

        // Draw every existing/alive block (i.e. any block that has not been hit).
        for (Block block : this.blocks) {
            if (block.exists()) {
                block.draw();
            }
        }
        
        // Show the frame.
        StdDraw.show(0);
    }
}

