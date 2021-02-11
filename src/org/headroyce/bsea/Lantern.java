package org.headroyce.bsea;

import javafx.scene.paint.Color;

public class Lantern extends Ball {
    public Lantern() {
        super(30, -1, 100);
        setColor(getRandomColor());
    }

    String[] pinkHexes = {"#FFC0CB", "#FFB6C1", "#FF69B4", "#FF1493", "#DB7093", "#C71585", "#E6E6FA", "#D8BFD8", "#DDA0DD", "#DA70D6", "#EE82EE", "#FF00FF", "#FF00FF", "#BA55D3", "#9932CC", "#9400D3", "#8A2BE2", "#8B008B", "#800080", "#6A0DAD", "#9370DB", "#7B68EE", "#6A5ACD", "#483D8B", "#663399", "#4B0082"};
    String[] greenHexes = {"#ADFF2F", "#7FFF00", "#7CFC00", "#00FF00", "#32CD32", "#98FB98", "#90EE90", "#00FA9A", "#00FF7F", "#3CB371", "#2E8B57", "#228B22", "#008000", "#006400", "#9ACD32", "#6B8E23", "#556B2F", "#66CDAA", "#8FBC8F", "#20B2AA", "#008B8B", "#008080"};

    private boolean checkColors(Color color, String[] hexes) {
        boolean rtn = false;
        for (String hex : hexes) {
            Color c = Color.web(hex);
            if (c == color) {
                rtn = true;
            }
        }
        return rtn;
    }
    public boolean isSpawnColor(Color color) {
        return (checkColors(color, pinkHexes));
    }
    public boolean isHealthColor(Color color) {
        return checkColors(color, greenHexes);
    }
}

