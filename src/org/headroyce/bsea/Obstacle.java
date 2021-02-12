package org.headroyce.bsea;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents an rectangular obstacle
 */

public class Obstacle extends Mob {
   public GameLogic logic = new GameLogic(getWidth(), getHeight());
    /**
     * Creates a destroyable PURPLE obstacle with a width of ten and height of 40, a damage of -1, and 100 offPoints.
     */
    public Obstacle(double canvasWidth) {
        this((int) (Math.random() * canvasWidth), 10, 40, Color.PURPLE, -1, true, 100);
    }

    /**
     * Creates an obstacle with a custom width and height (in pixels)
     *
     * @param width  the width (in pixels) to set of the obstacle; Non-positives are reset to ten
     * @param height the height (in pixels) to set of the obstacle; Non-positives are reset to ten
     */
    public Obstacle(double width, double height) {
        if (width <= 0) {
            width = 10;
        }
        if (height <= 0) {
            height = 10;
        }

        setWidth(width);
        setHeight(height);
    }

    /**
     * @param x     the x coordinate.
     *              While it *can* be any value, it is intended to be either 1 or the width of the screen,
     *              which is handled in GameLogic.
     * @param width the width of the spikedWall.
     *              GameLogic prevents it from being less than 5px and more than 40% of the width of the screen.
     */

    public Obstacle(int x, double width, double height, Color color, int damage, boolean destroyable, int offPoints) {
        setColor(color);
        setDamage(damage);
        setWidth(width);
        setHeight(height);
        setOffPoints(offPoints);
        setDestroyable(destroyable);
        this.x = x;
        setVelocityBoundX(0, 0);
        setVelocityBoundY(0, 5);
    }

    /**
     * Check to see if the obstacle overlaps with a ball (circular)
     *
     * @param other the ball to check intersection with
     * @return true is this object intersects with other, false otherwise
     */
    public boolean intersects(Ball other) {
        Obstacle o = new Obstacle(other.getRadius() * 2, other.getRadius() * 2);
        o.x = other.x - other.getRadius();
        o.y = other.y - other.getRadius();

        return this.intersects(o);
    }

    /**
     * Renders the obstacle.
     *
     * @param canvas the canvas to render on
     */
    public void render(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(this.getColor());
        gc.fillRect(x, y, getWidth(), getHeight());
    }
}
