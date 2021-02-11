package org.headroyce.bsea;

public class Sprinkle extends Ball {
    public Sprinkle() {
        super(5, 10, 0);
        this.x = 100;
        this.y = 100;
        setColor(getRandomColor());
    }
}
