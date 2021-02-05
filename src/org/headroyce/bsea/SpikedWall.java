package org.headroyce.bsea;

import javafx.scene.paint.Color;

public class SpikedWall extends Obstacle{
    public SpikedWall(){
        this(10, 80, 0);
    }
    public SpikedWall(double width, double height, double x){
        setColor(Color.AQUA);
        setHeight(height);
        setWidth(width);
        if (x < 20 || x > 300) {
            this.x = x;
        }
    }
    public int damage(int hp) {
        return super.damage(hp, -1);
    }
}
