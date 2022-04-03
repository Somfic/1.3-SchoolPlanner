package gui.simulation;

import data.*;
import data.map.Map;
import gui.GameNode;
import gui.schedule.ScheduleChangeCallback;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import logging.Logger;
import org.dyn4j.geometry.Vector2;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class SimulationView extends VBox implements GameNode, ScheduleChangeCallback {

    private Map map;
    private final Canvas canvas;
    private BufferedImage mapImage;

    private final List<Npc> npcs = new ArrayList<>();

    private final int tileSize = 25;

    public SimulationView(Canvas canvas) {
        this.canvas = canvas;
        this.getChildren().add(canvas);
    }

    MapInfo mapInfo = new MapInfo();

    @Override
    public void onStart() {
        this.map = Map.fromFile("./res/school.tmj");

        if (map == null) {
            //todo: error
            return;
        }

        mapImage = map.generateImage(tileSize);

        generateNpcs();
    }

    @Override
    public void onRender(GraphicsContext context) {
        context.drawImage(SwingFXUtils.toFXImage(mapImage, null), 0, 0, map.getWidth() * tileSize, map.getHeight() * tileSize);
        npcs.forEach(npc -> {
            context.fillOval(npc.getPosition().x * tileSize, npc.getPosition().y * tileSize, tileSize, tileSize);
        });
    }

    @Override
    public void onUpdate(double deltaTime) {
        npcs.forEach(npc -> {
            npc.setPosition(npc.getNextMove(Schedule.get(), 1, mapInfo));
        });
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

        // Create a NPC for each student
        studentGroups.forEach(studentGroup -> {
            studentGroup.getStudents().forEach(student -> {
                Logger.debug("Creating NPC for student " + student.getName() + " in group " + studentGroup.getName());
                npcs.add(new StudentNpc(student, studentGroup.getName()));
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
    }

    @Override
    public void onChange() {
        generateNpcs();
    }
}

