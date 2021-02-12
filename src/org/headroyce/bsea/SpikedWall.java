package org.headroyce.bsea;

import javafx.scene.paint.Color;

/**
 * Represents a large, indestructible Obstacle
 * that randomly moves adjacent to one of the left or right walls;
 * Width must be randomly chosen between 5px and 40% of the screen.
 */
public class SpikedWall extends Obstacle {

    /**
     * Creates an SpikedWall with a random x and width between 5 pixels and 40% of the screen,
     * a height of 200 pixels, of Color.AQUA, and with 100 offPoints,
     * meaning the player gets 100 points when the SpikedWall leaves the screen .
     */

    public SpikedWall(int x, double canvasWidth) {
        super(x, (Math.random() * ((canvasWidth * 0.4) - 5) + 5)/*thank you rosses*/, 200, Color.AQUA, -1, false, 100);
    }
}
