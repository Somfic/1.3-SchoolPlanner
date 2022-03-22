package Data.Map;

import Data.Tiles.*;
import IO.FileManager;
import Logging.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.*;
import java.util.zip.Inflater;

public class Map {

    private final int width;
    private final int height;
    private final List<Tile> tiles = new ArrayList<>();

    public Map(int width, int height) {

        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public Tile getTile(int x, int y) {
        for (Tile tile : tiles) {
            if (tile.getX() == x && tile.getY() == y) {
                return tile;
            }
        }

        Logger.warn("Could not find a tile at " + x + "x" + y);
        return null;
    }

    public static Map fromFile(String path) {
        // Read the JSON from the Tiles file
        String json = FileManager.read(path);

        // Parse the JSON into a Tiles object
        Tiles tiles = Tiles.fromJson(json);

        // If something went wrong while parsing the JSON, return null
        if (tiles == null) {
            Logger.error("Failed to load world from file: '" + path + "'");
            return null;
        }

        Logger.debug("Importing world from: '" + path + "'");

        // Import the sprites and layout
        HashMap<Integer, BufferedImage> sprites = importSprites(tiles.getTileSets());
        int[][] layout = importLayout(tiles.getLayers(), tiles.getWidth(), tiles.getHeight());

        Map world = new Map(tiles.getWidth(), tiles.getHeight());

        // Add the tiles to the world
        for (int x = 0; x < tiles.getWidth(); x++) {
            for (int y = 0; y < tiles.getHeight(); y++) {

                int blockId = layout[x][y];
                BufferedImage sprite = sprites.get(blockId);

                world.addTile(new Tile(x, y, sprite));
            }
        }

        return world;
    }

    private static int[][] importLayout(List<TilesLayer> layers, int width, int height) {
        int[][] layout = new int[width][height];

        for (TilesLayer layer : layers) {
            try {
                // Only works with zlib and base64 encoded data (for now?)
                if (!layer.getCompression().equals("zlib") || !layer.getEncoding().equals("base64")) {
                    throw new Exception("Unsupported layer encoding: " + layer.getEncoding() + " / " + layer.getCompression());
                }

                int amountOfImportedTiles = 0;
                for(TilesChunk chunk : layer.getChunks()) {
                    // Decode with base 64
                    byte[] bytes = Base64.getDecoder().decode(chunk.getData());

                    // Decompress with zlib
                    Inflater inflater = new Inflater();
                    inflater.setInput(bytes, 0, bytes.length);
                    bytes = new byte[4 * chunk.getWidth() * chunk.getHeight()];
                    inflater.inflate(bytes);
                    inflater.end();

                    // For each byte the array, grab the next 4 bytes and convert it to an int
                    int i = 0;
                    for (int x = 0; x < chunk.getWidth(); x++) {
                        for (int y = 0; y < chunk.getHeight(); y++) {
                            int block = Byte.toUnsignedInt(bytes[i])
                                    + (Byte.toUnsignedInt(bytes[i + 1]) << 8)
                                    + (Byte.toUnsignedInt(bytes[i + 2]) << 16)
                                    + (Byte.toUnsignedInt(bytes[i + 3]) << 24);

                            // If the block is not 0, add it to the layout
                            if (block != 0) {
                                amountOfImportedTiles++;
                                layout[x + chunk.getX()][y + chunk.getY()] = block;
                            }

                            // Move to the next block of 4 bytes
                            i += 4;
                        }
                    }
                }

                Logger.debug("Imported layer '" + layer.getName() + "' with " + amountOfImportedTiles + " tiles");

            } catch (Exception ex) {
                Logger.warn(ex, "Failed to import layer '" + layer.getName() + "'");
            }

            break;
        }

        return layout;
    }

    private static HashMap<Integer, BufferedImage> importSprites(List<TilesTileSetSource> tileSets) {
        HashMap<Integer, BufferedImage> sprites = new HashMap<>();

        for (TilesTileSetSource tileSetSource : tileSets) {
            try {
                String tileSetJson = FileManager.read("./res/" + tileSetSource.getSource());
                TilesTileSet tileSet = TilesTileSet.fromJson(tileSetJson);

                if(tileSet == null) {
                    throw new Exception("Failed to import tile set '" + tileSetSource.getSource() + "'");
                }

                InputStream imageStream = FileManager.getResource(tileSet.getImage());

                // If the image could not be found, throw an error
                if (imageStream == null) {
                    throw new Exception("Could not find tileset image for: " + tileSet.getImage());
                }

                // Load the image
                BufferedImage image = ImageIO.read(imageStream);

                // If the image does not match the expected size, throw an error
                if (tileSet.getImageWidth() != image.getWidth() || tileSet.getImageHeight() != image.getHeight()) {
                    throw new Exception("Tileset image size does not match tileset size, expected: " + tileSet.getImageWidth() + "x" + tileSet.getImageHeight() + ", got: " + image.getWidth() + "x" + image.getHeight());
                }

                // Calculate the amount of rows and columns in the image
                int columns = tileSet.getImageWidth() / tileSet.getTileWidth();
                int rows = tileSet.getImageHeight() / tileSet.getTileHeight();

                // Go through each row and column in the image
                for (int c = 0; c < columns; c++) {
                    for (int r = 0; r < rows; r++) {

                        // Calculate the x and y position of the tile in the image
                        int x = c * tileSet.getTileWidth();
                        int y = r * tileSet.getTileHeight();

                        // Crop the tile from the image
                        BufferedImage sprite = image.getSubimage(x, y, tileSet.getTileWidth(), tileSet.getTileHeight());

                        // Calculate the ID of the tile
                        int blockId = r * columns + c + tileSetSource.getFirstgid();

                        // Add the sprite to the sprites map
                        sprites.put(blockId, sprite);
                    }
                }

                Logger.debug("Imported tileset '" + tileSet.getName() + "' with " + columns * rows + " sprites");

            } catch (Exception ex) {
                Logger.warn(ex, "Failed to import tileset '" + tileSetSource.getSource() + "'");
            }
        }

        return sprites;
    }
}