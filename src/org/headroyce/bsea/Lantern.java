package org.headroyce.bsea;

import javafx.scene.paint.Color;

public class Lantern extends Ball {
    public Lantern() {
        super(30, -1, 100);
        Color randomColor = getRandomColor();
        setColor(randomColor);
    }
}

