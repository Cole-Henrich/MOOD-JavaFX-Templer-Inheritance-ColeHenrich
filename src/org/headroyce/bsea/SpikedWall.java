package org.headroyce.bsea;

import javafx.scene.paint.Color;

public class SpikedWall extends Obstacle {
    public SpikedWall(double x, double width) {
        super(width, 200, Color.AQUA, -1, false, 100);
        this.x = x;
    }

}
