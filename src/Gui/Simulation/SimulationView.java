package Gui.Simulation;

import Data.Map.Map;
import Data.Map.Tile;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.dyn4j.geometry.Vector2;

import java.io.File;


public class SimulationView extends VBox {

    private final Map map;
    private final Canvas canvas;

    private double tileSize = 10;

    public SimulationView() {
        map = Map.fromFile("./res/school.tmj");
        canvas = new Canvas(800, 600);

        if (map == null) {
            //todo: error
            return;
        }

        GraphicsContext context = canvas.getGraphicsContext2D();

        for (Tile tile : map.getTiles()) {
            Vector2 coords = new Vector2(tile.getX() * tileSize, tile.getY() * tileSize);
            if (tile.getImage() == null) {
                context.setFill(new Color(0.5, 0.5, 0.5, 1));
                context.fillRect(coords.x, coords.y, tileSize, tileSize);
            } else {
                context.drawImage(SwingFXUtils.toFXImage(tile.getImage(), null), coords.x, coords.y, tileSize, tileSize);
            }
        }

        this.getChildren().add(canvas);
    }
}
