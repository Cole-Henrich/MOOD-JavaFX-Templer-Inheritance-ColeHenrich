package org.headroyce.bsea;

/**
 * Represents a miniature Ball with a random color.
 * It appears when a Lantern isRed()
 *
 * @see Mob isRed()
 * at that time, 20 new Sprinkles spawn.
 * @see GameLogic handle()
 */
public class Sprinkle extends Ball {
    /**
     * Creates a randomly colored Ball
     * at (x,y) (100, 100)
     * <p>
     * with:
     * radius of 5
     * <p>
     * If the player collides with a sprinkle,
     * it loses 2 lives
     * <p>
     * and the player gains no points when the sprinkle leaves.
     * Wicked!
     */
    public Sprinkle() {
        super(5, -2, 0);
        this.x = 100;
        this.y = 100;
        setColor(getRandomColor());
    }
}
