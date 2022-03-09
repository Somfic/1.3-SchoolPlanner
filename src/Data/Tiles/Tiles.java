
package Data.Tiles;

import Data.Schedule;
import Logging.Logger;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tiles {
    private long compressionLevel;
    private long height;
    private boolean infinite;
    private List<Layer> layers = new ArrayList<>();
    private long nextLayerId;
    private long nextObjectId;
    private String orientation;
    private String renderOrder;
    private String tiledVersion;
    private long tileHeight;
    private List<Tileset> tileSets = new ArrayList<>();
    private long tileWidth;
    private String type;
    private String version;
    private long width;

    @JsonProperty("compressionlevel")
    public long getCompressionLevel() {
        return compressionLevel;
    }

    @JsonProperty("compressionlevel")
    public void getCompressionLevel(long value) {
        this.compressionLevel = value;
    }

    @JsonProperty("height")
    public long getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(long value) {
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
    public List<Layer> getLayers() {
        return layers;
    }

    @JsonProperty("layers")
    public void setLayers(List<Layer> value) {
        this.layers = value;
    }

    @JsonProperty("nextlayerid")
    public long getNextLayerId() {
        return nextLayerId;
    }

    @JsonProperty("nextlayerid")
    public void setNextLayerId(long value) {
        this.nextLayerId = value;
    }

    @JsonProperty("nextobjectid")
    public long getNextObjectId() {
        return nextObjectId;
    }

    @JsonProperty("nextobjectid")
    public void setNextObjectId(long value) {
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
    public long getTileHeight() {
        return tileHeight;
    }

    @JsonProperty("tileheight")
    public void setTileHeight(long value) {
        this.tileHeight = value;
    }

    @JsonProperty("tilesets")
    public List<Tileset> getTileSets() {
        return tileSets;
    }

    @JsonProperty("tilesets")
    public void setTileSets(List<Tileset> value) {
        this.tileSets = value;
    }

    @JsonProperty("tilewidth")
    public long getTileWidth() {
        return tileWidth;
    }

    @JsonProperty("tilewidth")
    public void setTileWidth(long value) {
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
    public long getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(long value) {
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