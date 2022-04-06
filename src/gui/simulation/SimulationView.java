package gui.simulation;

import data.FramesPerSecond;
import data.Schedule;
import data.StudentGroup;
import data.Teacher;
import data.map.Map;
import data.map.Tile;
import gui.schedule.ScheduleChangeCallback;
import gui.settings.SettingCallback;
import gui.settings.StartTimeCallback;
import io.InputManager;
import javafx.animation.AnimationTimer;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logging.Logger;
import org.dyn4j.geometry.Vector2;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.Resizable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class SimulationView extends VBox implements Resizable, ScheduleChangeCallback, SettingCallback {
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
    private LocalTime gameTime = LocalTime.of(6, 0);

    private final MapInfo mapInfo = new MapInfo();
    private final List<Npc> npcs = new ArrayList<>();

    public SimulationView() {
        fps = new FramesPerSecond();
        setToUpdateBackground(true);

        map = Map.fromFile("./res/school.tmj");
        canvas = new Canvas(Toolkit.getDefaultToolkit().getScreenSize().getWidth(), Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 50);
        backgroundCanvas = new Canvas(Toolkit.getDefaultToolkit().getScreenSize().getWidth(), Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 50);
        this.pane = new Pane();
        pane.getChildren().addAll(backgroundCanvas, canvas);
        canvas.toFront();
        camera = new Camera(this);
    }

    public void start() {
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

        for (Npc npc : npcs) {
            boolean isInSpawn = false;
            for (Vector2 spawnPoint : mapInfo.getSpawnPoints()) {
                if (Math.round(npc.position.x) == spawnPoint.x && Math.round(npc.position.y) == spawnPoint.y) {
                    isInSpawn = true;
                    break;
                }
            }

            if (!isInSpawn) {
                graphics.drawImage(npc.getSprite(), (int) (npc.getPosition().x * tileSize) + 7, (int) (npc.getPosition().y * tileSize) - 4, (int) tileSize * 16 / 34, (int) tileSize, null);
            }
        }

        // Linear interpolation between morning red and evening blue
        double time = gameTime.getHour() + gameTime.getMinute() / 60.0;

        // Sunset and sunrise filter
//        // Red in the morning
//        double red = Math.max(Math.min(1, time / 6.0 / 2), 0);
//
//        // Blue in the evening
//        double blue = Math.max(Math.min(1, (time - 6.0) / 6.0 / 2), 0);
//
//        // 0 in the middle of the day, 1 at midnight
//        double alpha = Math.max(Math.min(1, (time - 12.0) / 12.0), 0);
//
//        GraphicsContext context = canvas.getGraphicsContext2D();
//        context.setFill(new javafx.scene.paint.Color(red, 0, blue, alpha));
//        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        graphics.setTransform(new AffineTransform());
        graphics.setColor(Color.GREEN);
        graphics.setFont(new Font("Arial", Font.PLAIN, 20));

        graphics.drawString(String.format("%02d", fps.getPfs()) + " fps", (int) 10, 25);
        graphics.drawString(String.format("%02d", gameTime.getHour())+ ":" +  String.format("%02d", gameTime.getMinute()), 10, 50);
        graphics.drawString("Period: " + period, 10, 75);

    }

    public void drawBackground(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.clearRect(0, 0, (int) backgroundCanvas.getWidth(), (int) backgroundCanvas.getHeight());
        graphics.setTransform(camera.getTransform());
        graphics.setBackground(Color.BLACK);
        this.toUpdateBackground = false;

        for (Tile tile : map.getTiles()) {
            if (tile.getImage() == null)
                continue;
            Vector2 coords = new Vector2(tile.getX() * tileSize, tile.getY() * tileSize);
            AffineTransform transform = graphics.getTransform();
            transform.translate(coords.x, coords.y);
            if (tile.getWritableImage() == null) {
                tile.setWritableImage(SwingFXUtils.toFXImage(tile.getImage(), null));
            }
            backgroundCanvas.getGraphicsContext2D().drawImage(tile.getWritableImage(), coords.x, coords.y, tileSize, tileSize);
            //graphics.drawImage(tile.getImage(), transform, null);
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

    private int period = 1;
    private int lastPeriod = -1;
    LocalDateTime lastPeriodChange = LocalDateTime.now();

    public void update(double deltaTime) {
        gameTime = gameTime.plusSeconds((long) (deltaTime * settings.getSpeed() * 100));

        // Go to 6:00 if past 18:00
        if(gameTime.getHour() >= 18) {
            gameTime = LocalTime.of(6, 0);
        }

        // Calculate the current period
        int minutesPastStart = (int) settings.getStartTime().until(gameTime, ChronoUnit.MINUTES);
        period = minutesPastStart / settings.getClassBlockLength() + 1;

        if (lastPeriod != period) {
            lastPeriod = period;
            calculateNewTargets();
        }

        if (LocalDateTime.now().isAfter(lastFps.plusSeconds(1))) {
            fps.update(deltaTime);
            lastFps = LocalDateTime.now();
//            Logger.debug("FPS: " + fps.getPfs());
        }

//        if (InputManager.getKeys().isKeyDownFirst(KeyCode.SPACE)) {
//            period++;
//            calculateNewTargets();
//        }
//
//        if (InputManager.getKeys().isKeyDownFirst(KeyCode.BACK_SPACE)) {
//            period--;
//            calculateNewTargets();
//        }

        for (Npc npc : npcs) {
            long milis = ChronoUnit.MILLIS.between(lastPeriodChange, LocalDateTime.now());
            int iteration = (int) Math.floor(milis / 100f);
            double factor = Math.round(milis % 100f) / 100f;

            npc.setPosition(npc.calculatePositionOnRoute(iteration, factor));
        }
    }

    private void calculateNewTargets() {
        npcs.forEach(Npc::resetTarget);
        mapInfo.getClassRooms().forEach(SeatInfo::resetSeats);
        mapInfo.getBreakArea().resetSeats();
        mapInfo.getTeacherArea().resetSeats();
        lastPeriodChange = LocalDateTime.now();

        npcs.forEach(npc -> {
            try {
                npc.calculateTarget(Schedule.get(), period, mapInfo);
                npc.calculateRoute(map);
            } catch (Exception e) {
                Logger.warn(e, "Could not calculate route for " + npc.getPerson().getName());
            }
        });
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

    @Override
    public void onChange() {
        generateNpcs();
    }

    private void generateNpcs() {
        npcs.clear();

        // Get all the unique student groups
        List<StudentGroup> studentGroups = new ArrayList<>();
        Schedule.get().getItems().forEach(item -> {
            if (item.getStudentGroups() != null) {
                item.getStudentGroups().forEach(studentGroup -> {
                    if (!studentGroups.contains(studentGroup)) {
                        studentGroups.add(studentGroup);
                    }
                });
            }
        });

        List<Integer> students = new ArrayList<>();

        // Create a NPC for each student
        studentGroups.forEach(studentGroup -> {
            studentGroup.getStudents().forEach(student -> {
                if (!students.contains(student.getStudentNumber())) {
                    Logger.debug("Creating NPC for student " + student.getName() + " (" + student.getStudentNumber() + ")");
                    students.add(student.getStudentNumber());
                    npcs.add(new StudentNpc(student, studentGroup.getName()));
                }
            });
        });

        // Create a NPC for each teacher
        List<Teacher> teachers = new ArrayList<>();
        Schedule.get().getItems().forEach(item -> {
            if (item.getTeacher() != null) {
                if (!teachers.contains(item.getTeacher())) {
                    teachers.add(item.getTeacher());
                }
            }
        });

        teachers.forEach(teacher -> {
            Logger.debug("Creating NPC for teacher: " + teacher.getName());
            npcs.add(new TeacherNpc(teacher));
        });

        calculateNewTargets();
    }

    private ScheduleSettings settings = new ScheduleSettings();

    @Override
    public void onSettingChange(ScheduleSettings newSettings) {
        this.settings = newSettings;
    }
}
