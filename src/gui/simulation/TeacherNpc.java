package gui.simulation;

import data.Gender;
import data.Schedule;
import data.ScheduleItem;
import data.Teacher;
import io.FileManager;
import javafx.embed.swing.SwingFXUtils;
import logging.Logger;
import org.dyn4j.geometry.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TeacherNpc extends Npc {

    private final Teacher teacher;

    public TeacherNpc(Teacher teacher) {
        super(teacher);
        this.teacher = teacher;

        try {
            BufferedImage sprite;
            if (teacher.getGender() == Gender.MALE) {
                sprite = ImageIO.read(FileManager.getResource("./npcSprites.png")).getSubimage(0, 0, 20, 34);
            } else {
                sprite = ImageIO.read(FileManager.getResource("./npcSprites.png")).getSubimage(20, 0, 21, 34);
            }
            setSprite(sprite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    Vector2 getNextMove(Schedule schedule, int period, MapInfo mapInfo) {
        // Get the current period
        ScheduleItem currentPeriod = null;

        for (ScheduleItem item : schedule.getItems()) {
            if (item.getStartPeriod() <= period && item.getEndPeriod() >= period && item.getTeachers().equals(teacher)) {
                currentPeriod = item;
                break;
            }
        }

        // The student is not in a class
        if (currentPeriod == null) {
            return new Vector2(0, 0);
        }

        // Get a seat
        return mapInfo.getClassRoom(currentPeriod.getClassroom().getName()).getTeacherSeat();
    }

    @Override
    void giveUpSeat() {
        // Do nothing
    }

    public Teacher getTeacher() {
        return teacher;
    }
}
