// Purpose: Define the Object2D class, which represents any object within the game.

import java.awt.*;

public class Object2D {
    // Width and height of the object within the game space.
    private int width, height;

    // Position of the center point of the object.
    public int x, y;

    // Color of the object.
    private Color color;

    public Object2D(int width, int height, int x, int y, Color color) {
        // Initialize the object fields using the given parameters.
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void draw() {
        // Draw the object at its current position using its stored color.
        StdDraw.setPenColor(this.color);
        StdDraw.filledRectangle(this.x + 1, this.y + 1, (double) this.width / 2, (double) this.height / 2);
    }

    public int getY() {
        // Return the y coordinate of the object.
        return this.y;
    }

    public int getX() {
        // Return the x coordinate of the object.
        return this.x;
    }

    public void setX(int x) {
        // Set the x coordinate of the object.
        this.x = x;
    }

    public void setY(int y) {
        // Set the y coordinate of the object.
        this.y = y;
    }

    public int getWidth() {
        // Return the width of the block.
        return width;
    }

    public int getHeight() {
        // Return the height of the block.
        return height;
    }

    public Color getColor() {
        // Return the color of the block.
        return color;
    }
}
