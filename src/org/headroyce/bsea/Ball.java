package org.headroyce.bsea;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents an oval ball
 */
public class Ball extends Mob{
    private double radius;

    /**
     * Creates a ball with a radius of one
     */
    public Ball(){
        this(10, 200);
    }

    /**
     * Creates a ball with a custom radius (in pixels) and offPoints.
     *
     * @param radius    the radius (in pixels) to set of the ball; Non-positives are reset to one.
     * @param offPoints the amount of points the player will gain when this Ball leaves the screen.
     */
    public Ball(double radius, int offPoints) {
        this(radius, Color.BLACK, 0, offPoints);
        if (radius <= 0) {
            radius = 1;
        }
        setRadius(radius);
    }

    /**
     * Creates a ball with custom color. Calls the radius, damage, offPoints constructor.
     *
     * @param radius    the radius of the ball
     * @param COLOR     the color of the ball
     * @param damage    the amount of HP the player will lose when this Ball hits the player.
     * @param offPoints the number of points which the player will gain when this Ball leaves the screen.
     *                  By default this Ball is destroyable, meaning when it collides, it disappears.
     */
    public Ball(double radius, Color COLOR, int damage, int offPoints) {
        this(radius, damage, offPoints);
        setColor(COLOR);
    }

    /**
     * @param radius    the radius of the ball
     * @param damage    the amount of HP the player will lose when this Ball hits the player.
     * @param offPoints offPoints the number of points which the player will gain when this Ball leaves the screen.
     *                  By default this Ball is destroyable, meaning when it collides, it disappears.
     */
    public Ball(double radius, int damage, int offPoints) {
        if (radius <= 0) {
            radius = 1;
        }
        setDestroyable(true);
        setDamage(damage);
        setOffPoints(offPoints);
        this.radius = radius;
    }

    /**
     * Creates a Ball with 10 radius, red, -1 damage, and 200 offPoints. In other words, an enemy ball.
     *
     * @param damage the amount of HP the player will lose when this Ball hits the player.
     */
    public Ball(int damage) {
        this(10, Color.RED, -1, 200);
        setDamage(damage);
    }

    /**
     * Set the radius of this object.  A ball's radius must be positive.
     *
     * @param radius the new, positive, radius of this object
     * @return true if the radius is set, false if radius is not changed
     */
    public boolean setRadius(double radius){
        boolean rtn = false;

        if( radius > 0 ) {
            super.setHeight(radius*2);
            super.setWidth(radius*2);
            this.radius = radius;
            rtn = true;
        }

        return rtn;
    }

    /**
     * Set the width of this object.  A ball's width must be positive.
     * @param w the new, positive, width of this object
     * @return true if the width is set, false if width is not changed
     */
    public boolean setWidth(double w){
        boolean rtn = false;

        if( w > 0 ) {
            super.setWidth(w);
            this.radius = w/2;
            rtn = true;
        }

        return rtn;
    }

    /**
     * Set the height of this object.  An ball's height must be positive.
     * @param h the new, positive, height of this object
     * @return true if the height is set, false if height is not changed
     */
    public boolean setHeight(double h){
        boolean rtn = false;

        if( h > 0 ) {
            super.setHeight(h);
            this.radius = h/2;
            rtn = true;
        }

        return rtn;
    }

    /**
     * Get the current radius of this object
     * @return a positive radius
     */
    public double getRadius(){
        return this.radius;
    }

    /**
     * Check to see if two balls overlap each other
     *
     * @param other the other ball
     * @return true is this object intersects with other, false otherwise
     */
    public boolean intersects(Ball other) {
        double xd = (other.x - this.x);
        xd *= xd;

        double yd = (other.y - this.y);
        yd *= yd;

        double rad = (other.radius + this.radius);
        rad *= rad;                  // (r1 + r2)^2

        double distance = xd + yd;  // x^2+y^2
        return (distance < rad);
    }

    /**
     * Renders the Ball.
     *
     * @param canvas the canvas of the Ball to render on.
     */
    public void render(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(this.getColor());
        gc.fillOval(x, y, 2 * radius, 2 * radius);
    }
}
