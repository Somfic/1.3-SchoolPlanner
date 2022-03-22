package Data.Tiles;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TilesLayer {
    private List<TilesChunk> chunks;
    private String compression;
    private String encoding;
    private int height;
    private int id;
    private String name;
    private int opacity;
    private int startX;
    private int startY;
    private String type;
    private boolean visible;
    private int width;
    private int x;
    private int y;

    @JsonProperty("chunks")
    public List<TilesChunk> getChunks() {
        return chunks;
    }

    @JsonProperty("chunks")
    public void setChunks(List<TilesChunk> value) {
        this.chunks = value;
    }

    @JsonProperty("compression")
    public String getCompression() {
        return compression;
    }

    @JsonProperty("compression")
    public void setCompression(String value) {
        this.compression = value;
    }

    @JsonProperty("encoding")
    public String getEncoding() {
        return encoding;
    }

    @JsonProperty("encoding")
    public void setEncoding(String value) {
        this.encoding = value;
    }

    @JsonProperty("height")
    public int getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(int value) {
        this.height = value;
    }

    @JsonProperty("id")
    public int getID() {
        return id;
    }

    @JsonProperty("id")
    public void setID(int value) {
        this.id = value;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.name = value;
    }

    @JsonProperty("opacity")
    public int getOpacity() {
        return opacity;
    }

    @JsonProperty("opacity")
    public void setOpacity(int value) {
        this.opacity = value;
    }

    @JsonProperty("startx")
    public int getStartX() {
        return startX;
    }

    @JsonProperty("startx")
    public void setStartX(int value) {
        this.startX = value;
    }

    @JsonProperty("starty")
    public int getStartY() {
        return startY;
    }

    @JsonProperty("starty")
    public void setStartY(int value) {
        this.startY = value;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String value) {
        this.type = value;
    }

    @JsonProperty("visible")
    public boolean getVisible() {
        return visible;
    }

    @JsonProperty("visible")
    public void setVisible(boolean value) {
        this.visible = value;
    }

    @JsonProperty("width")
    public int getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(int value) {
        this.width = value;
    }

    @JsonProperty("x")
    public int getX() {
        return x;
    }

    @JsonProperty("x")
    public void setX(int value) {
        this.x = value;
    }

    @JsonProperty("y")
    public int getY() {
        return y;
    }

    @JsonProperty("y")
    public void setY(int value) {
        this.y = value;
    }
}
