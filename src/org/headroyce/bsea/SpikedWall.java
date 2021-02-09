package org.headroyce.bsea;

import javafx.scene.paint.Color;

public class SpikedWall extends Obstacle {
    public SpikedWall(double x, double width) {
        this(width, 200, x, Color.AQUA, -1, 1);
    }

    public SpikedWall(double width, double height, double x, Color color, int damage, int destroyable) {
        setColor(color);
        setHeight(height);
        setWidth(width);
        if (x < 20 || x > 300) {
            this.x = x;
        }
    }
}
