package org.headroyce.bsea;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Mob {
    char[] hexadecimal = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private boolean destroyable;

    private Color color;
    private double hp;
    private double damage;
    // [0] - lower bound
    // [1] - upper bound
    private double[] boundX = new double[2];
    private double width, height;

    public double x, y;         // Center point of the circle
    public double velX, velY;
    private double[] boundY = new double[2];
    private double offPoints;

    public Mob(int damage, int offPoints, boolean destroyable) {
        setDamage(damage);
        setOffPoints(offPoints);
        setDestroyable(destroyable);
    }

    public Mob() {
        this(-1, 100, true);
    }

    public Mob(int damage, int offPoints) {
        setColor(Color.BLACK);
        boundX = new double[2];
        boundY = new double[2];
        setDamage(damage);
        setOffPoints(offPoints);
    }

    /*
    Some green colors are
                          00FFC0 ---> 00FF00
                          00FF00 ---> A0FF00

     */
    private void makeGreen() {

    }


    public boolean setDamage(int damage, int min, int max) {
        boolean rtn = false;
        if (damage >= min && damage <= max) {
            this.damage = damage;
            rtn = true;
        }
        return rtn;
    }

    public boolean setDamage(int damage) {
        boolean rtn = false;
        if (setDamage(damage, -10, 10)) {
            this.damage = damage;
            rtn = true;
        }
        return rtn;
    }

    public double getOffPoints() {
        return this.offPoints;
    }

    public double getDamage() {
        return this.damage;
    }

    /**
     * Changes the velocity bounds in the x direction
     *
     * @param lower the lower limit
     * @param upper the upper limit
     * @return true if bounds have changed, false if lower > upper
     */
    public boolean setVelocityBoundX(double lower, double upper) {
        boolean rtn = true;
        if ((lower > upper)) {
            rtn = false;
        }
        if (rtn) {
            boundX[0] = lower;
            boundX[1] = upper;
        }
        return rtn;
    }
    /**
     * Changes the velocity bounds in the y direction
     * @param lower the lower limit
     * @param upper the upper limit
     * @return true if bounds have changed, false if lower > upper
     */
    public boolean setVelocityBoundY( double lower, double upper ) {
        boolean rtn = true;
        if (lower > upper) {
            rtn = false;
        }
        if (rtn) {
            boundY[0] = lower;
            boundY[1] = upper;
        }
        return rtn;
    }


    /**
     * Get the bounds on the velocity in the X direction
     *
     * @return a new array populated with the bounds of the Mob in the x direction
     */
    public double[] getVelocityBoundX() {
        double[] rtn = new double[2];

        rtn[0] = boundX[0];
        rtn[1] = boundX[1];
        return rtn;
    }

    /**
     * Get the bounds on the velocity in the Y direction
     *
     * @return a new array populated with the bounds of the Mob in the y direction
     */
    public double[] getVelocityBoundY() {
        double[] rtn = new double[2];

        rtn[0] = boundY[0];
        rtn[1] = boundY[1];
        return rtn;
    }

    /**
     * Sets the destructibility of the object.
     *
     * @param destroyable the new destroyable value.
     * @return true if the destroyable is 1 or 0; false otherwise.
     */
    public boolean setDestroyable(boolean destroyable) {
        this.destroyable = destroyable;
        return true;
    }

    /**
     * Get the destructibility of the Mob,
     * Whether it disappears on collision or not.
     */
    public boolean isDestroyable() {
        return this.destroyable;
    }

    /**
     * Get the color of the Mob
     *
     * @param c the new color of the Mob (cannot be null)
     * @return true if the color has changed, false otherwise
     */
    public boolean setColor(Color c) {
        if (c == null) {
            return false;
        }

        color = c;
        return true;

    }

    /**
     * Get the current color of the Mob
     *
     * @return the current color of the Mob
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Get the current hit point value of the ball
     * @return a non-negative value representing the hit points of the ball
     */
    public double getHP(){ return hp;
    }

    /**
     * Add to the current hit points of the ball.  Hit points cannot go below zero.
     *
     * @param deltaHP the value to add (or subtract) from the hitpoints of the ball
     */
    public void addHP(double deltaHP) {
        hp += deltaHP;
        if (hp < 0) {
            hp = 0;
        }
    }

    /**
     * Set the offPoints of this object.
     *
     * @param offPoints the proposed offPoints
     * @return true if the offPoints are set, false if not.
     */
    public boolean setOffPoints(double offPoints) {
        boolean rtn = false;

        if (offPoints > 0) {
            this.offPoints = offPoints;
            rtn = true;
        }
        return rtn;
    }

    /**
     * Set the width of this object.  An obstacle's width must be positive.
     *
     * @param w the new, positive, width of this object
     * @return true if the width is set, false if width is not changed
     */
    public boolean setWidth(double w) {
        boolean rtn = false;

        if (w > 0) {
            this.width = w;
            rtn = true;
        }

        return rtn;
    }

    /**
     * Set the height of this object.  An obstacle's height must be positive.
     *
     * @param h the new, positive, height of this object
     * @return true if the height is set, false if height is not changed
     */
    public boolean setHeight(double h) {
        boolean rtn = false;

        if (h > 0) {
            this.height = h;
            rtn = true;
        }

        return rtn;
    }

    /**
     * Get the current width of this object
     * @return a positive width
     */
    public double getWidth() { return this.width; }

    /**
     * Get the current height of this object
     * @return a positive height
     */
    public double getHeight(){ return this.height;}


    /**
     * Bounce the ball in the X direction
     */
    public void bounceX(){
        this.velX *= -1;
    }

    /**
     * Bounce the ball in the Y direction
     */
    public void bounceY() {
        this.velY *= -1;
    }

    /**
     * Move the ball along its trajectory vector
     */
    public void move(){
        double moveVelX = this.velX;
        double moveVelY = this.velY;

        double[] boundsX = getVelocityBoundX();
        double[] boundsY = getVelocityBoundY();

        // Clamp the x
        if( moveVelX < boundsX[0] ){
            moveVelX = boundsX[0];
        }
        else if( moveVelX > boundsX[1] ){
            moveVelX = boundsX[1];
        }

        // Clamp the y
        if( moveVelY < boundsY[0] ){
            moveVelY = boundsY[0];
        }
        else if( moveVelY > boundsY[1] ){
            moveVelY = boundsY[1];
        }

        this.x += moveVelX;
        this.y += moveVelY;
    }

    /**
     * Check to the see if a point in within the ball
     * @param point the 2D points to check
     * @return true if point is within this, false otherwise
     */
    public boolean contains(Point2D point){
        return false;
    }


    /**
     * Check to see if the obstacle overlaps with another obstacle
     * @param other the second Mob to check intersection with
     * @return true if this objects intersect, false otherwise
     */
    public boolean intersects(Mob other){
        if( this.x + this.getWidth() < other.x ){
            return false;
        }
        if (this.x > other.x + other.getWidth()) {
            return false;
        }
        if (this.y + this.getHeight() < other.y) {
            return false;
        }
        return !(this.y > other.y + other.getHeight());
    }

    public void render(Canvas canvas) {
    }

    public Color getRandomColor() {
        return new Color(Math.random(), Math.random(), Math.random(), Math.random());
    }

    public boolean isGreen(Color color) {
        double threshold = 0.64 * color.getGreen();
        System.out.println("GREEN!");
        return (color.getBlue() < threshold && color.getRed() < threshold);
    }

    public boolean isRed(Color color) {
        double threshold = 0.64 * color.getRed();
        System.out.println("RED!");
        return (color.getBlue() < threshold && color.getGreen() < threshold);
    }
}
