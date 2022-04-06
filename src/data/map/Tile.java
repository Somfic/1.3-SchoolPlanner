package data.map;

import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;

public class Tile {
    private final BufferedImage image;
    private final int x;
    private final int y;
    private final int z;
    private final boolean canWalk;

    private WritableImage writableImage;

    public Tile(int x, int y, int z, BufferedImage image, boolean canWalk) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.z = z;
        this.canWalk = canWalk;
        writableImage = null;
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

    public boolean isCanWalk() {
        return canWalk;
    }

    public WritableImage getWritableImage() {
        return writableImage;
    }

    public void setWritableImage(WritableImage writableImage) {
        this.writableImage = writableImage;
    }
}
