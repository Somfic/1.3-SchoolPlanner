package Data.Tiles;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class TilesWangTile {
    private int tileId;
    private List<Integer> wangId = new ArrayList<>();

    @JsonProperty("tileid")
    public int getTileId() {
        return tileId;
    }

    @JsonProperty("tileid")
    public void setTileId(int value) {
        this.tileId = value;
    }

    @JsonProperty("wangid")
    public List<Integer> getWangId() {
        return wangId;
    }

    @JsonProperty("wangid")
    public void setWangId(List<Integer> value) {
        this.wangId = value;
    }
}
