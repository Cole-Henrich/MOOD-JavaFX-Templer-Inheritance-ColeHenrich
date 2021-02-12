package org.headroyce.bsea;

import javafx.scene.paint.Color;

/**
 * A Lantern is a large Ball with a random color.
 */
public class Lantern extends Ball {
    /**
     * Creates a lantern with a radius of 30, damage of -1, and offPoints of 100.
     * Sets the color to a random color.
     * Damage of -1 means that when Lantern collides with player:
     * player.addHP(Mob.getDamage())
     * So the player loses 1 life
     * <p>
     * offPoints of 100 means that the player gains 100 points when a Lantern leaves the screen.
     */
    public Lantern() {
        super(30, -1, 100);
        Color randomColor = getRandomColor();
        setColor(randomColor);
    }
}

