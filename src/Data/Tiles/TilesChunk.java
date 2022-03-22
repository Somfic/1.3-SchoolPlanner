package Data.Tiles;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TilesChunk {
    private String data;
    private int height;
    private int width;
    private int x;
    private int y;

    @JsonProperty("data")
    public String getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(String value) {
        this.data = value;
    }

    @JsonProperty("height")
    public int getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(int value) {
        this.height = value;
    }

    @JsonProperty("width")
    public int getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(int value) {
        this.width = value;
    }

    @JsonProperty("x")
    public int getX() {
        return x;
    }

    @JsonProperty("x")
    public void setX(int value) {
        this.x = value;
    }

    @JsonProperty("y")
    public int getY() {
        return y;
    }

    @JsonProperty("y")
    public void setY(int value) {
        this.y = value;
    }
}