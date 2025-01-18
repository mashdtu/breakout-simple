import java.awt.Color;

public class GameSetup {
    // Width and height of the game itself (not the GUI).
    private int width, height;

    // List of blocks in the game.
    private Block[] blocks;

    // Platform the player controls.
    private Platform platform;

    // Ball that moves around the screen.
    private Ball ball;

    public GameSetup (int noOfBlocksX, int noOfBlocksY) {
        // Set the width and height of each individual block.
        final int blockWidth = 30;
        final int blockHeight = 10;

        // Set the width and height of the platform.
        final int platformWidth = 60;
        final int platformHeight = 10;
        
        // Set the radius of the ball.
        final int ballRadius = 10;
        
        // Set the colors of the objects.
        final Color blockColor = StdDraw.BLACK;
        final Color platformColor = StdDraw.BLACK;
        final Color ballColor = StdDraw.BLACK;

        // Define the distance between each block.
        final int dy = 1;
        final int dx = 2;

        // Using the dimensions of the block field, the game matrix size is calculated.
        this.width = noOfBlocksX * blockWidth + noOfBlocksX * dx + 1;
        this.height = (noOfBlocksY * blockHeight + noOfBlocksY * dy + 1) * 2 + platformHeight * 2;

        // Initialize the list of blocks.
        this.blocks = new Block[noOfBlocksX * noOfBlocksY];

        // Define each block. For each block in the list:
        for (int i = 0; i < noOfBlocksX; i++) {
            for (int j = 0; j < noOfBlocksY; j++) {
                // Define the x coordinate
                int x = i * (blockWidth + dx) + blockWidth / 2 ;

                // Define the y coordinate
                int y = this.height - j * (blockHeight + dy) - blockHeight / 2 - 2 * dy;

                // Define the specific block in the list using the x and y values.
                this.blocks[i * noOfBlocksY + j] = new Block(blockWidth, blockHeight, x, y, blockColor);
            }
        }

        // Define the platform at the default position.
        this.platform = new Platform(platformWidth, platformHeight, this.width / 2, platformHeight / 2 + dy, platformColor);

        // Define the ball at the default position.
        this.ball = new Ball(ballRadius, this.width / 2, platformHeight / 2 + dy + ballRadius, ballColor, 0, -2);
    }

    public int getWidth () {
        // Return the width of the game matrix.
        return this.width;
    }

    public int getHeight () {
        // Return the height of the game matrix.
        return this.height;
    }

    public Block[] getBlocks() {
        // Return the list of blocks.
        return this.blocks;
    }

    public Platform getPlatform() {
        // Return the platform object.
        return this.platform;
    }

    public Ball getBall() {
        // Return the ball object.
        return this.ball;
    }
}
