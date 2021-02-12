package org.headroyce.bsea;

import javafx.scene.control.Button;

/**
 * A Button which can utilize a quicker setSize() method.
 */
public class Button1 extends Button {

    public Button1(String reset) {
        super(reset);
    }

    public Button1() {
        super();
    }

    /**
     * Sets the acceptable range of widths and heights for the button.
     *
     * @param minWidth  of the button
     * @param minHeight of the button
     * @param maxWidth  of the button
     * @param maxHeight of the button
     *                  both minimums must be less than the maximums
     */
    public void setSize(double minWidth, double minHeight, double maxWidth, double maxHeight) {
        if (minWidth < maxWidth && minHeight < maxHeight) {
            this.setMinSize(minWidth, minHeight);
            this.setMaxSize(maxWidth, maxHeight);
        }
    }
}
