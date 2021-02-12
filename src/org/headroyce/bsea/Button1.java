package org.headroyce.bsea;

import javafx.scene.control.Button;


/**
 * A Button that a few basic methods that are easier to manipulate than in the normal Button class.
 */
public class Button1 extends Button {
    public Button1(String text) {
        super(text);
        setFontSize(40);
    }

    public Button1() {
        super();
    }

    /**
     * Sets the size of text on a button.
     */
    public void setFontSize(int fontSize) {
        String fontSet = "-fx-font-size:" + fontSize;
        this.setStyle(fontSet);
    }

    /**
     * AutoSizes the font to 1/25 of the current screen size.
     *
     * @param canvasWidth the width of the screen
     */
    public void autoSize(double canvasWidth) {
        int autoFontSize = (int) (canvasWidth / 25);
        this.setFontSize(autoFontSize);
    }
}
