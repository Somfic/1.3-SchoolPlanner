package Data.Tiles;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TilesTileOffset {
    private int x;
    private int y;

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
