package gui.simulation;

import data.map.Map;
import data.map.Tile;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.dyn4j.geometry.Vector2;

import java.time.LocalDateTime;
import java.util.Timer;
import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.util.TimerTask;


public class SimulationView extends VBox {

    private final Map map;
    private final Canvas canvas;
    private int i = 0;
private Label timeLabel = new Label("uhi");
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
        this.getChildren().addAll(timeLabel,canvas);
    }

}
