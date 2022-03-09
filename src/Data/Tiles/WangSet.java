package Data.Tiles;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WangSet {
    private List<Color> colors;
    private String name;
    private long tile;
    private String type;
    private List<WangTile> wangTiles;

    @JsonProperty("colors")
    public List<Color> getColors() {
        return colors;
    }

    @JsonProperty("colors")
    public void setColors(List<Color> value) {
        this.colors = value;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.name = value;
    }

    @JsonProperty("tile")
    public long getTile() {
        return tile;
    }

    @JsonProperty("tile")
    public void setTile(long value) {
        this.tile = value;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String value) {
        this.type = value;
    }

    @JsonProperty("wangtiles")
    public List<WangTile> getWangTiles() {
        return wangTiles;
    }

    @JsonProperty("wangtiles")
    public void setWangTiles(List<WangTile> value) {
        this.wangTiles = value;
    }
}
