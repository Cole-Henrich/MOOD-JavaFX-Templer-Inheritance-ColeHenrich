package org.headroyce.bsea;

import javafx.scene.paint.Color;

public class SpikedWall extends Obstacle{

    public SpikedWall(double x){
        this(20, 200, x, Color.AQUA);
    }
    public SpikedWall(double width, double height, double x, Color color){
        setColor(color);
        setHeight(height);
        setWidth(width);
        if (x < 20 || x > 300) {
            this.x = x;
        }
    }
}
