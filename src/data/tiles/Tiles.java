
package data.tiles;

import logging.Logger;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Tiles {
    private int compressionLevel;
    private int height;
    private boolean infinite;
    private List<TilesLayer> layers = new ArrayList<>();
    private int nextLayerId;
    private int nextObjectId;
    private String orientation;
    private String renderOrder;
    private String tiledVersion;
    private int tileHeight;
    private List<TilesTileSetSource> tileSets = new ArrayList<>();
    private int tileWidth;
    private String type;
    private String version;
    private int width;
    private TilesEditorSettings editorSettings;

    @JsonProperty("editorsettings")
    public TilesEditorSettings getEditorSettings() {
        return editorSettings;
    }

    @JsonProperty("editorsettings")
    public void setEditorSettings(TilesEditorSettings editorSettings) {
        this.editorSettings = editorSettings;
    }

    @JsonProperty("compressionlevel")
    public int getCompressionLevel() {
        return compressionLevel;
    }

    @JsonProperty("compressionlevel")
    public void getCompressionLevel(int value) {
        this.compressionLevel = value;
    }

    @JsonProperty("height")
    public int getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(int value) {
        this.height = value;
    }

    @JsonProperty("infinite")
    public boolean getInfinite() {
        return infinite;
    }

    @JsonProperty("infinite")
    public void setInfinite(boolean value) {
        this.infinite = value;
    }

    @JsonProperty("layers")
    public List<TilesLayer> getLayers() {
        return layers;
    }

    @JsonProperty("layers")
    public void setLayers(List<TilesLayer> value) {
        this.layers = value;
    }

    @JsonProperty("nextlayerid")
    public int getNextLayerId() {
        return nextLayerId;
    }

    @JsonProperty("nextlayerid")
    public void setNextLayerId(int value) {
        this.nextLayerId = value;
    }

    @JsonProperty("nextobjectid")
    public int getNextObjectId() {
        return nextObjectId;
    }

    @JsonProperty("nextobjectid")
    public void setNextObjectId(int value) {
        this.nextObjectId = value;
    }

    @JsonProperty("orientation")
    public String getOrientation() {
        return orientation;
    }

    @JsonProperty("orientation")
    public void setOrientation(String value) {
        this.orientation = value;
    }

    @JsonProperty("renderorder")
    public String getRenderOrder() {
        return renderOrder;
    }

    @JsonProperty("renderorder")
    public void setRenderOrder(String value) {
        this.renderOrder = value;
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

    @JsonProperty("tilesets")
    public List<TilesTileSetSource> getTileSets() {
        return tileSets;
    }

    @JsonProperty("tilesets")
    public void setTileSets(List<TilesTileSetSource> value) {
        this.tileSets = value;
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

    @JsonProperty("width")
    public int getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(int value) {
        this.width = value;
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            Logger.warn(e, "Failed to serialize tiles");
            return null;
        }
    }

    public static Tiles fromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(json, Tiles.class);
        } catch (Exception e) {
            Logger.warn(e, "Failed to deserialize tiles");
            return null;
        }
    }
}