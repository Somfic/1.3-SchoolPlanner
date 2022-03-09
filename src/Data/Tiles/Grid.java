package Data.Tiles;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Grid {
    private long height;
    private String orientation;
    private long width;

    @JsonProperty("height")
    public long getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(long value) {
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
    public long getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(long value) {
        this.width = value;
    }
}
