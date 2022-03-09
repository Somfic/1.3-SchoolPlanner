package Data.Tiles;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Tileset {
    private long columns;
    private long firstGid;
    private Grid grid;
    private String image;
    private long imageHeight;
    private long imageWidth;
    private long margin;
    private String name;
    private long spacing;
    private long tileCount;
    private long tileHeight;
    private TileOffset tileoffset;
    private long tileWidth;
    private List<WangSet> wangSets = new ArrayList<>();

    @JsonProperty("columns")
    public long getColumns() {
        return columns;
    }

    @JsonProperty("columns")
    public void setColumns(long value) {
        this.columns = value;
    }

    @JsonProperty("firstgid")
    public long getFirstGid() {
        return firstGid;
    }

    @JsonProperty("firstgid")
    public void setFirstGid(long value) {
        this.firstGid = value;
    }

    @JsonProperty("grid")
    public Grid getGrid() {
        return grid;
    }

    @JsonProperty("grid")
    public void setGrid(Grid value) {
        this.grid = value;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String value) {
        this.image = value;
    }

    @JsonProperty("imageheight")
    public long getImageHeight() {
        return imageHeight;
    }

    @JsonProperty("imageheight")
    public void setImageHeight(long value) {
        this.imageHeight = value;
    }

    @JsonProperty("imagewidth")
    public long getImageWidth() {
        return imageWidth;
    }

    @JsonProperty("imagewidth")
    public void setImageWidth(long value) {
        this.imageWidth = value;
    }

    @JsonProperty("margin")
    public long getMargin() {
        return margin;
    }

    @JsonProperty("margin")
    public void setMargin(long value) {
        this.margin = value;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.name = value;
    }

    @JsonProperty("spacing")
    public long getSpacing() {
        return spacing;
    }

    @JsonProperty("spacing")
    public void setSpacing(long value) {
        this.spacing = value;
    }

    @JsonProperty("tilecount")
    public long getTileCount() {
        return tileCount;
    }

    @JsonProperty("tilecount")
    public void setTileCount(long value) {
        this.tileCount = value;
    }

    @JsonProperty("tileheight")
    public long getTileHeight() {
        return tileHeight;
    }

    @JsonProperty("tileheight")
    public void setTileHeight(long value) {
        this.tileHeight = value;
    }

    @JsonProperty("tileoffset")
    public TileOffset getTileoffset() {
        return tileoffset;
    }

    @JsonProperty("tileoffset")
    public void setTileoffset(TileOffset value) {
        this.tileoffset = value;
    }

    @JsonProperty("tilewidth")
    public long getTileWidth() {
        return tileWidth;
    }

    @JsonProperty("tilewidth")
    public void setTileWidth(long value) {
        this.tileWidth = value;
    }

    @JsonProperty("wangsets")
    public List<WangSet> getWangSets() {
        return wangSets;
    }

    @JsonProperty("wangsets")
    public void setWangSets(List<WangSet> value) {
        this.wangSets = value;
    }
}
