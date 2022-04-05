package gui.simulation;

import data.FramesPerSecond;
import data.map.Map;
import data.map.Tile;
import io.InputManager;
import javafx.animation.AnimationTimer;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.dyn4j.geometry.Vector2;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.Resizable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.time.LocalDateTime;

public class SimulationView extends VBox implements Resizable {
    private Map map;
    private Canvas canvas;
    private double tileSize = 25;
    private FramesPerSecond fps;
    private LocalDateTime lastFps = LocalDateTime.now();

    private Canvas backgroundCanvas;
    private Camera camera;
    private FXGraphics2D graphics2D;
    private FXGraphics2D backgroundGraphics;
    private Pane pane;
    private boolean toUpdateBackground;

    public SimulationView() {
        fps = new FramesPerSecond();
        setToUpdateBackground(true);

        map = Map.fromFile("./res/school.tmj");
        canvas = new Canvas(1500, 800);
        backgroundCanvas = new Canvas(1500, 800);
        this.pane = new Pane();
        pane.getChildren().addAll(backgroundCanvas, canvas);
        canvas.toFront();
        camera = new Camera(this);

    private final int tileSize = 25;

    public SimulationView(Canvas canvas) {
        this.setCenter(canvas);
    }

    MapInfo mapInfo = new MapInfo();

    @Override
    public void onStart() {
        this.map = Map.fromFile("./res/school.tmj");

        if (map == null) {
            //todo: error
            return;
        }

        graphics2D = new FXGraphics2D(canvas.getGraphicsContext2D());
        backgroundGraphics = new FXGraphics2D(backgroundCanvas.getGraphicsContext2D());
        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(graphics2D);
            }
        }.start();
        draw(graphics2D);

        this.getChildren().add(pane);
    }

    @Override
    public void draw(FXGraphics2D graphics) {
        if (toUpdateBackground)
            drawBackground(backgroundGraphics);

        canvas = createNewCanvas();
        graphics = graphics2D;
        graphics.setTransform(camera.getTransform());

        graphics.setTransform(new AffineTransform());
        graphics.setColor(Color.GREEN);
        graphics.setFont(new Font("Arial", Font.PLAIN, 20));
        graphics.drawString(fps + "", (int) backgroundCanvas.getWidth() - 30, 25);
    }

    public void drawBackground(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.clearRect(0, 0, (int) backgroundCanvas.getWidth(), (int) backgroundCanvas.getHeight());
        graphics.setTransform(camera.getTransform());
        graphics.setBackground(Color.BLACK);
        this.toUpdateBackground = false;

        for (Tile tile : map.getTiles()) {
            Vector2 coords = new Vector2(tile.getX() * tileSize, tile.getY() * tileSize);
            AffineTransform transform = graphics.getTransform();
            transform.translate(coords.x, coords.y);
//            backgroundCanvas.getGraphicsContext2D().drawImage(SwingFXUtils.toFXImage(tile.getImage(), null), coords.x, coords.y, tileSize, tileSize);
            graphics.drawImage(tile.getImage(), transform, null);
        }

    }

    private Canvas createNewCanvas() {
        pane.getChildren().remove(canvas);
        this.canvas = new Canvas(canvas.getWidth(), canvas.getHeight());
        pane.getChildren().add(this.canvas);
        this.canvas.toFront();
        graphics2D = new FXGraphics2D(canvas.getGraphicsContext2D());
        return this.canvas;
    }

    public void update(double deltaTime) {
        InputManager.update();

        if (LocalDateTime.now().isAfter(lastFps.plusSeconds(1))) {
            fps.update(deltaTime);
            lastFps = LocalDateTime.now();
//            Logger.debug("FPS: " + fps.getPfs());
        }

//        for (Event event : Festival.getInstance().getEventList()) {
//            if (time.isBefore(event.getEndTime()) && time.isAfter(event.getStartTime())) {
//                Podium selectedPodium = event.getPodium();
//                if (!event.isEventVisitorsSpawned()) {
//                    event.setEventVisitorsSpawned(true);
//                    for (int i = 0; i < event.getPopularity() * 3; i++) {
//                        Festival.getInstance().getVisitors().add(new Visitor(new Target(tiledMap.getCollisionLayer(), selectedPodium.getObject().getCenterTile())));
//                    }
//                }
//            }
//        }
//        for (Visitor visitor : Festival.getInstance().getVisitors()) {
//            if (!visitor.isSpawned()) {
//                if (timer <= 0) {
//                    visitor.spawn(tiledMap.getSpawn());
//                    timer = 1;
//                }
//            } else {
//                visitor.update(deltaTime);
//            }
//        }
    }

    public Canvas getBackgroundCanvas() {
        return backgroundCanvas;
    }

    public void setBackgroundCanvas(Canvas backgroundCanvas) {
        this.backgroundCanvas = backgroundCanvas;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public FXGraphics2D getGraphics2D() {
        return graphics2D;
    }

    public void setGraphics2D(FXGraphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }

    public FXGraphics2D getBackgroundGraphics() {
        return backgroundGraphics;
    }

    public void setBackgroundGraphics(FXGraphics2D backgroundGraphics) {
        this.backgroundGraphics = backgroundGraphics;
    }

    public boolean isToUpdateBackground() {
        return toUpdateBackground;
    }

    public void setToUpdateBackground(boolean toUpdateBackground) {
        this.toUpdateBackground = toUpdateBackground;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}

