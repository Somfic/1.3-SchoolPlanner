package Data.Tiles;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TileOffset {
    private long x;
    private long y;

    @JsonProperty("x")
    public long getX() {
        return x;
    }

    @JsonProperty("x")
    public void setX(long value) {
        this.x = value;
    }

    @JsonProperty("y")
    public long getY() {
        return y;
    }

    @JsonProperty("y")
    public void setY(long value) {
        this.y = value;
    }
}
