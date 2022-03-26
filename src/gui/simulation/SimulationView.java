package gui.simulation;

import data.map.Map;
import data.map.Tile;
import gui.GameNode;
import gui.Gui;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import org.dyn4j.geometry.Vector2;

import java.awt.image.BufferedImage;


public class SimulationView extends VBox implements GameNode {

    private Map map;
    private final Canvas canvas;
    private BufferedImage mapImage;

    private int tileSize = 25;

    public SimulationView(Canvas canvas) {
        this.canvas = canvas;
        this.getChildren().add(canvas);
    }

    @Override
    public void onStart() {
        this.map = Map.fromFile("./res/school.tmj");

        if (map == null) {
            //todo: error
            return;
        }

        mapImage = map.generateImage(tileSize);
    }

    @Override
    public void onRender(GraphicsContext context) {
        context.drawImage(SwingFXUtils.toFXImage(mapImage, null), 0, 0, map.getWidth() * tileSize, map.getHeight() * tileSize);
    }

    @Override
    public void onUpdate(double deltaTime) {

    }
}


