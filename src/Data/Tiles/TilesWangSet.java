package Data.Tiles;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class TilesWangSet {
    private List<TilesColor> colors = new ArrayList<>();
    private String name;
    private int tile;
    private String type;
    private List<TilesWangTile> wangTiles = new ArrayList<>();

    @JsonProperty("colors")
    public List<TilesColor> getColors() {
        return colors;
    }

    @JsonProperty("colors")
    public void setColors(List<TilesColor> value) {
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
    public int getTile() {
        return tile;
    }

    @JsonProperty("tile")
    public void setTile(int value) {
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
    public List<TilesWangTile> getWangTiles() {
        return wangTiles;
    }

    @JsonProperty("wangtiles")
    public void setWangTiles(List<TilesWangTile> value) {
        this.wangTiles = value;
    }
}
