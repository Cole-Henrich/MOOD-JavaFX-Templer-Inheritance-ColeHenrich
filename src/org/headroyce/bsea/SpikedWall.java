package org.headroyce.bsea;

import javafx.scene.paint.Color;

public class SpikedWall extends Obstacle {
    public SpikedWall(double x, double width) {
        super(width, 200, Color.AQUA, x, -1, 1, 100);
    }

}
