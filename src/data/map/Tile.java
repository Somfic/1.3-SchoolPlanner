package data.map;

import java.awt.image.BufferedImage;

public class Tile {
    private final BufferedImage image;
    private final int x;
    private final int y;
    private final int z;

    public Tile(int x, int y, int z, BufferedImage image) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.z = z;
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
