package data.tiles;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TilesColor {
    private String color;
    private String name;
    private int probability;
    private int tile;

    @JsonProperty("color")
    public String getColor() {
        return color;
    }

    @JsonProperty("color")
    public void setColor(String value) {
        this.color = value;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.name = value;
    }

    @JsonProperty("probability")
    public int getProbability() {
        return probability;
    }

    @JsonProperty("probability")
    public void setProbability(int value) {
        this.probability = value;
    }

    @JsonProperty("tile")
    public int getTile() {
        return tile;
    }

    @JsonProperty("tile")
    public void setTile(int value) {
        this.tile = value;
    }
}
