package Data.Tiles;

import Logging.Logger;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TilesTileSet {
    private int columns;
    private String image;
    private int imageHeight;
    private int imageWidth;
    private int margin;
    private String name;
    private int spacing;
    private int tileCount;
    private String tiledVersion;
    private int tileHeight;
    private int tileWidth;
    private String type;
    private String version;

    @JsonProperty("columns")
    public int getColumns() {
        return columns;
    }

    @JsonProperty("columns")
    public void setColumns(int value) {
        this.columns = value;
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

    @JsonProperty("tiledversion")
    public String getTiledVersion() {
        return tiledVersion;
    }

    @JsonProperty("tiledversion")
    public void setTiledVersion(String value) {
        this.tiledVersion = value;
    }

    @JsonProperty("tileheight")
    public int getTileHeight() {
        return tileHeight;
    }

    @JsonProperty("tileheight")
    public void setTileHeight(int value) {
        this.tileHeight = value;
    }

    @JsonProperty("tilewidth")
    public int getTileWidth() {
        return tileWidth;
    }

    @JsonProperty("tilewidth")
    public void setTileWidth(int value) {
        this.tileWidth = value;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String value) {
        this.type = value;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String value) {
        this.version = value;
    }

    public static TilesTileSet fromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(json, TilesTileSet.class);
        } catch (Exception e) {
            Logger.warn(e, "Failed to deserialize tileset");
            return null;
        }
    }
}
