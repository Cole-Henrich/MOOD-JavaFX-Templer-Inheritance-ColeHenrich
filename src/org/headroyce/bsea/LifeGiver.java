package org.headroyce.bsea;

import javafx.scene.paint.Color;

public class LifeGiver extends Ball {
    public LifeGiver(){
        this(1);
    }
    public LifeGiver( double radius){
        setColor(Color.GREEN);
    }

}
