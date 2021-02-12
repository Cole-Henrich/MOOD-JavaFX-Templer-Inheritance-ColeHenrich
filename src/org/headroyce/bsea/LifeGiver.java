package org.headroyce.bsea;

import javafx.scene.paint.Color;

/**
 * Represents a green, player-sized ball
 * which adds one life to the player when captured.
 */
public class LifeGiver extends Ball {
    /**
     * Creates a DARKGREEN LifeGiver with a radius of 10,
     * offPoints of 200
     * and a damage of +1.
     */
    public LifeGiver() {
        super(10, Color.DARKGREEN, 1, 200);
    }
}
