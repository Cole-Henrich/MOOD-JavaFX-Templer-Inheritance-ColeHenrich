package org.headroyce.bsea;

import javafx.scene.paint.Color;

/**
 * Represents a large, indestructible Obstacle
 * that randomly moves adjacent to one of the left or right walls;
 * Width must be randomly chosen between 5px and 40% of the screen.
 */
public class SpikedWall extends Obstacle {
    public SpikedWall(double canvasWidth) {
        double width = (Math.random() * ((canvasWidth * 0.4) - 5) + 5); //thank you rosses
        int spikeX = 1;
        if (Math.random() >= 0.5) {
            spikeX = (int) canvasWidth;
        }
        this(canvas)

    }

    /**
     * Creates an SpikedWall with a random x and width between 5 pixels and 40% of the screen,
     * a height of 200 pixels, of Color.AQUA, and with 100 offPoints,
     * meaning the player gets 100 points when the SpikedWall leaves the screen .
     */

    public SpikedWall(double canvasWidth, int spikeX, double width) {
        super(spikeX, width, 200, Color.AQUA, -1, false, 100);
    }
}
