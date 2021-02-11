package org.headroyce.bsea;

import javafx.scene.paint.Color;

public class LifeGiver extends Ball {
    public LifeGiver(){
        this(10, Color.DARKGREEN, 1);
    }
    public LifeGiver(double radius, Color COLOR, int damage){
        setColor(COLOR);
        setRadius(this.getRadius());
        setDamage(damage);
    }
}
