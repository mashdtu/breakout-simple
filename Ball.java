// Purpose: This class is used to define the ball moving around the game colliding with other objects.

import java.awt.*;

public class Ball extends Object2D{
    // Velocity of the object in the x and y directions.
    public double vx, vy;

    // Decimal x and y values.
    public double realX, realY;

    public Ball(int radius, int x, int y, Color color, double vx, double vy) {
        // Initialize a ball using the given parameters, inhereting from the Object2D class.
        super(radius, radius, x, y, color);

        // Set ball velocity in the x and y direction.
        this.vx = vx;
        this.vy = vy;

        // Set the initial real coordinates using the x and y parameters.
        this.realX = x;
        this.realY = y;
    }

    @Override
    public void draw() {
        // Move the ball by adding the velocity to the x and y coordinates.
        this.realX += this.vx;
        this.realY += this.vy;

        // Change the displayed x and y coordinates to the real coordinates rounded to be integers.
        this.x = (int) this.realX;
        this.y = (int) this.realY;
        
        // Draw the ball at its new position.
        StdDraw.setPenColor(this.getColor());
        StdDraw.filledRectangle(this.x, this.y, this.getWidth() / 2, this.getHeight() / 2);
    }

    public boolean outOfBounds() {
        // Check if the ball is out of bounds.
        return this.y < 0;
    }

    public void bounceOffWalls() {
        // Bounce the ball off the walls by reversing the x or y velocity.
        // Check if the ball hits the left wall.
        if (this.realX <= this.getWidth() / 2 && this.vx < 0) {
            this.bounceX();
        }
        // Check if the ball hits the right wall.
        if (this.realX >= Game.width - this.getWidth() / 2) {
            this.bounceX();
        }
        // Check if the ball hits the top wall.
        if (this.realY >= Game.height - this.getHeight() / 2) {
            this.bounceY();
        }
    }

    public void bounceX() {
        // Bounce the ball in the x direction by reversing the x velocity.
        this.vx = -this.vx;
    }

    public void bounceY() {
        // Bounce the ball in the y direction by reversing the y velocity.
        this.vy = -this.vy;
    }
}


