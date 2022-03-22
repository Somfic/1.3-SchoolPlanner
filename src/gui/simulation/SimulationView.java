package gui.simulation;

import data.map.Map;
import data.map.Tile;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import org.dyn4j.geometry.Vector2;


public class SimulationView extends VBox {

    private final Map map;
    private final Canvas canvas;

    public SimulationView() {
        map = Map.fromFile("./res/isometric_grass_and_water.tmj");
        canvas = new Canvas(800, 600);

        if (map == null) {
            //todo: error
            return;
        }

        GraphicsContext context = canvas.getGraphicsContext2D();

        for (Tile tile : map.getTiles()) {
            Vector2 iso = toIsometric(tile.getX(), tile.getY());
            context.drawImage(SwingFXUtils.toFXImage(tile.getImage(), null), iso.x, iso.y);
        }

        this.getChildren().add(canvas);
    }

    private Vector2 toIsometric(Vector2 point) {
        double x = point.x;
        double y = point.y;

        return toIsometric(x, y);
    }

    private Vector2 toIsometric(double x, double y) {
        double z = 0;

        double tileSize = 30;

        double isoX = x * 0.5f * tileSize + y * -0.5f * tileSize - tileSize / 2f + canvas.getWidth() / 2f;
        double isoY = x * 0.25f * tileSize + y * 0.25f * tileSize - z * tileSize / 2f;

        return new Vector2(isoX, isoY);
    }
}
