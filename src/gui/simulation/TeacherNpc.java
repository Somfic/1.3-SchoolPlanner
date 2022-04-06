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
    void calculateTarget(Schedule schedule, int period, MapInfo mapInfo) {
        if(this.target != null) {
            return;
        }

        if(period > 10 || period < 0) {
            this.target = new Vector2(5, 28);
            return;
        }

        // Get the current period
        ScheduleItem currentPeriod = null;

        for (ScheduleItem item : schedule.getItems()) {
            if (item.getStartPeriod() <= period && item.getEndPeriod() >= period && item.getTeacher().equals(teacher)) {
                currentPeriod = item;
                break;
            }
        }

        if (currentPeriod == null) {
            // Not in a lesson, get a break area seat
            this.target = mapInfo.getTeacherArea().getSeat();
        } else {
            // Get a seat in the classroom
            this.target = mapInfo.getClassRoom(currentPeriod.getClassroom().getName()).getTeacherSeat();
        }
    }

    public Teacher getTeacher() {
        return teacher;
    }
}
