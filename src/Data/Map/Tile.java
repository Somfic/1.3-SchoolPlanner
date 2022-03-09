package Data.Map;

import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class Tile {
    private final BufferedImage image;
    private final int x;
    private final int y;

    public Tile(int x, int y, BufferedImage image) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
