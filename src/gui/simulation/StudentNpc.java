package gui.simulation;

import data.*;
import data.Student;
import io.FileManager;
import logging.Logger;
import org.dyn4j.geometry.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class StudentNpc extends Npc {
    private final Student student;
    private final String studentGroup;

    public StudentNpc(Student student, String studentGroup) {
        super(student);
        this.student = student;
        this.studentGroup = studentGroup;

        try {
            BufferedImage sprite;
            Random random = new Random();
            int colorOffset = 36 * random.nextInt(3);
            if (student.getGender() == Gender.MALE) {
                sprite = ImageIO.read(FileManager.getResource("./npcSprites.png")).getSubimage(41 + colorOffset, 0, 15, 34);
            } else {
                sprite = ImageIO.read(FileManager.getResource("./npcSprites.png")).getSubimage(56 + colorOffset, 0, 21, 34);
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

        // Get the current period
        ScheduleItem currentPeriod = null;

        for (ScheduleItem item : schedule.getItems()) {
            if (item.getStartPeriod() <= period && item.getEndPeriod() >= period) {
                for (StudentGroup studentGroup : item.getStudentGroups()) {
                    if (studentGroup.getName().equals(this.studentGroup)) {
                        currentPeriod = item;
                        break;
                    }
                }
                break;
            }
        }


        if (currentPeriod == null) {
            // The student is not in a class
            this.target = mapInfo.getBreakArea().getSeat();
        } else {
            this.target = mapInfo.getClassRoom(currentPeriod.getClassroom().getName()).getSeat();
        }
    }

    public Student getStudent() {
        return student;
    }

}
