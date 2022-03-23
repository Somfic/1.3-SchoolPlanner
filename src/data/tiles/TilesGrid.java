package data.tiles;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TilesGrid {
    private int height;
    private String orientation;
    private int width;

    @JsonProperty("height")
    public int getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(int value) {
        this.height = value;
    }

    @JsonProperty("orientation")
    public String getOrientation() {
        return orientation;
    }

    @JsonProperty("orientation")
    public void setOrientation(String value) {
        this.orientation = value;
    }

    @JsonProperty("width")
    public int getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(int value) {
        this.width = value;
    }
}
