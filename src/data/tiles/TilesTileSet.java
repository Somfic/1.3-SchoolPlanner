package data.tiles;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class TilesTileSet {
    private int columns;
    private int firstGid;
    private TilesGrid grid;
    private String image;
    private int imageHeight;
    private int imageWidth;
    private int margin;
    private String name;
    private int spacing;
    private int tileCount;
    private int tileHeight;
    private TilesTileOffset tileoffset;
    private int tileWidth;
    private List<TilesWangSet> wangSets = new ArrayList<>();

    @JsonProperty("columns")
    public int getColumns() {
        return columns;
    }

    @JsonProperty("columns")
    public void setColumns(int value) {
        this.columns = value;
    }

    @JsonProperty("firstgid")
    public int getFirstGid() {
        return firstGid;
    }

    @JsonProperty("firstgid")
    public void setFirstGid(int value) {
        this.firstGid = value;
    }

    @JsonProperty("grid")
    public TilesGrid getGrid() {
        return grid;
    }

    @JsonProperty("grid")
    public void setGrid(TilesGrid value) {
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
    public int getImageHeight() {
        return imageHeight;
    }

    @JsonProperty("imageheight")
    public void setImageHeight(int value) {
        this.imageHeight = value;
    }

    @JsonProperty("imagewidth")
    public int getImageWidth() {
        return imageWidth;
    }

    @JsonProperty("imagewidth")
    public void setImageWidth(int value) {
        this.imageWidth = value;
    }

    @JsonProperty("margin")
    public int getMargin() {
        return margin;
    }

    @JsonProperty("margin")
    public void setMargin(int value) {
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
    public int getSpacing() {
        return spacing;
    }

    @JsonProperty("spacing")
    public void setSpacing(int value) {
        this.spacing = value;
    }

    @JsonProperty("tilecount")
    public int getTileCount() {
        return tileCount;
    }

    @JsonProperty("tilecount")
    public void setTileCount(int value) {
        this.tileCount = value;
    }

    @JsonProperty("tileheight")
    public int getTileHeight() {
        return tileHeight;
    }

    @JsonProperty("tileheight")
    public void setTileHeight(int value) {
        this.tileHeight = value;
    }

    @JsonProperty("tileoffset")
    public TilesTileOffset getTileoffset() {
        return tileoffset;
    }

    @JsonProperty("tileoffset")
    public void setTileoffset(TilesTileOffset value) {
        this.tileoffset = value;
    }

    @JsonProperty("tilewidth")
    public int getTileWidth() {
        return tileWidth;
    }

    @JsonProperty("tilewidth")
    public void setTileWidth(int value) {
        this.tileWidth = value;
    }

    @JsonProperty("wangsets")
    public List<TilesWangSet> getWangSets() {
        return wangSets;
    }

    @JsonProperty("wangsets")
    public void setWangSets(List<TilesWangSet> value) {
        this.wangSets = value;
    }
}
