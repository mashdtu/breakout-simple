// Purpose: This class is used to define the platform the player controls.

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Platform extends Object2D {
    // Horizontal velocity of the platform
    private int velocity;

    // Weight of the platform, used for calculations of deacceleration and momentum.
    private final double weight = 1.8;

    // Maximum speed the platform can travel at, i.e. max number velocity can be.
    private final double topSpeed = 15.0;

    // Friction constant used for decceleration calculations.
    private final double friction = 0.4;

    public Platform(int width, int height, int x, int y, Color color) {
        // Initialize a platform using the given parameters, inhereting from the Object2D class.
        super(width, height, x, y, color);
        this.velocity = 0;
    }

    public void moveX (int dx) {
        // Increase the platform velocity by dx, unless the velocity would exceed the top speed.
        if (this.velocity + dx <= this.topSpeed && this.velocity + dx >= -this.topSpeed) {
            this.velocity += dx;
        } 
    }

    @Override
    public void draw() {
        // Check if the left and right arrow keys are pressed, calls the method to move 2 units in the x dimension.
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
            this.moveX(-2);
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
            this.moveX(2);
        }

        // Move the platform by adding the velocity to the x coordinate.
        this.x += this.velocity;

        // Deaccerelate by subtracting half of the difference between the weight and velocity from the platform.
        this.velocity -= this.weight / this.velocity * 0.5;

        // If the velocity goes below the friction constant, the velocity is set equal to zero.
        if (Math.abs(this.velocity) < this.friction) {
            this.velocity = 0;
        }

        // Check if the platform is colliding with the left wall.
        if (this.x - this.getWidth() / 2 < 0) {
            // If true, position the platform at the very edge of the game to disallow it moving past the wall.
            this.x = this.getWidth() / 2;

            // Reset the speed if still travelling towards the wall.
            this.velocity = this.velocity < 0 ? 0 : this.velocity;
        }

        // Check if the platform is colliding with the right wall.
        if (this.x + this.getWidth() / 2 > Game.width) {
            // If true, position the platform at the very edge of the game to disallow it moving past the wall.
            this.x = Game.width - this.getWidth() / 2;

            // Reset the speed if still travelling towards the wall.
            this.velocity = this.velocity > 0 ? 0 : this.velocity;
        }

        // Set the StdDraw pen color to the platform color.
        StdDraw.setPenColor(this.getColor());

        // Draw a rectangle using the platform position and size.
        StdDraw.filledRectangle(this.x, this.y, this.getWidth() / 2, this.getHeight() / 2);
    }

    public int getVelocity() {
        // Return the horizontal velocity of the platform.
        return this.velocity;
    }
    
    public double getTopSpeed() {
        // Return the top speed / maximum velocity of the platform.
        return this.topSpeed;
    }

    public double getWeight() {
        // Return the weight of the platform.
        return this.weight;
    }
}
