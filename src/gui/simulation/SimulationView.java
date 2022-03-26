package gui.simulation;

import data.map.Map;
import data.map.Tile;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.dyn4j.geometry.Vector2;


public class SimulationView extends VBox {

    private final Map map;
    private final Canvas canvas;

    private double tileSize = 25;

    public SimulationView() {
        map = Map.fromFile("./res/school.tmj");
        canvas = new Canvas(1500, 800);

        if (map == null) {
            //todo: error
            return;
        }

        GraphicsContext context = canvas.getGraphicsContext2D();

        for (Tile tile : map.getTiles()) {
            Vector2 coords = new Vector2(tile.getX() * tileSize, tile.getY() * tileSize);
            if (tile.getImage() != null) {
                context.drawImage(SwingFXUtils.toFXImage(tile.getImage(), null), coords.x, coords.y, tileSize, tileSize);
            }
        }

        this.getChildren().add(canvas);
    }
}
