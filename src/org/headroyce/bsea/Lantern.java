package org.headroyce.bsea;

import javafx.scene.paint.Color;

public class Lantern extends Ball {
    public Lantern(Color color) {
        super(30, color, -1, 100);
    }

    /**
     * Hecka more efficient than generating 16^6 Strings and picking one at random.
     * Compare: 16^6 iterations vs. 6 iterations
     * It's not even funny how much more efficient this is.
     * Brain just dropped a big realization about how dumb the old way was.
     * Here's how: it picks a char at random from the valid hexchars 0-9, A-F,
     * and appends those to a StringBuilder. It then returns Color.web(String.valueOf(hexcode));
     *
     * @return any random color.
     * @🤯 woah.
     */
    private javafx.scene.paint.Color getRandomColor_MoreEfficiently() {
        char[] hexadecimal = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuilder hexcode = new StringBuilder();
        hexcode.append("#");
        for (int i = 0; i < 6; i++) {
            int random = (int) (Math.random() * hexadecimal.length);
            hexcode.append(hexadecimal[random]);
        }
        return javafx.scene.paint.Color.web(String.valueOf(hexcode));
    }
}
