package gui.simulation;

import data.*;
import data.map.Map;
import gui.GameNode;
import gui.schedule.ScheduleChangeCallback;
import io.InputManager;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import logging.Logger;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class SimulationView extends BorderPane implements GameNode, ScheduleChangeCallback {

    private Map map;
    private final Canvas canvas;
    private BufferedImage mapImage;

    private final List<Npc> npcs = new ArrayList<>();

    private final int tileSize = 25;

    public SimulationView(Canvas canvas) {
        this.canvas = canvas;
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

        mapImage = map.generateImage(tileSize);

        generateNpcs();
    }

    @Override
    public void onRender(GraphicsContext context) {
        context.drawImage(SwingFXUtils.toFXImage(mapImage, null), 0, 0, map.getWidth() * tileSize, map.getHeight() * tileSize);
        npcs.forEach(npc -> {
            if(npc instanceof StudentNpc) {
                context.setFill(new Color(0, 0, 1, 0.75));
            } else {
                context.setFill(new Color(1, 0, 0, 0.75));
            }

            context.fillOval(npc.getPosition().x * tileSize, npc.getPosition().y * tileSize, tileSize, tileSize);
        });

        context.fillText("Period: " + period, 10, 10);
    }

    private int period = 1;

    @Override
    public void onUpdate(double deltaTime) {
        // Set the target
        npcs.forEach(npc -> {
           npc.calculateTarget(Schedule.get(), period, mapInfo);
        });

        // Calculate the path
        npcs.forEach(npc -> {
            npc.getNextPathPoint(map);
        });

        if(InputManager.getKeys().isKeyDownFirst(KeyCode.SPACE)) {
            period++;
            calculateNewTargets();
        }

        if(InputManager.getKeys().isKeyDownFirst(KeyCode.BACK_SPACE)) {
            period--;
            calculateNewTargets();
        }

        period = Math.min(period, 10);
        period = Math.max(period, 1);
    }

    private void calculateNewTargets() {
        npcs.forEach(Npc::resetTarget);
        mapInfo.getClassRooms().forEach(SeatInfo::resetSeats);
        mapInfo.getBreakArea().resetSeats();
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
            if (item.getTeachers() != null) {
                item.getTeachers().forEach(teacher -> {
                    if (!teachers.contains(teacher)) {
                        teachers.add(teacher);
                    }
                });
            }

            if(item.getTeacher() != null) {
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

