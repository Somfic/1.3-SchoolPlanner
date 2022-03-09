package Data.Tiles;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class WangTile {
    private long tileId;
    private List<Long> wangId = new ArrayList<>();

    @JsonProperty("tileid")
    public long getTileId() {
        return tileId;
    }

    @JsonProperty("tileid")
    public void setTileId(long value) {
        this.tileId = value;
    }

    @JsonProperty("wangid")
    public List<Long> getWangId() {
        return wangId;
    }

    @JsonProperty("wangid")
    public void setWangId(List<Long> value) {
        this.wangId = value;
    }
}
