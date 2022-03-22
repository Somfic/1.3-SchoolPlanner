package Data.Tiles;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TilesTileSetSource {
    private int firstgid;
    private String source;

    @JsonProperty("firstgid")
    public int getFirstgid() {
        return firstgid;
    }

    @JsonProperty("firstgid")
    public void setFirstgid(int value) {
        this.firstgid = value;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String value) {
        this.source = value;
    }
}
