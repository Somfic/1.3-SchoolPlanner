package Data.Tiles;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TilesLayer {
    private String compression;
    private String data;
    private String encoding;
    private int height;
    private int id;
    private String name;
    private int opacity;
    private String type;
    private boolean visible;
    private int width;
    private int x;
    private int y;

    private int[][] indices;

    @JsonProperty("compression")
    public String getCompression() {
        return compression;
    }

    @JsonProperty("compression")
    public void setCompression(String value) {
        this.compression = value;
    }

    @JsonProperty("data")
    public String getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(String value) {
        this.data = value;
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
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int value) {
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

    public int[][] getIndices() {
        return indices;
    }
}
